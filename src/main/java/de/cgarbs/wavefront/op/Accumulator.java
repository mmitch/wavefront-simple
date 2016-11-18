/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import de.cgarbs.wavefront.Coordinate;

abstract class Accumulator implements Operation
{
	protected double x;
	protected double y;
	protected double z;

	Accumulator(double init)
	{
		this.x = init;
		this.y = init;
		this.z = init;
	}

	abstract double function(double oldValue, double newValue);

	/**
	 * Returns the calculated result of this operation.
	 * 
	 * @return the calculated result
	 * 
	 * @since 0.3.0
	 */
	public Coordinate getResult()
	{
		return new Coordinate(x, y, z);
	}

	@Override
	public double applyX(double x)
	{
		this.x = function(this.x, x);

		return x;
	}

	@Override
	public double applyY(double y)
	{
		this.y = function(this.y, y);

		return y;
	}

	@Override
	public double applyZ(double z)
	{
		this.z = function(this.z, z);

		return z;
	}
}
