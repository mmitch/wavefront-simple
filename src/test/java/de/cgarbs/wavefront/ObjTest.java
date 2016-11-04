/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.io.PrintStream;

import org.junit.Test;

import de.cgarbs.wavefront.test.util.CaptureOutputStream;

public class ObjTest {

	@Test
	public void faceIsConvertedToVertices() {
		Obj obj = new Obj();
		obj.addFace(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0));
		assertThat(obj.getVertices(), hasSize(3));
		assertThat(obj.getFaces(), hasSize(1));
	}

	@Test
	public void duplicateVertexIsRemoved() {
		Obj obj = new Obj();
		obj.addFace(new V(1, 1, 1), new V(2, 2, 2), new V(1, 1, 1));
		assertThat(obj.getVertices(), hasSize(2));
		assertThat(obj.getFaces(), hasSize(1));
	}

	@Test
	public void checkTriangleFile() {
		Obj obj = new Obj();
		obj.addFace(new V(0,0,1), new V(0, 1,0), new V(1,0,0));
		CaptureOutputStream os = new CaptureOutputStream();
		obj.writeTo(new PrintStream(os));
		assertThat(os.getCapture(), is( //
				"V 0 0 1\n" + //
				"V 0 1 0\n" + //
				"V 1 0 0\n" + //
				"f 1 2 3\n" //
				));
	}
}
