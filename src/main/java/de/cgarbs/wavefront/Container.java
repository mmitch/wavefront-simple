/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.cgarbs.wavefront.op.FindGreatest;
import de.cgarbs.wavefront.op.FindSmallest;
import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Operation;
import de.cgarbs.wavefront.op.Program;

abstract class Container<SELF, DATA extends Operable<DATA> & Comparable<? super DATA>> //
		implements Comparable<Container<SELF, DATA>>, Centerable<SELF>
{
	private final List<DATA> data;
	private final String shorthand;

	protected Container(String shorthand, List<DATA> data)
	{
		this.shorthand = shorthand;
		this.data = data;
	}

	@Override
	public String toString()
	{
		return data.stream() //
				.map(DATA::toString) //
				.collect(Collectors.joining(" ", shorthand + "{", "}"));
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Container))
		{
			return false;
		}
		// FIXME: because of type erasure, empty Containers of different types
		// will be equal!
		Container<?, ?> c = (Container<?, ?>) o;
		if (this.data.size() != c.data.size())
		{
			return false;
		}
		Iterator<DATA> ours = this.data.stream().sorted().iterator();
		Iterator<?> theirs = c.data.stream().sorted().iterator();
		while (ours.hasNext())
		{
			if (!ours.next().equals(theirs.next()))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public int compareTo(Container<SELF, DATA> that)
	{
		int c;
		c = this.data.size() - that.data.size();
		if (c == 0)
		{
			Iterator<DATA> ours = this.data.stream().sorted().iterator();
			Iterator<DATA> theirs = that.data.stream().sorted().iterator();
			while (c == 0 && ours.hasNext())
			{
				c = theirs.next().compareTo(ours.next());
			}
		}
		return c;
	}

	@Override
	public SELF apply(Operation operation)
	{
		List<DATA> newData = stream() //
				.map((d) -> d.apply(operation)) //
				.collect(Collectors.toList());

		return getInstance(newData);
	}

	@Override
	public BoundingBox getBoundingBox()
	{
		FindSmallest smallest = new FindSmallest();
		FindGreatest greatest = new FindGreatest();

		apply(new Program(smallest, greatest));

		return new BoundingBox( //
				smallest.getResult(), //
				greatest.getResult() //
		);
	}

	abstract protected SELF getInstance(List<DATA> data);

	protected Stream<DATA> stream()
	{
		return data.stream();
	}
}
