/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import java.math.BigDecimal;

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
	public BigDecimal applyX(BigDecimal x)
	{
		return x.add(vector.getX());
	}

	@Override
	public BigDecimal applyY(BigDecimal y)
	{
		return y.add(vector.getY());
	}

	@Override
	public BigDecimal applyZ(BigDecimal z)
	{
		return z.add(vector.getZ());
	}

}
