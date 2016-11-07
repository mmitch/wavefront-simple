package de.cgarbs.wavefront;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.collection.IsArrayContainingInOrder.arrayContaining;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

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
	public void sortsByColumn()
	{
		V v1 = new V(2, 2, 1);
		V v2 = new V(0, 5, 2);
		V v3 = new V(1, 1, 2);
		V v4 = new V(0, 2, 3);
		V[] arr = new V[] { v1, v2, v3, v4 };
		Arrays.sort(arr);
		assertThat(arr, arrayContaining(v4, v2, v3, v1));
	}
}
