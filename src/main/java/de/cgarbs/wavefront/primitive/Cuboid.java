/*
 * Copyright 2016 (C)  Christian Garbs <mitch@cgarbs.de>
 * Licensed under GNU GPL 3 (or later)
 */
package de.cgarbs.wavefront.primitive;

import java.math.BigDecimal;

import de.cgarbs.wavefront.Coordinate;
import de.cgarbs.wavefront.Face;
import de.cgarbs.wavefront.Obj;
import de.cgarbs.wavefront.V;
import de.cgarbs.wavefront.Vec;
import de.cgarbs.wavefront.op.Translation;

/**
 * A Cuboid is an Object with 8 Vertices and 6 Faces
 * where all angles are right angles.
 * 
 * @author Christian Garbs &lt;mitch@cgarbs.de&gt;
 * @since 0.5.0
 */
public class Cuboid extends Obj
{
	/**
	 * Generates a non-rotated cuboid (all edges and faces are parallel to the
	 * coordinate axes) from a given coordinate that is used as a Vertex and
	 * a space diagonal that points to the opposing Vertex.
	 * 
	 * @param coordinate
	 *            the base coordinate
	 * @param spaceDiagonal
	 *            a Vector pointing to the opposite Vertex
	 * @since 0.5.0
	 */
	public Cuboid(Coordinate coordinate, Vec spaceDiagonal)
	{
		this( //
				coordinate, //
				coordinate.apply(new Translation(spaceDiagonal)) //
		);
	}

	/**
	 * Generates a non-rotated cuboid (all edges and faces are parallel to the
	 * coordinate axes) with two opposite Vertices on the given Coordinates.
	 * 
	 * @param coordinate1
	 *            the first coordinate
	 * @param coordinate2
	 *            the second coordinate
	 * @since 0.5.0
	 */
	public Cuboid(Coordinate coordinate1, Coordinate coordinate2)
	{
		BigDecimal x1 = coordinate1.getX();
		BigDecimal y1 = coordinate1.getY();
		BigDecimal z1 = coordinate1.getZ();
		BigDecimal x2 = coordinate2.getX();
		BigDecimal y2 = coordinate2.getY();
		BigDecimal z2 = coordinate2.getZ();

		V leftBottomFront = new V(x1, y1, z1);
		V rightBottomFront = new V(x2, y1, z1);
		V leftTopFront = new V(x1, y2, z1);
		V rightTopFront = new V(x2, y2, z1);
		V leftBottomBack = new V(x1, y1, z2);
		V rightBottomBack = new V(x2, y1, z2);
		V leftTopBack = new V(x1, y2, z2);
		V rightTopBack = new V(x2, y2, z2);

		Face bottom = new Face(leftBottomFront, rightBottomFront, rightBottomBack, leftBottomBack);
		Face top = new Face(leftTopFront, rightTopFront, rightTopBack, leftTopBack);
		Face left = new Face(leftBottomFront, leftTopFront, leftTopBack, leftBottomBack);
		Face right = new Face(rightBottomFront, rightTopFront, rightTopBack, rightBottomBack);
		Face front = new Face(leftBottomFront, leftTopFront, rightTopFront, rightBottomFront);
		Face back = new Face(leftBottomBack, leftTopBack, rightTopBack, rightBottomBack);

		addFace(bottom);
		addFace(top);
		addFace(left);
		addFace(right);
		addFace(front);
		addFace(back);
	}

}
