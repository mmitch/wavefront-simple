/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static java.util.stream.Collectors.toList;

import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import de.cgarbs.wavefront.meta.ArgSupplier;

/**
 * Represents a 3D Object consisting of multiple {@link Face}s.
 * 
 * An Object can be written to a file in Wavefront.obj format
 * to be viewed with an external 3D model viewer.
 * 
 * See file format description at
 * https://en.wikipedia.org/wiki/Wavefront_.obj_file
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.1.0
 */
public class Obj extends Container<Obj, Face>
{
	ArgSupplier<ObjWriter, List<Face>> objWriterSupplier = ObjWriter::new;

	/**
	 * Creates an Object consisting of the given Faces.
	 * 
	 * @param faces
	 *            the Faces of the Object
	 * @since 0.6.0
	 */
	public Obj(Face... faces)
	{
		this(Arrays.asList(faces));
	}

	/**
	 * Creates an Object consisting of the given Faces.
	 * 
	 * @param faces
	 *            the Faces of the Object
	 * @since 0.6.0
	 */
	public Obj(List<Face> faces)
	{
		super("O", faces);
	}

	/**
	 * Creates a copy of this Object with an additional Face.
	 * 
	 * @param face
	 *            the face
	 * @return the new Obj with the additional Face
	 * @since 0.6.0
	 */
	public Obj addFace(Face face)
	{
		return getInstance(Stream //
				.concat(stream(), Stream.of(face)) //
				.collect(toList()));
	}

	/**
	 * Creates a copy of this Object with an additional Face.
	 * Convenience method to be called with at least three Vertices.
	 * 
	 * 
	 * @param v1
	 *            first vertex
	 * @param v2
	 *            second vertex
	 * @param v3
	 *            third vertex
	 * @param additionalVertices
	 *            possible further vertices of the face
	 * @return the new Obj with the additional Face
	 * @since 0.6.0
	 */
	public Obj addFace(V v1, V v2, V v3, V... additionalVertices)
	{
		return addFace(new Face(v1, v2, v3, additionalVertices));
	}

	/**
	 * Writes the .obj file to an OutputStream.
	 * Does not close the stream after writing.
	 * 
	 * See file format description at
	 * https://en.wikipedia.org/wiki/Wavefront_.obj_file
	 * 
	 * @param os
	 *            the OutputStream to write to
	 * @since 0.1.0
	 */
	public void writeTo(OutputStream os)
	{
		objWriterSupplier.get(stream().collect(toList())).writeTo(os);
	}

	@Override
	protected Obj getInstance(List<Face> faces)
	{
		return new Obj(faces);
	}

}
