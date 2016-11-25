/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.math.BigDecimal;

abstract class Triplet implements Comparable<Triplet>
{

	private final BigDecimal x;
	private final BigDecimal y;
	private final BigDecimal z;
	private final String shorthand;

	protected Triplet(double x, double y, double z, String shorthand)
	{
		this( //
				BigDecimal.valueOf(x), //
				BigDecimal.valueOf(y), //
				BigDecimal.valueOf(z), //
				shorthand //
		);
	}

	protected Triplet(BigDecimal x, BigDecimal y, BigDecimal z, String shorthand)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.shorthand = shorthand;
	}

	public double getX()
	{
		return x.doubleValue();
	}

	public double getY()
	{
		return y.doubleValue();
	}

	public double getZ()
	{
		return z.doubleValue();
	}

	@Override
	public int compareTo(Triplet that)
	{
		int c = that.z.compareTo(this.z);
		if (c == 0)
		{
			c = that.y.compareTo(this.y);
			if (c == 0)
			{
				c = that.x.compareTo(this.x);
			}
		}
		return c;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!this.getClass().equals(o.getClass()))
		{
			return false;
		}
		Triplet that = (Triplet) o;
		return this.x.equals(that.x) //
				&& this.y.equals(that.y) //
				&& this.z.equals(that.z) //
				&& this.shorthand.equals(that.shorthand);
	}

	@Override
	public String toString()
	{
		// TODO: use real BigDecimal format
		return String.format("%s[%.1f %.1f %.1f]", shorthand, x, y, z);
	}

}