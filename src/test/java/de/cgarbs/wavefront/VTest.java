package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.Test;

public class VTest
{
	@Test
	public void sameValuesAreEqual()
	{
		V v1 = new V(1, 2, 3);
		V v2 = new V(1, 2, 3);
		assertThat(v1, is(v2));
	}

	@Test
	public void sameInstancesAreEqual()
	{
		V v = new V(1, 2, 3);
		assertThat(v, is(v));
	}

	@Test
	public void differentValuesAreNotEqual()
	{
		V v1 = new V(1, 2, 3);
		V v2 = new V(3, 2, 1);
		assertThat(v1, not(v2));
	}

	@Test
	public void sortSmallestCoordinatesFirst()
	{
		V v1 = new V(1, 1, 1);
		V v2 = new V(-1, -1, -1);
		V v3 = new V(-1, 0, 0);
		V v4 = new V(0, 1, 0);
		V v5 = new V(0, 1, -1);

		List<V> vertices = Arrays.asList(v1, v2, v3, v4, v5);
		vertices.sort(Comparator.naturalOrder());

		assertThat(vertices, is(Arrays.asList(v2, v3, v5, v4, v1)));
	}

	@Test
	public void vertexCanBeCreatedAtGivenCoordinate()
	{
		Coordinate c = new Coordinate(1, 2, 3);
		V v = new V(c);
		assertThat(v, is(new V(1, 2, 3)));
	}
}
