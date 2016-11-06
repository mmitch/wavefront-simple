package de.cgarbs.wavefront.test.util;

import java.io.IOException;

import org.junit.Test;

public class NullOutputStreamTest
{
	@Test
	public void writeDoesNothing() throws IOException
	{
		NullOutputStream nos = new NullOutputStream();
		nos.write('h');
	}
}
