package de.cgarbs.wavefront.meta;

/**
 * A Supplier for Classes with one-argument constructors.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.2.0
 *
 * @param <R>
 *            class to instanciate
 * @param <T>
 *            constructor argument type
 */
@FunctionalInterface
public interface ArgSupplier<R, T>
{
	/**
	 * Creates a new instance of the given class.
	 * 
	 * @param arg
	 *            constructor argument
	 * @return new class instance
	 * 
	 * @since 0.2.0
	 * 
	 */
	R get(T arg);
}
