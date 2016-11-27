/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.junit.Test;

public class SceneTest
{
	@Test
	public void facesContainsAllFaces()
	{
		Face face1 = new Face(new V(0, 0, 1), new V(0, 1, 0), new V(1, 0, 0));
		Face face2 = new Face(new V(4, 4, 1), new V(0, 1, 3), new V(1, 2, 0));
		Face face3 = new Face(new V(-5, 4, 2), new V(0, 0, 0), new V(-7, 4, 0));

		Obj obj1 = new Obj(face1);
		Obj obj2 = new Obj(face2, face3);

		Scene scene = new Scene(obj1, obj2);

		assertThat(scene.faces(), is(Arrays.asList(face1, face2, face3)));
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

	private static Obj getScene(Scene s, int i)
	{
		return s.stream().collect(Collectors.toList()).get(i);
	}
}
