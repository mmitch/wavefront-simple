/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

import de.cgarbs.wavefront.test.util.CaptureOutputStream;

public class ObjWriterTest
{

	@Test
	public void faceIsConvertedToVertices()
	{
		Face face = new Face(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0));
		ObjWriter writer = new ObjWriter(Arrays.asList(face));
		assertThat(writer.uniqueVertices, hasSize(3));
		assertThat(writer.faces, hasSize(1));
	}

	@Test
	public void duplicateVertexIsRemoved()
	{
		Face face = new Face(new V(0, 0, 1), new V(0, 1, 0), new V(0, 0, 1));
		ObjWriter writer = new ObjWriter(Arrays.asList(face));
		assertThat(writer.uniqueVertices, hasSize(2));
		assertThat(writer.faces, hasSize(1));
	}

	@Test
	public void checkTriangleFile()
	{
		Face face = new Face(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0));
		ObjWriter writer = new ObjWriter(Arrays.asList(face));
		CaptureOutputStream os = new CaptureOutputStream();
		writer.writeTo(os);
		assertThat(os.getCapture(),
				is( //
						"V 0 0 1\n" + //
								"V 0 1 0\n" + //
								"V 1 0 0\n" + //
								"f 1 2 3\n" //
				));
	}
}
