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

public class ProgramTest
{
	@Test
	public void applyingMultipleOperationsAppliesEachInTurn()
	{
		Program program = new Program( //
				new Translation(new Vec(0, 0, 1)), //
				new Translation(new Vec(0, 1, 0)), //
				new Translation(new Vec(1, 0, 0)) //
		);

		V vOld = new V(1, 2, 3);
		V vNew = vOld.apply(program);
		assertThat(vNew, not(sameInstance(vOld)));
		assertThat(vNew, is(new V(2, 3, 4)));
	}
}
