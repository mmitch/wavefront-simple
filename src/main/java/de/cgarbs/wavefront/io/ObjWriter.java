package de.cgarbs.wavefront.io;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import de.cgarbs.wavefront.Face;
import de.cgarbs.wavefront.V;

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
		PrintStream ps = createOrKeepPrintStream(os);
		writeVertices(ps);
		writeFaces(ps);

	}

	PrintStream createOrKeepPrintStream(OutputStream os)
	{
		if (os instanceof PrintStream)
		{
			return (PrintStream) os;
		}
		return new PrintStream(os);
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
		uniqueVertices.forEach((v) -> formatter.format("V %.0f %.0f %.0f\n", v.getX(), v.getY(), v.getZ()));
		ps.print(formatter.toString());
		formatter.close();
	}

	private void writeFaces(PrintStream ps)
	{
		faces.stream() //
				.map( //
						(face) -> face.vertices() //
								.map((vertex) -> uniqueVertices.indexOf(vertex) + 1) //
								.map(String::valueOf) //
								.collect(Collectors.joining(" "))) //
				.sorted() //
				.forEach((vertices) -> ps.printf("f %s\n", vertices));
	}
}
