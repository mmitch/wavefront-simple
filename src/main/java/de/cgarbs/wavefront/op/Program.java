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
		for (Operation operation : operations)
		{
			x = operation.applyX(x);
		}
		return x;
	}

	@Override
	public double applyY(double y)
	{
		for (Operation operation : operations)
		{
			y = operation.applyY(y);
		}
		return y;
	}

	@Override
	public double applyZ(double z)
	{
		for (Operation operation : operations)
		{
			z = operation.applyZ(z);
		}
		return z;
	}
}
