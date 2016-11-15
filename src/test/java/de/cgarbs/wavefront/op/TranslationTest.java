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
import de.cgarbs.wavefront.Vec;

public class TranslationTest
{
	@Test
	public void translatingAVertexMovesItToOtherCoordinates()
	{
		V vOld = new V(1, 2, 3);
		V vNew = vOld.apply(new Translation(new Vec(0.5, -0.25, 0.125)));
		assertThat(vNew, not(sameInstance(vOld)));
		assertThat(vNew, is(new V(1.5, 1.75, 3.125)));
	}

}
