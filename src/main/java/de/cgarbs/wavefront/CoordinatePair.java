/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Operation;

/**
 * A pair of coordinates.
 *
 * TODO: just subclass an unmodifiable list, but Java 8 does not seem to have
 * that
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * 
 * @since 0.3.0
 */
public class CoordinatePair implements Iterable<Coordinate>, Operable<CoordinatePair>
{
	List<Coordinate> coordinates;

	/**
	 * Create a coordinate pair from two coordinates.
	 * 
	 * @param first
	 *            first coordinate
	 * @param second
	 *            second coordinate
	 * @since 0.3.0
	 */
	public CoordinatePair(Coordinate first, Coordinate second)
	{
		coordinates = Collections.unmodifiableList(Arrays.asList(first, second));
	}

	/**
	 * returns the first coordinate
	 * 
	 * @return first coordinate of the pair
	 * @since 0.3.0
	 */
	public Coordinate getFirst()
	{
		return coordinates.get(0);
	}

	/**
	 * returns the second coordinate
	 * 
	 * @return second coordinate of the pair
	 * @since 0.3.0
	 */
	public Coordinate getSecond()
	{
		return coordinates.get(1);
	}

	/**
	 * returns a sequential Stream of both coordinates
	 * 
	 * @return a Stream of both coordinates
	 * @since 0.3.0
	 */
	public Stream<Coordinate> stream()
	{
		return coordinates.stream();
	}

	@Override
	public Iterator<Coordinate> iterator()
	{
		return coordinates.iterator();
	}

	@Override
	public CoordinatePair apply(Operation operation)
	{
		return new CoordinatePair(//
				getFirst().apply(operation), //
				getSecond().apply(operation) //
		);
	}
}
