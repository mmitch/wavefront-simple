/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.cgarbs.wavefront.Coordinate;
import de.cgarbs.wavefront.V;

public class FindGreatestTest
{
	@Test
	public void maximumOfTWoVerticesIsTheMaximumOfEachCoordinate()
	{
		FindGreatest accumulator = new FindGreatest();
		new V(1, -2, 4).apply(accumulator);
		new V(3, -1, -1).apply(accumulator);
		assertThat(accumulator.getResult(), is(new Coordinate(3, -1, 4)));
	}
}
