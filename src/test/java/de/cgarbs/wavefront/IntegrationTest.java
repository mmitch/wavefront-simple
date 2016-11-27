/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.cgarbs.wavefront.op.Scale;
import de.cgarbs.wavefront.op.Translation;
import de.cgarbs.wavefront.primitive.Cube;
import de.cgarbs.wavefront.test.util.CaptureOutputStream;

public class IntegrationTest
{
	@Test
	public void generateSceneAndCheckCreatedFile()
	{
		CaptureOutputStream outputStream = new CaptureOutputStream();

		new Cube(Coordinate.ORIGIN, 1).apply(new Scale(2)) //
				.apply(new Translation(new Vec(-1, -1, -1))) //
				.writeTo(outputStream);

		assertThat(outputStream.getCapture(), //
				is("V -1 -1 -1\n" + //
						"V -1 -1 1\n" + //
						"V -1 1 -1\n" + //
						"V -1 1 1\n" + //
						"V 1 -1 -1\n" + //
						"V 1 -1 1\n" + //
						"V 1 1 -1\n" + //
						"V 1 1 1\n" + //
						"f 1 3 4 2\n" + //
						"f 1 3 7 5\n" + //
						"f 1 5 6 2\n" + //
						"f 2 4 8 6\n" + //
						"f 3 7 8 4\n" + //
						"f 5 7 8 6\n"));
	}
}
