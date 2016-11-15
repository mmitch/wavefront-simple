/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

/**
 * The scale operation scales the distance from the
 * coordinate origin by the given factor while keeping
 * the bearing from the origin.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @since 0.3.0
 */
public class Scale implements Operation
{

	private double factor;

	/**
	 * Create a scale operation with a given factor.
	 * 
	 * @param factor
	 *            the scale factor
	 * 
	 * @since 0.3.0
	 */
	public Scale(double factor)
	{
		this.factor = factor;
	}

	@Override
	public double applyX(double x)
	{
		return x * factor;
	}

	@Override
	public double applyY(double y)
	{
		return y * factor;
	}

	@Override
	public double applyZ(double z)
	{
		return z * factor;
	}

}
