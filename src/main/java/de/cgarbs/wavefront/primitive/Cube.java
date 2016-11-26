/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.primitive;

import java.math.BigDecimal;

import de.cgarbs.wavefront.Coordinate;
import de.cgarbs.wavefront.Vec;

/**
 * A Cube is a {@link Cuboid} where all edges and faces have equal size.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.5.0
 */
public class Cube extends Cuboid
{
	/**
	 * Generates a non-rotated cube (all edges and faces are parallel to the
	 * coordinate axes) from a given Coordinate that is used as a starting
	 * Vertex and the length of the edges starting at that Coordinate.
	 * 
	 * @param coordinate
	 *            the base coordinate
	 * @param edgeLength
	 *            the edge length of the cube
	 * @since 0.5.0
	 */
	public Cube(Coordinate coordinate, double edgeLength)
	{
		this(coordinate, BigDecimal.valueOf(edgeLength));
	}

	/**
	 * Generates a non-rotated cube (all edges and faces are parallel to the
	 * coordinate axes) from a given Coordinate that is used as a starting
	 * Vertex and the length of the edges starting at that Coordinate.
	 * 
	 * @param coordinate
	 *            the base coordinate
	 * @param edgeLength
	 *            the edge length of the cube
	 * @since 0.5.0
	 */
	public Cube(Coordinate coordinate, BigDecimal edgeLength)
	{
		super( //
				coordinate, //
				new Vec(edgeLength, edgeLength, edgeLength) //
		);
	}
}
