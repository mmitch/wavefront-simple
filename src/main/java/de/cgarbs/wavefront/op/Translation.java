/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import de.cgarbs.wavefront.Vec;

/**
 * The translation operation moves something along a given vector.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @since 0.3.0
 */
public class Translation implements Operation
{
	private Vec vector;

	/**
	 * Creates a translation operation along the given vector.
	 * 
	 * @param vector
	 *            the translation vector
	 * 
	 * @since 0.3.0
	 */
	public Translation(Vec vector)
	{
		this.vector = vector;
	}

	@Override
	public double applyX(double x)
	{
		return x + vector.getX();
	}

	@Override
	public double applyY(double y)
	{
		return y + vector.getY();
	}

	@Override
	public double applyZ(double z)
	{
		return z + vector.getZ();
	}

}
