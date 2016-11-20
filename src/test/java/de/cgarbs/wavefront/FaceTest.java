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

import de.cgarbs.wavefront.op.Translation;

public class FaceTest
{
	@Test
	public void appliedOperationsWorkOnAllVertices()
	{
		Face f = new Face(new V(0, 0, 0), new V(0, 1, 1), new V(2, 0, 0));
		Face fNew = f.apply(new Translation(new Vec(0.25, -0.5, 0.125)));
		assertThat(fNew, is(new Face(new V(0.25, -0.5, 0.125), new V(0.25, 0.5, 1.125), new V(2.25, -0.5, 0.125))));
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
	public void faceIsNeverEqualToAnotherClass()
	{
		Face face = new Face(new V(1, 2, 3), new V(-1, -2, -3), new V(-3, 3, 0));
		assertThat(face, not(new V(1, 2, 3)));
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

	@Test
	public void boundingBoxIstToSmallestAndGreatestCoordinates()
	{
		Face f = new Face(new V(1, 3, 9), new V(-2, -17, 12), new V(0, 18, -1));

		BoundingBox bb = f.getBoundingBox();

		assertThat(bb.getFirst(), is(new Coordinate(-2, -17, -1)));
		assertThat(bb.getSecond(), is(new Coordinate(1, 18, 12)));
	}

	@Test
	public void toStringContainsAllVertices()
	{
		V v1 = new V(1, 2, 3);
		V v2 = new V(-2, 5, 7);
		V v3 = new V(-1, 4, -2);
		V v4 = new V(0, 3, -2);

		Face f = new Face(v1, v2, v3, v4);

		assertThat(f.toString(), is(String.format("F{%s %s %s %s}", v1, v2, v3, v4)));
	}

}
