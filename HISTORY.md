wavefront-simple release history
================================

0.3.0 - unreleased
------------------

- Add Operations that can be applied to things.
  Operations work immutable and create a copy of the
  object operated on.
  Current Operations are:
    - Identity
    - Translation
    - Scale
    - FindGreatest
    - FindSmallest
  Current Operables (things supporting Operations) are:
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
