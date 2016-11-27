/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.io;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.text.IsEmptyString.isEmptyString;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.cgarbs.wavefront.Face;
import de.cgarbs.wavefront.V;
import de.cgarbs.wavefront.test.util.CaptureOutputStream;

public class WritableTest
{
	List<Face> FACES = Arrays.asList( //
			new Face(new V(1, 2, 3), new V(9, 8, 7), new V(-2, 2, -2)), //
			new Face(new V(3, 7, 8), new V(1, 2, -3), new V(2, 2, -2)) //
	);

	@Test
	public void writeToPassesAllFacesToOutputStream()
	{
		CaptureOutputStream cos1 = new CaptureOutputStream();
		CaptureOutputStream cos2 = new CaptureOutputStream();

		Writable w = new StubbedWritable();
		w.writeTo(cos1);

		// we can't "catch" the ObjWriter instance inside Writable,
		// so just do it by hand and compare the results...
		new ObjWriter(FACES).writeTo(cos2);

		assertThat(cos1.getCapture(), not(isEmptyString()));
		assertThat(cos1.getCapture(), is(cos2.getCapture()));
	}

	class StubbedWritable implements Writable
	{
		@Override
		public List<Face> faces()
		{
			return FACES;
		}
	}
}
