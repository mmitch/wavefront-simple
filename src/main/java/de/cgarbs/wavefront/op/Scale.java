/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import java.math.BigDecimal;

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

	private BigDecimal factor;

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
		this(BigDecimal.valueOf(factor));
	}

	/**
	 * Create a scale operation with a given factor.
	 * 
	 * @param factor
	 *            the scale factor
	 * 
	 * @since 0.5.0
	 */
	public Scale(BigDecimal factor)
	{
		this.factor = factor;
	}

	@Override
	public BigDecimal applyX(BigDecimal x)
	{
		return x.multiply(factor);
	}

	@Override
	public BigDecimal applyY(BigDecimal y)
	{
		return y.multiply(factor);
	}

	@Override
	public BigDecimal applyZ(BigDecimal z)
	{
		return z.multiply(factor);
	}

}
