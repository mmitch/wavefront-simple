/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.math.BigDecimal;

import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Operation;

/**
 * A vector in 3D space.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * 
 * @since 0.3.0
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
	 * @since 0.3.0
	 */
	public Vec(double x, double y, double z)
	{
		super(x, y, z, "Vec");
	}

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
	 * @since 0.5.0
	 */
	public Vec(BigDecimal x, BigDecimal y, BigDecimal z)
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
				to.getX().subtract(from.getX()), //
				to.getY().subtract(from.getY()), //
				to.getZ().subtract(from.getZ()) //
		);
	}

	/**
	 * Creates a new vector from a coordinate pair,
	 * pointing from the first to the second coordinate.
	 * 
	 * @param coordinates
	 *            the coordinate pair
	 */
	public Vec(CoordinatePair coordinates)
	{
		this(coordinates.getFirst(), coordinates.getSecond());
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
