/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import java.math.BigDecimal;

import de.cgarbs.wavefront.Coordinate;

abstract class Accumulator implements Operation
{
	protected BigDecimal x;
	protected BigDecimal y;
	protected BigDecimal z;

	abstract BigDecimal function(BigDecimal oldValue, BigDecimal newValue);

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
	public BigDecimal applyX(BigDecimal x)
	{
		this.x = functionOrInit(this.x, x);

		return x;
	}

	@Override
	public BigDecimal applyY(BigDecimal y)
	{
		this.y = functionOrInit(this.y, y);

		return y;
	}

	@Override
	public BigDecimal applyZ(BigDecimal z)
	{
		this.z = functionOrInit(this.z, z);

		return z;
	}

	private BigDecimal functionOrInit(BigDecimal oldValue, BigDecimal newValue)
	{
		if (oldValue == null)
		{
			return newValue;
		}
		return function(oldValue, newValue);
	}
}
