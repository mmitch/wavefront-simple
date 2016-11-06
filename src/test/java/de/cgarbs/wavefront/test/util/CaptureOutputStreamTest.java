/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.test.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

public class CaptureOutputStreamTest
{

	@Test
	public void captureWrittenByte() throws IOException
	{
		CaptureOutputStream cos = new CaptureOutputStream();
		cos.write('h');
		assertThat(cos.getCapture(), is("h"));
		cos.close();
	}

	@Test
	public void capturePrintStream() throws IOException
	{
		CaptureOutputStream cos = new CaptureOutputStream();
		PrintStream ps = new PrintStream(cos);
		ps.println("Hello world");
		assertThat(cos.getCapture(), is("Hello world\n"));
		cos.close();
	}
}
