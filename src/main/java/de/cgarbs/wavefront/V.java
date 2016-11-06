/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

/**
 * V is shorthand vor Vertex, a point in 3D space.
 * 
 * @author Christian Garbs <mitch@cgarbs.de>
 * @since 0.1.0
 *
 */
public class V implements Comparable<V>
{

	final double x;
	final double y;
	final double z;

	/**
	 * Creates a new vertex with the given coordinates.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @param z
	 *            z coordinate
	 * @since 0.1.0
	 */
	public V(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public int compareTo(V that)
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
		if (!(o instanceof V))
		{
			return false;
		}
		V v = (V) o;
		return this == v || (this.x == v.x && this.y == v.y && this.z == v.z);
	}

	@Override
	public String toString()
	{
		return String.format("V[%.0f %.0f %.0f]", x, y, z);
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

}
