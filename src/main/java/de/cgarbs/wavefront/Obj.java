/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import de.cgarbs.wavefront.meta.ArgSupplier;

/**
 * minimal Wavefront .obj file writer
 * 
 * This allows you to write files describing 3D models.
 * Viewer not included.
 * 
 * See file format description at
 * https://en.wikipedia.org/wiki/Wavefront_.obj_file}
 * 
 * @author Christian Garbs <mitch@cgarbs.de>
 * @since 0.1.0
 *
 */
public class Obj
{
	ArgSupplier<ObjWriter, List<Face>> objWriterSupplier = ObjWriter::new;

	List<Face> faces = new ArrayList<>();

	/**
	 * Adds a face to the object.
	 * 
	 * @param face
	 *            the face
	 * @since 0.2.0
	 */
	public void addFace(Face face)
	{
		faces.add(face);
	}

	/**
	 * Adds a face to the object. Convenience
	 * method to be called with vertices.
	 * 
	 * @param vertices
	 *            the vertices of the face
	 * @since 0.1.0
	 */
	public void addFace(V... vertices)
	{
		addFace(new Face(vertices));
	}

	/**
	 * Writes the .obj file to a PrintStream.
	 * Does not close the stream after writing.
	 * 
	 * @param ps
	 *            the PrintStream to write to
	 * @since 0.1.0
	 */
	public void writeTo(PrintStream ps)
	{
		// TODO: OutputStream is enough, build the PrintStream internally
		objWriterSupplier.get(faces).writeTo(ps);
	}

}
