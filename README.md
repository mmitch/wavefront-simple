simple wavefront file writer
============================

[![Build Status](https://travis-ci.org/mmitch/wavefront-simple.svg?branch=master)](https://travis-ci.org/mmitch/wavefront-simple)
[![Coverage Status](https://codecov.io/github/mmitch/wavefront-simple/coverage.svg?branch=master)](https://codecov.io/github/mmitch/wavefront-simple?branch=master)
[![GPL 3+](https://img.shields.io/badge/license-GPL%203%2B-blue.svg)](http://www.gnu.org/licenses/gpl-3.0-standalone.html)


about
-----

This is a very simple writer for the [Wavefront .obj]
(https://en.wikipedia.org/wiki/Wavefront_.obj_file) file format
describing 3D models.

The project homepage is at https://github.com/mmitch/wavefront-simple


example
-------

This code
```java
		// generate a cube at (0, 0, 0) with edge length 1
		Cube cube = new Cube(Coordinate.ORIGIN, 1);
		// write object to file
		cube.writeTo(new FileOutputStream("/tmp/cube.obj"));
```
creates an `.obj` file that looks like this:
```
V 1 1 1
V 0 1 1
V 1 0 1
V 0 0 1
V 1 1 0
V 0 1 0
V 1 0 0
V 0 0 0
f 8 7 3 4
f 6 5 1 2
f 8 6 2 4
f 7 5 1 3
f 8 6 5 7
f 4 2 1 3
```


usage
-----

This is a library, no standalone program (although test executables
are available in the ``de.cgarbs.wavefront.test.app`` package).

To include ``wavefront-simple`` in your project, add this to your
``build.gradle``:

```Gradle
repositories {
    repositories {
        maven {
			name "cgarbs.de repo"
			url "https://www.cgarbs.de/maven2"
		}
	}
}

dependencies {
	compile 'de.cgarbs:wavefront-simple:0.4.0'
}
```

Note: You should propably update the version number (``0.4.0`` in the
example above) to the most current version (see the ``HISTORY`` file).

The same coordinates should work for Maven projects, too.


build
-----

Just run ``gradle``, the project will configure, compile and then run the checks.



dependencies
------------

 - Java 8
 - Gradle
 - further dependencies are fetched automatically, see ``build.gradle``


further plans
-------------

 - add Scene (like Obj, but containing multiple Objs)
 - add rotation operation(?)
 - add colors/textures(?)
 

copyright
---------

wavefront-simple - generate simple 3D models in Wavefront .obj file format  
Copyright (C) 2016  Christian Garbs <<mitch@cgarbs.de>>  
Licensed under GNU GPL v3 (or later)

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.
