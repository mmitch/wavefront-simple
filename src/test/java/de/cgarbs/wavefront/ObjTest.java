/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.cgarbs.wavefront.meta.ArgSupplier;
import de.cgarbs.wavefront.op.Translation;
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
		obj.writeTo(new NullOutputStream());

		// then
		assertThat(captureList, hasSize(1));
	}

	@Test
	public void operationsGetAppliedToEveryFace()
	{
		// given
		Face face1 = new Face(//
				new V(1, 2, 3), //
				new V(2, 6, 8), //
				new V(-10, 4, -3) //
		);
		Face face2 = new Face(//
				new V(-1, -2, 3), //
				new V(2, -6, -8), //
				new V(10, 4, 3) //
		);

		Obj obj = new Obj();
		obj.addFace(face1);
		obj.addFace(face2);

		Translation tl = new Translation(new Vec(0.5, 1, 2));

		// when
		Obj newObj = obj.apply(tl);

		// then
		assertThat(obj.faces.get(0), is(face1));
		assertThat(obj.faces.get(1), is(face2));
		assertThat(newObj.faces.get(0), is(face1.apply(tl)));
		assertThat(newObj.faces.get(1), is(face2.apply(tl)));
	}

	@Test
	public void applyCreatesCopyAndDoesNotChangeOriginalObject()
	{
		// given
		Face face = new Face(//
				new V(1, 2, 3), //
				new V(2, -6, -8), //
				new V(-10, 4, -3) //
		);

		Obj obj = new Obj();
		obj.addFace(face);

		Translation tl = new Translation(new Vec(0.5, 1, 2));

		// when
		Obj newObj = obj.apply(tl);

		// then
		assertThat(obj.faces.get(0), sameInstance(face));
		assertThat(newObj, not(sameInstance(obj)));
		assertThat(newObj.faces.get(0), not(sameInstance(face)));
	}

	@Test
	public void boundingBoxReturnsSmallestAndGreatestCoordinatesOfAllFaces()
	{
		Face face1 = new Face(//
				new V(1, 2, 3), //
				new V(2, 6, 8), //
				new V(-10, 4, -3) //
		);
		Face face2 = new Face(//
				new V(-1, -2, 3), //
				new V(2, -6, -8), //
				new V(10, 4, 3) //
		);

		Obj obj = new Obj();
		obj.addFace(face1);
		obj.addFace(face2);

		BoundingBox bb = obj.getBoundingBox();

		assertThat(bb.getFirst(), is(new Coordinate(-10, -6, -8)));
		assertThat(bb.getSecond(), is(new Coordinate(10, 6, 8)));
	}

	ArgSupplier<ObjWriter, List<Face>> getMockedSupplier(final List<Face> captureList)
	{
		return (arg) -> {
			captureList.addAll(arg);
			return mock(ObjWriter.class);
		};
	};
}
