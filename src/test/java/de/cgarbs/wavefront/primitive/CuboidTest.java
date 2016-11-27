/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.primitive;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;

import de.cgarbs.wavefront.Coordinate;
import de.cgarbs.wavefront.Face;
import de.cgarbs.wavefront.Obj;
import de.cgarbs.wavefront.V;
import de.cgarbs.wavefront.Vec;

public class CuboidTest
{
	@Test
	public void cuboidCreates6FacewWith8Vertices()
	{
		Cuboid c = new Cuboid(new Coordinate(0, 0, 0), new Coordinate(2, 3, 4));

		Obj expected = new Obj( //
				new Face(new V(0, 0, 0), new V(0, 0, 4), new V(0, 3, 4), new V(0, 3, 0)), //
				new Face(new V(2, 0, 0), new V(2, 0, 4), new V(2, 3, 4), new V(2, 3, 0)), //

				new Face(new V(0, 0, 0), new V(0, 0, 4), new V(2, 0, 4), new V(2, 0, 0)), //
				new Face(new V(0, 3, 0), new V(0, 3, 4), new V(2, 3, 4), new V(2, 3, 0)), //

				new Face(new V(0, 0, 0), new V(0, 3, 0), new V(2, 3, 0), new V(2, 0, 0)), //
				new Face(new V(0, 0, 4), new V(0, 3, 4), new V(2, 3, 4), new V(2, 0, 4)) //
		);

		Set<V> uniqueVertices = c.faces().stream() //
				.flatMap(Face::vertices) //
				.collect(Collectors.toSet());

		assertThat(c.faces(), hasSize(6));
		assertThat(uniqueVertices, hasSize(8));
	}

	@Test
	public void cuboidhasExpectedVertices()
	{
		Cuboid c = new Cuboid(new Coordinate(0, 0, 0), new Coordinate(2, 3, 4));

		Obj expected = new Obj( //
				new Face(new V(0, 0, 0), new V(0, 0, 4), new V(0, 3, 4), new V(0, 3, 0)), //
				new Face(new V(2, 0, 0), new V(2, 0, 4), new V(2, 3, 4), new V(2, 3, 0)), //

				new Face(new V(0, 0, 0), new V(0, 0, 4), new V(2, 0, 4), new V(2, 0, 0)), //
				new Face(new V(0, 3, 0), new V(0, 3, 4), new V(2, 3, 4), new V(2, 3, 0)), //

				new Face(new V(0, 0, 0), new V(0, 3, 0), new V(2, 3, 0), new V(2, 0, 0)), //
				new Face(new V(0, 0, 4), new V(0, 3, 4), new V(2, 3, 4), new V(2, 0, 4)) //
		);

		assertThat(c, is(expected));
	}

	@Test
	public void cuboidWithCoordinateAndVectorIsEqualToTwoCoordinateVersion()
	{
		Cuboid cv = new Cuboid(new Coordinate(2, 3, 4), new Vec(-5, -7, -9));
		Cuboid cc = new Cuboid(new Coordinate(-3, -4, -5), new Coordinate(2, 3, 4));

		assertThat(cv, is(cc));
	}
}
