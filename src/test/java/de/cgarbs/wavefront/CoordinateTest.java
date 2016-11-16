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

import de.cgarbs.wavefront.op.Translation;

public class CoordinateTest
{
	@Test
	public void applyingAnOperationCreatesImmutableCopy()
	{
		Coordinate cOld = new Coordinate(5, 3, 1);
		Coordinate cNew = cOld.apply(new Translation(new Vec(-1, -2, -3)));
		assertThat(cNew, not(sameInstance(cOld)));
		assertThat(cNew, is(new Coordinate(4, 1, -2)));
	}

}
