/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import de.cgarbs.wavefront.op.Scale;

public class CoordinatePairTest
{
	@Test
	public void coordinatePairCanIterateOverTheCoordinates()
	{
		Coordinate c1 = new Coordinate(-1, -2, -3);
		Coordinate c2 = new Coordinate(3, 5, 99);
		CoordinatePair pair = new CoordinatePair(c1, c2);

		Iterator<Coordinate> iter = pair.iterator();
		assertThat(iter.next(), is(c1));
		assertThat(iter.next(), is(c2));
		assertThat(iter.hasNext(), is(false));
	}

	@Test
	public void streamReturnsBothCoordinatesInOrder()
	{
		Coordinate c1 = new Coordinate(-1, -2, -3);
		Coordinate c2 = new Coordinate(4, 6, 9);
		CoordinatePair pair = new CoordinatePair(c1, c2);

		List<Coordinate> result = pair.stream().collect(Collectors.toList());
		assertThat(result, is(Arrays.asList(c1, c2)));
	}

	@Test
	public void applyingAnOperationAppliesToBothCoordinates()
	{
		Coordinate c1 = new Coordinate(1, 2, 3);
		Coordinate c2 = new Coordinate(-1, -10, -100);
		CoordinatePair pair = new CoordinatePair(c1, c2);

		CoordinatePair result = pair.apply(new Scale(2));

		assertThat(result.getFirst(), is(new Coordinate(2, 4, 6)));
		assertThat(result.getSecond(), is(new Coordinate(-2, -20, -200)));
	}

	@Test
	public void toStringContainsBothCoordinates()
	{
		Coordinate c1 = new Coordinate(0, 1, 2);
		Coordinate c2 = new Coordinate(-1, -10, -100);
		CoordinatePair pair = new CoordinatePair(c1, c2);

		assertThat(pair.toString(), is("CP{" + c1 + " " + c2 + "}"));
	}
}
