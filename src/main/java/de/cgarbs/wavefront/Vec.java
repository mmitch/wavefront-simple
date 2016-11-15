/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

/**
 * A vector in 3D space.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 */
public class Vec extends Triplet
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
}
