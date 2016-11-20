/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class TripletTest
{
	@Test
	public void tripletIsNeverEqualToAnotherClass()
	{
		Triplet triplet = new TestableTriplet(1, 2, 3);
		Coordinate coordinate = new Coordinate(1, 2, 3);
		assertThat(triplet, not(coordinate));
	}

	@Test
	public void tripletEqualsTripletWithSameCoordinates()
	{
		Triplet t1 = new TestableTriplet(1, 2, 3);
		Triplet t2 = new TestableTriplet(1, 2, 3);
		assertThat(t1, is(t2));
	}

	@Test
	public void tripletDoesNotEqualTripletWithDifferentXCoordinate()
	{
		Triplet t1 = new TestableTriplet(1, 2, 3);
		Triplet t2 = new TestableTriplet(-1, 2, 3);
		assertThat(t1, not(t2));
	}

	@Test
	public void tripletDoesNotEqualTripletWithDifferentYCoordinate()
	{
		Triplet t1 = new TestableTriplet(1, 2, 3);
		Triplet t2 = new TestableTriplet(1, -2, 3);
		assertThat(t1, not(t2));
	}

	@Test
	public void tripletDoesNotEqualTripletWithDifferentZCoordinate()
	{
		Triplet t1 = new TestableTriplet(1, 2, 3);
		Triplet t2 = new TestableTriplet(1, 2, -3);
		assertThat(t1, not(t2));
	}

	@Test
	public void tripletDoesNotEqualTripletWithDifferentShorthand()
	{
		Triplet t1 = new TestableTriplet(1, 2, 3, "a");
		Triplet t2 = new TestableTriplet(1, 2, 3, "bb");
		assertThat(t1, not(t2));
	}

	class TestableTriplet extends Triplet
	{

		protected TestableTriplet(double x, double y, double z)
		{
			super(x, y, z, "TT");
		}

		protected TestableTriplet(double x, double y, double z, String shorthand)
		{
			super(x, y, z, shorthand);
		}

	}
}
