/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.math.BigDecimal;

import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Operation;

/**
 * V is shorthand vor Vertex, a point in 3D space
 * that is part of a Face.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.1.0
 *
 */
public class V extends Triplet implements Operable<V>
{

	/**
	 * Creates a new vertex with the given coordinates.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 * @since 0.1.0
	 */
	public V(double x, double y, double z)
	{
		super(x, y, z, "V");
	}

	/**
	 * Creates a new vertex with the given coordinates.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 * @since 0.5.0
	 */
	public V(BigDecimal x, BigDecimal y, BigDecimal z)
	{
		super(x, y, z, "V");
	}

	/**
	 * Creates a new vertex at the given Coordinate.
	 * 
	 * @param c
	 *            the Coordinate
	 * @since 0.3.0
	 */
	public V(Coordinate c)
	{
		this(c.getX(), c.getY(), c.getZ());
	}

	@Override
	public V apply(Operation operation)
	{
		return new V( //
				operation.applyX(getX()), //
				operation.applyY(getY()), //
				operation.applyZ(getZ()) //
		);
	}
}
