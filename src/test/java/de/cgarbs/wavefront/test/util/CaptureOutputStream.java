/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.test.util;

import java.io.IOException;
import java.io.OutputStream;

public class CaptureOutputStream extends OutputStream {

	StringBuilder capture = new StringBuilder();
	
	@Override
	public void write(int b) throws IOException {
		capture.append((char) b); 
	}

	public String getCapture() {
		return capture.toString();
	}
}