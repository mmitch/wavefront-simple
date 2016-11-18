/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.cgarbs.wavefront.op.Identity;
import de.cgarbs.wavefront.op.Scale;

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

	@Test
	public void scalingAVectorEffectivelyMeansScalarMultiply()
	{
		Vec vec = new Vec(2, 3, 4);
		Vec result = vec.apply(new Scale(-2));
		assertThat(result, is(new Vec(-4, -6, -8)));
	}

	@Test
	public void applyingAnOperationCreatesImmutableCopy()
	{
		Vec vOld = new Vec(5, 3, 1);
		Vec vNew = vOld.apply(new Identity());
		assertThat(vNew, not(sameInstance(vOld)));
		assertThat(vNew, is(vOld));
	}

	@Test
	public void vectorOfCoordinatePairEqualsVectorOfTheCoordinates()
	{
		Coordinate start = new Coordinate(1, 2, 3);
		Coordinate end = new Coordinate(17, -33, 9);
		Vec viaPair = new Vec(new CoordinatePair(start, end));
		Vec viaCoordinates = new Vec(start, end);
		assertThat(viaPair, is(viaCoordinates));
	}
}
