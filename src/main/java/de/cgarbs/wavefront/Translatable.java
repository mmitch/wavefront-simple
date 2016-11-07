/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

/**
 * Describes things that can be translated.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 */
public interface Translatable
{
	/**
	 * Move something along a vector.
	 * 
	 * @param vector
	 */
	void translate(Vec vector);
}
