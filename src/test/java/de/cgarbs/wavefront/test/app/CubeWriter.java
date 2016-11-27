/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.test.app;

import de.cgarbs.wavefront.Coordinate;
import de.cgarbs.wavefront.primitive.Cube;

public class CubeWriter
{
	public static void main(String[] args)
	{
		// generate a cube at (0, 0, 0) with edge length 1
		Cube cube = new Cube(Coordinate.ORIGIN, 1);

		// write object to sysout
		cube.writeTo(System.out);
	}
}
