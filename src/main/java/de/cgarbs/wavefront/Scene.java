/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import de.cgarbs.wavefront.io.Writable;

/**
 * Represents a 3D Scene consisting of multiple {@link Obj}ects.
 * 
 * A Scene can be written to a file in Wavefront.obj format
 * to be viewed with an external 3D model viewer.
 * 
 * See file format description at
 * https://en.wikipedia.org/wiki/Wavefront_.obj_file
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.6.0
 */
public class Scene extends Container<Scene, Obj> implements Writable
{
	private static final String SHORTHAND = "S";

	/**
	 * Creates a Scene consisting of the given Objects.
	 * 
	 * @param objects
	 *            the Objects of the Scene
	 * @since 0.6.0
	 */
	public Scene(Obj... objects)
	{
		this(Arrays.asList(objects));
	}

	/**
	 * Creates a Scene consisting of the given Objects.
	 * 
	 * @param objects
	 *            the Objects of the Scene
	 * @since 0.6.0
	 */
	public Scene(List<Obj> objects)
	{
		super(SHORTHAND, objects);
	}

	/**
	 * Creates a copy of this Scene with an additional Object.
	 * 
	 * @param object
	 *            the Object
	 * @return the new Scene with the additional Object
	 * @since 0.6.0
	 */
	public Scene addObject(Obj object)
	{
		return getInstance(Stream //
				.concat(stream(), Stream.of(object)) //
				.collect(toList()));
	}

	@Override
	protected Scene getInstance(List<Obj> objects)
	{
		return new Scene(objects);
	}

	@Override
	public String toString()
	{
		return stream() //
				.map(Obj::toString) //
				.collect(Collectors.joining("\n  ", SHORTHAND + "{\n  ", "\n}"));
	}

	@Override
	public List<Face> faces()
	{
		return stream() //
				.flatMap(Obj::stream) //
				.collect(toList());
	}

}
