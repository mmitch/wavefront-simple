/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

/**
 * The identity operation does nothing, it maps
 * the source to unchanged to the output (propably
 * in a new instance).
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @since 0.3.0
 */
public class Identity implements Operation
{

	@Override
	public double applyX(double x)
	{
		return x;
	}

	@Override
	public double applyY(double y)
	{
		return y;
	}

	@Override
	public double applyZ(double z)
	{
		return z;
	}

}
