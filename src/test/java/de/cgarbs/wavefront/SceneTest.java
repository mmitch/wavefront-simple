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
import de.cgarbs.wavefront.test.util.NullOutputStream;

public class SceneTest
{
	@Test
	public void writingToFilePassesFacesToWriter()
	{
		// given
		Face face = new Face(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0));
		Scene scene = new Scene(new Obj(face));
		List<Face> captureList = new ArrayList<>();
		scene.objWriterSupplier = getMockedSupplier(captureList);

		// when
		scene.writeTo(new NullOutputStream());

		// then
		assertThat(captureList, hasSize(1));
		assertThat(captureList.get(0), is(face));
	}

	@Test
	public void addFaceCreatesCopyAndDoesNotChangeOriginalObject()
	{
		// given
		Obj obj = new Obj(new Face(//
				new V(1, 2, 3), //
				new V(2, -6, -8), //
				new V(-10, 4, -3) //
		));

		Scene scene = new Scene();

		// when
		Scene newScene = scene.addObject(obj);

		// then
		assertThat(scene.stream().count(), is(0L));
		assertThat(newScene, not(sameInstance(scene)));
		assertThat(getScene(newScene, 0), is(obj));
	}

	@Test
	public void toStringReportsMultipleLines()
	{
		Obj obj1 = new Obj(new Face(//
				new V(-1, 2, 3), //
				new V(-2, -3, -8), //
				new V(-10, 4, 9) //
		));
		Obj obj2 = new Obj(new Face(//
				new V(1, 5, 6), //
				new V(2, -5, 0), //
				new V(10, 5, -7) //
		));

		Scene scene = new Scene(obj1, obj2);

		assertThat(scene.toString(),
				is( //
						"S{\n" + //
								"  " + obj1.toString() + "\n" + //
								"  " + obj2.toString() + "\n" + //
								"}"));
	}

	ArgSupplier<ObjWriter, List<Face>> getMockedSupplier(final List<Face> captureList)
	{
		return (arg) -> {
			captureList.addAll(arg);
			return mock(ObjWriter.class);
		};
	};

	private static Obj getScene(Scene s, int i)
	{
		return s.stream().collect(Collectors.toList()).get(i);
	}
}
