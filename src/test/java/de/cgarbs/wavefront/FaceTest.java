/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class FaceTest
{
	@Test
	public void translationMovesAllVertices()
	{
		Face f = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0));
		f.translate(new Vec(0.25, -0.5, 0.125));
		assertThat(f, is(new Face(new V(0.25, -0.5, 0.125), new V(0.25, 0.5, 1.125), new V(2.25, -0.5, 0.125))));
	}

	@Test
	public void facesWithDifferentNumbersOfVerticesAreNotEqual()
	{
		Face f1 = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0));
		Face f2 = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0), new V(3, 3, 0));
		assertThat(f1, not(f2));
	}

	@Test
	public void facesWithDifferentVerticesAreNotEqual()
	{
		Face f1 = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0));
		Face f2 = new Face(new V(0, 0, 0), new V(1, 0, 0), new V(0, 2, 2));
		assertThat(f1, not(f2));
	}

	@Test
	public void facesWithVerticesInDifferentOrderAreEqual()
	{
		Face f1 = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0));
		Face f2 = new Face(new V(0, 0, 0), new V(2, 0, 0), new V(0, 1, 1));
		assertThat(f1, is(f2));
	}

	@Test
	public void compareToSortsByVertexCoundFollowedBySortedVertices()
	{
		Face f1 = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0), new V(3, 3, 3));
		Face f2 = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0));
		Face f3 = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0), new V(3, 0, 0));
		Face f4 = new Face(new V(0, 0, 0), new V(2, 0, 0), new V(0, 1, 2));
		Face[] arr = new Face[] { f1, f2, f3, f4 };
		Arrays.sort(arr);
		assertThat(arr, arrayContaining(f2, f4, f3, f1));
	}
}
