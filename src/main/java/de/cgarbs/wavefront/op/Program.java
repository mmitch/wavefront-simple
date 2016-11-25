/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import java.math.BigDecimal;
import java.util.function.BiFunction;

/**
 * A simple program consisting of multiple Operations.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @since 0.3.0
 */
public class Program implements Operation
{
	private Operation[] operations;

	/**
	 * Creates a new program.
	 * 
	 * @param operations
	 *            the operations describing the program
	 * 
	 * @since 0.3.0
	 */
	public Program(Operation... operations)
	{
		this.operations = operations;
	}

	@Override
	public BigDecimal applyX(BigDecimal x)
	{
		return applyForAll((operation, value) -> operation.applyX(value), x);
	}

	@Override
	public BigDecimal applyY(BigDecimal y)
	{
		return applyForAll((operation, value) -> operation.applyY(value), y);
	}

	@Override
	public BigDecimal applyZ(BigDecimal z)
	{
		return applyForAll((operation, value) -> operation.applyZ(value), z);
	}

	private BigDecimal applyForAll(BiFunction<Operation, BigDecimal, BigDecimal> func, BigDecimal value)
	{
		for (Operation operation : operations)
		{
			value = func.apply(operation, value);
		}
		return value;
	}
}
