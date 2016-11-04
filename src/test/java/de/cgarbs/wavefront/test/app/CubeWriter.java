/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.test.app;

import de.cgarbs.wavefront.Obj;
import de.cgarbs.wavefront.V;

public class CubeWriter {

	public static void main(String[] args) {
		Obj obj = new Obj();
		obj.addFace(new V(-1, -1, -1), new V(-1, 1, -1), new V(1, 1, -1), new V(1, -1, -1));
		obj.addFace(new V(-1, -1, 1), new V(-1, 1, 1), new V(1, 1, 1), new V(1, -1, 1));

		obj.addFace(new V(-1, -1, -1), new V(-1, -1, 1), new V(-1, 1, 1), new V(-1, 1, -1));
		obj.addFace(new V(1, -1, -1), new V(1, -1, 1), new V(1, 1, 1), new V(1, 1, -1));

		obj.addFace(new V(-1, -1, -1), new V(-1, -1, 1), new V(1, -1, 1), new V(1,-1, -1));
		obj.addFace(new V(-1, 1, -1), new V(-1, 1, 1), new V(1, 1, 1), new V(1,1, -1));

		obj.writeTo(System.out);
	}

}
