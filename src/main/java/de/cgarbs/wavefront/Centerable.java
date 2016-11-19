/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Translation;

/**
 * A centerable object can be centered on the point of origin
 * regarding its Bounding Box.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * 
 * @param <T>
 *            the type of the object
 * 
 * @since 0.3.0
 *
 */
public interface Centerable<T> extends Operable<T>, HasBoundingBox
{
	/**
	 * Centers the object on the Coordinate origin.
	 * 
	 * A translation is applied so that the center of the
	 * Bounding Box is on the origin.
	 * 
	 * @return a translated and centered copy of the object
	 */
	default T centerOnOrigin()
	{
		BoundingBox boundingBox = getBoundingBox();
		Coordinate center = boundingBox.getCenter();
		Vec move = new Vec(center, Coordinate.ORIGIN);
		return apply(new Translation(move));
	}

}
