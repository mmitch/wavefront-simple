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

	public BigDecimal getX()
	{
		return x;
	}

	public BigDecimal getY()
	{
		return y;
	}

	public BigDecimal getZ()
	{
		return z;
	}

	@Override
	public int compareTo(Triplet that)
	{
		int c = this.x.compareTo(that.x);
		if (c == 0)
		{
			c = this.y.compareTo(that.y);
			if (c == 0)
			{
				c = this.z.compareTo(that.z);
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

		return equalsIgnoreScale(this.x, that.x) //
				&& equalsIgnoreScale(this.y, that.y) //
				&& equalsIgnoreScale(this.z, that.z) //
				&& this.shorthand.equals(that.shorthand);
	}

	@Override
	public String toString()
	{
		// TODO: use real BigDecimal format
		return String.format("%s[%.1f %.1f %.1f]", shorthand, x, y, z);
	}

	private static boolean equalsIgnoreScale(BigDecimal bd1, BigDecimal bd2)
	{
		return bd1.compareTo(bd2) == 0;
	}

}