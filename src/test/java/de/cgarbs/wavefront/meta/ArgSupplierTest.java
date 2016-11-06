package de.cgarbs.wavefront.meta;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ArgSupplierTest
{
	class SimpleClass
	{
		final String attr;

		SimpleClass(String attr)
		{
			this.attr = attr;
		}
	}

	@Test
	public void checkSupplierWithConstructorArgument()
	{
		ArgSupplier<SimpleClass, String> supplier = (s) -> new SimpleClass(s);
		SimpleClass sc = supplier.get("foo");
		assertThat(sc.attr, is("foo"));
	}
}
