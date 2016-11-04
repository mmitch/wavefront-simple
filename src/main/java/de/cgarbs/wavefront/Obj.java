/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * minimal Wavefront .obj file writer
 * 
 * This allows you to write files describing 3D models.
 * Viewer not included.
 * 
 * See file format description at https://en.wikipedia.org/wiki/Wavefront_.obj_file}
 * 
 * @author Christian Garbs <mitch@cgarbs.de>
 * @since 0.1.0
 *
 */
public class Obj {

	List<Face> faces = new ArrayList<>();
	List<V> vertices = new ArrayList<>();

	/**
	 * Adds a face to the object.
	 * 
	 * @param vertices the vertices of the face
	 * @since 0.1.0
	 */
	public void addFace(V... vertices) {
		int[] vIndices = Stream.of(vertices) //
				.mapToInt((v) -> storeAndGetIndex(v)) //
				.toArray();
		faces.add(new Face(vIndices));
	}

	/**
	 * Writes the .obj file to a PrintStream.
	 * Does not close the stream after writing.
	 *  
	 * @param ps the PrintStream to write to
	 * @since 0.1.0
	 */
	public void writeTo(PrintStream ps) {
		writeVertices(ps);
		writeFaces(ps);
	}

	List<V> getVertices() {
		return Collections.unmodifiableList(vertices);
	}

	List<Face> getFaces() {
		return Collections.unmodifiableList(faces);
	}

	private int storeAndGetIndex(V vertex) {
		int index = vertices.indexOf(vertex);
		if (index >= 0) {
			return index + 1;
		}
		vertices.add(vertex);
		return vertices.size();
	}

	private void writeFaces(PrintStream ps) {
		faces.stream() //
				.map( //
						(f) -> f.vertices() //
							.mapToObj(String::valueOf) //
							.collect(Collectors.joining(" "))
				)//
				.forEach((s) -> ps.printf("f %s\n", s));
	}

	private void writeVertices(PrintStream ps) {
		Formatter formatter = new Formatter(Locale.ENGLISH);
		vertices.forEach((v) -> formatter.format("V %.0f %.0f %.0f\n", v.x, v.y, v.z));
		ps.print(formatter.toString());
		formatter.close();
	}

}
