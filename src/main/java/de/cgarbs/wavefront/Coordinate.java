/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Operation;

/**
 * A coordinate is a point in 3D space.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.1.0
 *
 */
public class Coordinate extends Triplet implements Operable<Coordinate>
{
	/**
	 * Creates a new coordinate.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 * @since 0.3.0
	 */
	public Coordinate(double x, double y, double z)
	{
		super(x, y, z, "C");
	}

	@Override
	public Coordinate apply(Operation operation)
	{
		return new Coordinate( //
				operation.applyX(getX()), //
				operation.applyY(getY()), //
				operation.applyZ(getZ()) //
		);
	}
}
