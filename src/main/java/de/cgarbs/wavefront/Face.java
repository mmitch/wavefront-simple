/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * A face is a surface bounded by its vertices.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.4.0
 *
 */
public class Face extends Container<Face, V>
{

	/**
	 * Creates a new face bounded by the given vertices. At least
	 * three vertices must be present.
	 * 
	 * @param v1
	 *            first vertex
	 * @param v2
	 *            second vertex
	 * @param v3
	 *            third vertex
	 * @param additionalVertices
	 *            additional vertices
	 * @since 0.4.0
	 */
	public Face(V v1, V v2, V v3, V... additionalVertices)
	{
		this(asList(v1, v2, v3, additionalVertices));
	}

	private Face(List<V> vertices)
	{
		super("F", vertices);
	}

	/**
	 * returns a Stream of the Face's vertices
	 * 
	 * @return the vertices of this Face
	 */
	public Stream<V> vertices()
	{
		return stream();
	}

	@Override
	protected Face getInstance(List<V> data)
	{
		return new Face(data);
	}

	private static List<V> asList(V v1, V v2, V v3, V... additionalVertices)
	{
		List<V> vertices = new ArrayList<>();
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.addAll(Arrays.asList(additionalVertices));
		return vertices;
	}

}
