/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import de.cgarbs.wavefront.meta.ArgSupplier;
import de.cgarbs.wavefront.op.FindGreatest;
import de.cgarbs.wavefront.op.FindSmallest;
import de.cgarbs.wavefront.op.Operable;
import de.cgarbs.wavefront.op.Operation;
import de.cgarbs.wavefront.op.Program;

/**
 * minimal Wavefront .obj file writer
 * 
 * This allows you to write files describing 3D models.
 * Viewer not included.
 * 
 * See file format description at
 * https://en.wikipedia.org/wiki/Wavefront_.obj_file}
 * 
 * This class represents an Object/a full Scene.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.1.0
 *
 */
public class Obj implements Operable<Obj>, HasBoundingBox, Centerable<Obj>
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
	 *            possible further vertices of the face
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

	@Override
	public Obj apply(Operation operation)
	{
		Obj ret = new Obj();
		faces.stream() //
				.map((f) -> f.apply(operation)) //
				.forEach(ret::addFace);
		return ret;
	}

	@Override
	public BoundingBox getBoundingBox()
	{
		FindSmallest smallest = new FindSmallest();
		FindGreatest greatest = new FindGreatest();
		Program program = new Program(smallest, greatest);

		faces.stream().map(Face::getBoundingBox).flatMap(BoundingBox::stream).forEach((c) -> c.apply(program));

		return new BoundingBox( //
				smallest.getResult(), //
				greatest.getResult() //
		);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (!(o instanceof Obj))
		{
			return false;
		}
		Obj obj = (Obj) o;
		if (this.faces.size() != obj.faces.size())
		{
			return false;
		}
		Iterator<Face> ours = this.faces.stream().sorted().iterator();
		Iterator<Face> theirs = obj.faces.stream().sorted().iterator();
		while (ours.hasNext())
		{
			if (!ours.next().equals(theirs.next()))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString()
	{
		return faces.stream() //
				.map(Face::toString) //
				.collect(Collectors.joining(" ", "O{", "}"));
	}

}
