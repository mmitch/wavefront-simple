/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// TODO: make class public, otherwise Obj.addFace(Face) is bogus
class Face
{

	private List<V> vertices;

	public Face(V... vertices)
	{
		// TODO: require at least 3 vertices
		this.vertices = Arrays.asList(vertices);
	}

	public Stream<V> vertices()
	{
		return vertices.stream();
	}
}
