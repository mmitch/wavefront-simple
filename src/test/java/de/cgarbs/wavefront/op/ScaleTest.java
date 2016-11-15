/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.op;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import de.cgarbs.wavefront.V;

public class ScaleTest
{
	@Test
	public void scalingAVertexMovesItToOtherCoordinates()
	{
		V vOld = new V(1, 2, 3);
		V vNew = vOld.apply(new Scale(0.25d));
		assertThat(vNew, not(sameInstance(vOld)));
		assertThat(vNew, is(new V(0.25d, 0.5d, 0.75d)));
	}
}
