/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

/**
 * An operation that can act on 3D coordinates.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @since 0.3.0
 */
public interface Operation
{
	/**
	 * Applies the operation to the x coordinate
	 * and calculates a new x coordinate as the result.
	 * 
	 * @param x
	 *            old x coordinate
	 * @return new x coordinate
	 */
	double applyX(double x);

	/**
	 * Applies the operation to the y coordinate
	 * and calculates a new y coordinate as the result.
	 * 
	 * @param y
	 *            old y coordinate
	 * @return new y coordinate
	 */
	double applyY(double y);

	/**
	 * Applies the operation to the z coordinate
	 * and calculates a new z coordinate as the result.
	 * 
	 * @param z
	 *            old z coordinate
	 * @return new z coordinate
	 */
	double applyZ(double z);
}
