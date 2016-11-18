/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BoundingBoxTest
{
	@Test
	public void centerIsHalfwayBetweenBothCoordinates()
	{
		BoundingBox bb = new BoundingBox( //
				new Coordinate(3, -4, 9), //
				new Coordinate(7, 3, 4) //
		);
		assertThat(bb.getCenter(), is(new Coordinate(5, -0.5, 6.5)));
	}

	@Test
	public void toStringContainsBothCoordinates()
	{
		Coordinate c1 = new Coordinate(0, 1, 2);
		Coordinate c2 = new Coordinate(-1, -10, -100);
		BoundingBox bb = new BoundingBox(c1, c2);

		assertThat(bb.toString(), is("BB{" + c1 + " " + c2 + "}"));
	}
}
