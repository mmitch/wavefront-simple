/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

// TODO: make class public, otherwise Obj.addFace(Face) is bogus
class Face
{

	private List<V> vertices = new ArrayList<>();

	public Face(V v1, V v2, V v3, V... additionalVertices)
	{
		vertices.add(v1);
		vertices.add(v2);
		vertices.add(v3);
		vertices.addAll(Arrays.asList(additionalVertices));
	}

	public Stream<V> vertices()
	{
		return vertices.stream();
	}
}
