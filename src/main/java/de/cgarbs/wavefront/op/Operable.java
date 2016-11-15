/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

/**
 * Describes something that operations can act on.
 * Work immutable and generates new objects.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 *
 * @param <T>
 *            the type of object that is generated
 *            after applying the operation
 * 
 * @since 0.3.0
 */
public interface Operable<T>
{
	/**
	 * Apply an operation to the current instance
	 * and create a new object (immutable).
	 * 
	 * @param operation
	 *            the operation to apply
	 * @return the new object
	 */
	T apply(Operation operation);
}
