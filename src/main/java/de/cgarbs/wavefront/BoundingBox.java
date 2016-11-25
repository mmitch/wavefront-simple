/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import de.cgarbs.wavefront.op.Scale;
import de.cgarbs.wavefront.op.Translation;

/**
 * A Bounding Box describes the smallest possible non-rotated cuboid
 * (all edges and faces are parallel to the coordinate axes) that can
 * completely contain the corresponding object.
 * 
 * The Bounding Box is described by just two coordinates: one having the
 * smallest x, y and z coordinates and one having the greatest x, y, z
 * coordinates of that cuboid's vertices.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @since 0.3.0
 */
public class BoundingBox extends CoordinatePair
{

	/**
	 * Create a bounding box from two coordinates.
	 * 
	 * @param low
	 *            smallest coordinate
	 * @param high
	 *            greatest coordinate
	 */
	public BoundingBox(Coordinate low, Coordinate high)
	{
		super(low, high);
	}

	/**
	 * Returns the coordinate of the center of the Bounding Box.
	 * 
	 * @return center coordinate of the Bounding Box
	 */
	public Coordinate getCenter()
	{
		Vec diameter = new Vec(this);
		return getFirst().apply(new Translation(diameter.apply(new Scale(0.5))));
	}

	@Override
	public String toString()
	{
		return String.format("BB{%s %s}", getFirst(), getSecond());
	}
}
