/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class ContainerTest
{
	@Test
	public void differentEmptyContainerTypesAreNotEqual()
	{
		Obj obj = new Obj();
		Scene scene = new Scene();

		assertThat(obj, not(scene));
	}

	@Test
	public void containerComparesEqualToItself()
	{
		Obj obj = new Obj(new Face(new V(1, 2, 3), new V(2, 4, 6), new V(-1, -1, 0)));

		assertThat(obj.compareTo(obj), is(0));
	}
}
