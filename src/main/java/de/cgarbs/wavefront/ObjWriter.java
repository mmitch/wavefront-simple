package de.cgarbs.wavefront;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

class ObjWriter
{
	final List<V> uniqueVertices;
	final List<Face> faces;

	public ObjWriter(List<Face> faces)
	{
		this.faces = faces;
		this.uniqueVertices = getUniqueVertices();
	}

	public void writeTo(OutputStream os)
	{
		PrintStream ps = new PrintStream(os);
		writeVertices(ps);
		writeFaces(ps);

	}

	private List<V> getUniqueVertices()
	{
		return faces.stream() //
				.flatMap(Face::vertices) //
				.sorted() //
				.distinct() //
				.collect(Collectors.toList());
	}

	private void writeVertices(PrintStream ps)
	{
		Formatter formatter = new Formatter(Locale.ENGLISH);
		uniqueVertices.forEach((v) -> formatter.format("V %.0f %.0f %.0f\n", v.x, v.y, v.z));
		ps.print(formatter.toString());
		formatter.close();
	}

	private void writeFaces(PrintStream ps)
	{
		faces.stream() //
				.map( //
						(f) -> f.vertices() //
								.map((face) -> uniqueVertices.indexOf(face) + 1) //
								.map(String::valueOf) //
								.collect(Collectors.joining(" ")))//
				.forEach((s) -> ps.printf("f %s\n", s));
	}
}
