/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Operation;

/**
 * A vector in 3D space.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 */
public class Vec extends Triplet implements Operable<Vec>
{
	/**
	 * Creates a new vector with the given dimensions.
	 * 
	 * TODO: find proper names for this
	 * 
	 * @param x
	 *            x offset
	 * @param y
	 *            y offset
	 * @param z
	 *            z offset
	 */
	public Vec(double x, double y, double z)
	{
		super(x, y, z, "Vec");
	}

	/**
	 * Creates a new vector connecting two given coordinates.
	 * 
	 * @param from
	 *            start point of vector
	 * @param to
	 *            end point of vector
	 */
	public Vec(Coordinate from, Coordinate to)
	{
		this( //
				to.getX() - from.getX(), //
				to.getY() - from.getY(), //
				to.getZ() - from.getZ() //
		);
	}

	@Override
	public Vec apply(Operation operation)
	{
		return new Vec( //
				operation.applyX(getX()), //
				operation.applyY(getY()), //
				operation.applyZ(getZ()) //
		);
	}
}
