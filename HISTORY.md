wavefront-simple release history
================================


0.6.0 - 2016/11/27
------------------

- make Obj immutable *[incompatible]*
    - addFace() returns a new instance, original instance stays unchanged
- add Scene consisting of multiple Objs


0.5.0 - 2016/11/26
------------------

- use BigDecimal internally instead of double
- add Cube and Cuboid primitives
- fix x coordinate in Triplet.compare()
- add Obj.equals() and Obj.toString()


0.4.0 - 2016/11/20
------------------

- Triplet.equals() now also compares shorthands *[incompatible]*
- Face class is now public


0.3.0 - 2016/11/20
------------------

- fix Javadoc that broke the build


0.3.0 - 2016/11/20
------------------

- Add Operations that can be applied to things.
  Operations work immutable and create a copy of the
  object operated on.
    - current Operations are:
		- Identity
		- Translation
		- Scale
		- FindGreatest
		- FindSmallest
	- current Operables (things supporting Operations) are:
		- Coordinate
		- CoordinatePair
		- Face
		- Obj
		- Vertex (V)
		- Vector (Vec)
- add Program to chain multiple Operations
- add plain Coordinates and CoordinatePairs
- a Vector can be constructed of two Coordinates or a
  Coordinate pair
- calculate Bounding Boxes of various things
- Face and Obj can be centered on the point of origin
  using their Bounding Boxes
- Face implements Comparable


0.2.0 - 2016/11/07
------------------

- Face now requires at least 3 vertices *[incompatible]*
- the .obj file can be written to any ``OutputStream``,
  no ``PrintStream`` needed any more


0.1.0 - 2016/11/04
------------------

- initial release
