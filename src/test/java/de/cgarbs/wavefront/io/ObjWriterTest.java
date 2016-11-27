/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.io;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

import de.cgarbs.wavefront.Face;
import de.cgarbs.wavefront.V;
import de.cgarbs.wavefront.io.ObjWriter;
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

	@Test
	public void plainOutputStreamIsConvertedToPrintStream()
	{
		// given
		ObjWriter writer = new ObjWriter(Collections.emptyList());
		CaptureOutputStream os = new CaptureOutputStream();

		// when
		PrintStream ps = writer.createOrKeepPrintStream(os);
		ps.print("x");

		// then
		assertThat(ps, not(sameInstance(os)));
		assertThat(os.getCapture(), is("x"));
	}

	@Test
	public void printStreamIsTakenAsIs()
	{
		// given
		ObjWriter writer = new ObjWriter(Collections.emptyList());
		PrintStream ps = new PrintStream(new CaptureOutputStream());

		// when
		PrintStream result = writer.createOrKeepPrintStream(ps);

		// then
		assertThat(result, sameInstance(ps));
	}
}
