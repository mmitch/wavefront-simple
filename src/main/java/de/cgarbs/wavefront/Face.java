/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.cgarbs.wavefront.op.FindGreatest;
import de.cgarbs.wavefront.op.FindSmallest;
import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Operation;
import de.cgarbs.wavefront.op.Program;

/**
 * A face is a surface bounded by its vertices.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.4.0
 *
 */
public class Face implements Comparable<Face>, Operable<Face>, HasBoundingBox, Centerable<Face>
{

	private List<V> vertices = new ArrayList<>();

	/**
	 * Creates a new face bounded by the given vertices. At least
	 * three vertices must be present.
	 * 
	 * @param v1
	 *            first vertex
	 * @param v2
	 *            second vertex
	 * @param v3
	 *            third vertex
	 * @param additionalVertices
	 *            additional vertices
	 * @since 0.4.0
	 */
	public Face(V v1, V v2, V v3, V... additionalVertices)
	{
		// TODO: delegate to plain List constructor? but building an
		// intermediate List is unnecessary overhead
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.addAll(Arrays.asList(additionalVertices));
	}

	private Face(List<V> vertices)
	{
		this.vertices.addAll(vertices);
	}

	/**
	 * returns a Stream of the Face's vertices
	 * 
	 * @return the vertices of this Face
	 */
	public Stream<V> vertices()
	{
		return vertices.stream();
	}

	@Override
	public String toString()
	{
		return vertices.stream() //
				.map(V::toString) //
				.collect(Collectors.joining(" ", "F{", "}"));
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Face))
		{
			return false;
		}
		Face f = (Face) o;
		if (this.vertices.size() != f.vertices.size())
		{
			return false;
		}
		Iterator<V> ours = this.vertices.stream().sorted().iterator();
		Iterator<V> theirs = f.vertices.stream().sorted().iterator();
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
	public int compareTo(Face that)
	{
		int c;
		c = this.vertices.size() - that.vertices.size();
		if (c == 0)
		{
			Iterator<V> ours = this.vertices.stream().sorted().iterator();
			Iterator<V> theirs = that.vertices.stream().sorted().iterator();
			while (c == 0 && ours.hasNext())
			{
				c = theirs.next().compareTo(ours.next());
			}
		}
		return c;
	}

	@Override
	public Face apply(Operation operation)
	{
		return new Face( //
				vertices() //
						.map((v) -> v.apply(operation)) //
						.collect(Collectors.toList()) //
		);
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

}
