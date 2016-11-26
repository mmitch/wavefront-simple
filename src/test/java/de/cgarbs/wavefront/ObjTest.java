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
import java.util.stream.Collectors;

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
		Obj obj = new Obj(new Face(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0)));
		List<Face> captureList = new ArrayList<>();
		obj.objWriterSupplier = getMockedSupplier(captureList);

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

		Obj obj = new Obj(face1, face2);

		Translation tl = new Translation(new Vec(0.5, 1, 2));

		// when
		Obj newObj = obj.apply(tl);

		// then
		assertThat(getFace(obj, 0), is(face1));
		assertThat(getFace(obj, 1), is(face2));
		assertThat(getFace(newObj, 0), is(face1.apply(tl)));
		assertThat(getFace(newObj, 1), is(face2.apply(tl)));
	}

	@Test
	public void addFaceCreatesCopyAndDoesNotChangeOriginalObject()
	{
		// given
		Face face = new Face(//
				new V(1, 2, 3), //
				new V(2, -6, -8), //
				new V(-10, 4, -3) //
		);

		Obj obj = new Obj();

		// when
		Obj newObj = obj.addFace(face);

		// then
		assertThat(obj.stream().count(), is(0L));
		assertThat(newObj, not(sameInstance(obj)));
		assertThat(getFace(newObj, 0), is(face));
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

		Obj obj = new Obj(face);

		Translation tl = new Translation(new Vec(0.5, 1, 2));

		// when
		Obj newObj = obj.apply(tl);

		// then
		assertThat(getFace(obj, 0), sameInstance(face));
		assertThat(newObj, not(sameInstance(obj)));
		assertThat(getFace(newObj, 0), not(sameInstance(face)));
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

		Obj obj = new Obj(face1, face2);

		BoundingBox bb = obj.getBoundingBox();

		assertThat(bb.getFirst(), is(new Coordinate(-10, -6, -8)));
		assertThat(bb.getSecond(), is(new Coordinate(10, 6, 8)));
	}

	@Test
	public void objsWithDifferentFacesAreNotEqual()
	{
		Obj obj1 = new Obj( //
				new Face(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0)) //
		);

		Obj obj2 = new Obj( //
				new Face(new V(1, 1, 1), new V(0, 1, 0), new V(1, 0, 0)), //
				new Face(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0)) //
		);

		assertThat(obj1, not(obj2));
	}

	@Test
	public void objsWithEqualFacesAreEqual()
	{
		Obj obj1 = new Obj(new Face(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0)));

		Obj obj2 = new Obj(new Face(new V(1, 0, 0), new V(0, 1, 0), new V(0, 0, 1)));

		assertThat(obj1, is(obj2));
	}

	ArgSupplier<ObjWriter, List<Face>> getMockedSupplier(final List<Face> captureList)
	{
		return (arg) -> {
			captureList.addAll(arg);
			return mock(ObjWriter.class);
		};
	};

	private static Face getFace(Obj o, int i)
	{
		return o.stream().collect(Collectors.toList()).get(i);
	}
}
