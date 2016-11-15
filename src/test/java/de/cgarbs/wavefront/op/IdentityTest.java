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

public class IdentityTest
{
	@Test
	public void applyingTheIdentityFunctionChangesNothing()
	{
		V vOld = new V(1, 2, 3);
		V vNew = vOld.apply(new Identity());
		assertThat(vNew, not(sameInstance(vOld)));
		assertThat(vNew, is(vOld));
	}

}
