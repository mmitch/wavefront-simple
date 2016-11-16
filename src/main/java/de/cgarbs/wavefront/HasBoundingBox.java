/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

/**
 * A Bounding Box can be calculated.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @since 0.3.0
 */
public interface HasBoundingBox
{
	/**
	 * calculate the Bounding Box of this object
	 * 
	 * @return the Bounding Box
	 */
	BoundingBox getBoundingBox();
}
