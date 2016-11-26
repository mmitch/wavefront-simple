/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.primitive;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.cgarbs.wavefront.Coordinate;
import de.cgarbs.wavefront.Vec;

public class CubeTest
{
	@Test
	public void aCubeEqualsACuboidWithTheSameDimensions()
	{
		Cube cube = new Cube(Coordinate.ORIGIN, 1);

		Cuboid expected = new Cuboid(Coordinate.ORIGIN, new Vec(1, 1, 1));

		assertThat(cube, is(expected));
	}
}
