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
public class V {

	final double x;
	final double y;
	final double z;

	/**
	 * Creates a new vertex with the given coordinates.
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 * @param z z coordinate
	 * @since 0.1.0
	 */
	public V(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	public boolean equals(Object o) {
		if (! (o instanceof V)) {
			return false;
		}
		V v = (V) o;
		return this.x == v.x && this.y == v.y && this.z == v.z;
	}
	
}
