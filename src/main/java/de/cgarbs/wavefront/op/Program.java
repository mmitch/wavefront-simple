/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

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
	public double applyX(double x)
	{
		return applyForAll((operation, value) -> operation.applyX(value), x);
	}

	@Override
	public double applyY(double y)
	{
		return applyForAll((operation, value) -> operation.applyY(value), y);
	}

	@Override
	public double applyZ(double z)
	{
		return applyForAll((operation, value) -> operation.applyZ(value), z);
	}

	private double applyForAll(DoubleBiDoubleFunction<Operation> func, double value)
	{
		for (Operation operation : operations)
		{
			value = func.applyAsDouble(operation, value);
		}
		return value;
	}

	@FunctionalInterface
	interface DoubleBiDoubleFunction<T>
	{
		double applyAsDouble(T t, double value);
	}
}
