/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class VecTest
{
	@Test
	public void vectorOfTwoPointsIsTheirDifference()
	{
		Coordinate start = new Coordinate(1, 20, 300);
		Coordinate end = new Coordinate(11, 220, 3300);
		Vec difference = new Vec(start, end);
		assertThat(difference, is(new Vec(10, 200, 3000)));
	}
}
