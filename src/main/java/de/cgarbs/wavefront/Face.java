/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.util.stream.IntStream;

class Face {

	private int[] vNumbers;

	public Face(int[] vNumbers) {
		this.vNumbers = vNumbers;
	}

	public IntStream vertices() {
		return IntStream.of(vNumbers);
	}
}
