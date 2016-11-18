/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class CenterableTest
{
	@Test
	public void centerOnOriginCentersOnOrigin()
	{
		Face face = new Face( //
				new V(-12, -10, -10), //
				new V(-10, -12, -10), //
				new V(-10, -10, -12) //
		);

		Face centeredFace = face.centerOnOrigin();

		assertThat(//
				centeredFace,
				is(new Face( //
						new V(-1, 1, 1), //
						new V(1, -1, 1), //
						new V(1, 1, -1) //
				)));
	}
}
