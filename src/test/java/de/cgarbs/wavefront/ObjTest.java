/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.cgarbs.wavefront.meta.ArgSupplier;
import de.cgarbs.wavefront.test.util.NullOutputStream;

public class ObjTest
{
	@Test
	public void writingToFilePassesFacesToWriter()
	{
		// given
		Obj obj = new Obj();
		List<Face> captureList = new ArrayList<>();
		obj.objWriterSupplier = getMockedSupplier(captureList);
		obj.addFace(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0));

		// when
		obj.writeTo(new PrintStream(new NullOutputStream()));

		// then
		assertThat(captureList, hasSize(1));
	}

	ArgSupplier<ObjWriter, List<Face>> getMockedSupplier(final List<Face> captureList)
	{
		return (arg) -> {
			captureList.addAll(arg);
			return mock(ObjWriter.class);
		};
	};
}
