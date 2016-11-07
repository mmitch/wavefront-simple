/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

abstract class Triplet implements Comparable<Triplet>
{

	private double x;
	private double y;
	private double z;
	private String shorthand;

	protected Triplet(double x, double y, double z, String shorthand)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.shorthand = shorthand;
	}

	public double getX()
	{
		return x;
	}

	public double getY()
	{
		return y;
	}

	public double getZ()
	{
		return z;
	}

	@Override
	public int compareTo(Triplet that)
	{
		int c = compare(this.z, that.z);
		if (c == 0)
		{
			c = compare(this.y, that.y);
			if (c == 0)
			{
				compare(this.x, that.x);
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
		Triplet v = (Triplet) o;
		return this.x == v.x && this.y == v.y && this.z == v.z;
	}

	@Override
	public String toString()
	{
		return String.format("%s[%.0f %.0f %.0f]", shorthand, x, y, z);
	}

	private int compare(double d1, double d2)
	{
		if (d2 > d1)
		{
			return 1;
		}
		if (d2 < d1)
		{
			return -1;
		}
		return 0;
	}

	protected void translate(Vec vector)
	{
		x += vector.getX();
		y += vector.getY();
		z += vector.getZ();
	}

}