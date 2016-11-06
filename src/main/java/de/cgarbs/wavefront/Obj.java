/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.io.OutputStream;
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
	 * Adds a face to the object. Convenience method
	 * to be called with at least three vertices.
	 * 
	 * 
	 * @param v1
	 *            first vertex
	 * @param v2
	 *            second vertex
	 * @param v3
	 *            third vertex
	 * @param additionalVertices
	 *            the vertices of the face
	 * @since 0.1.0
	 */
	public void addFace(V v1, V v2, V v3, V... additionalVertices)
	{
		addFace(new Face(v1, v2, v3, additionalVertices));
	}

	/**
	 * Writes the .obj file to an OutputStream.
	 * Does not close the stream after writing.
	 * 
	 * @param os
	 *            the OutputStream to write to
	 * @since 0.1.0
	 */
	public void writeTo(OutputStream os)
	{
		objWriterSupplier.get(faces).writeTo(os);
	}

}
