/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import java.math.BigDecimal;

/**
 * This operation keeps the greatest value of
 * each encountered x, y and z coordinates.
 * The objects it is applied to are left unchanged.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @since 0.3.0
 */
public class FindGreatest extends Accumulator
{
	@Override
	protected BigDecimal function(BigDecimal oldValue, BigDecimal newValue)
	{
		return oldValue.max(newValue);
	}

}
