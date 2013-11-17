package gamesoft.slots.domain.model.slot;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import gamesoft.slots.domain.model.slot.Weighted;
import gamesoft.slots.domain.model.slot.WeightedList;

import java.util.Arrays;

import lombok.Data;
import lombok.experimental.Accessors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import gamesoft.random.RandomNumberGenerator;

@RunWith(MockitoJUnitRunner.class)
public class WeightedListUTest {
	
	@Mock private RandomNumberGenerator rng;
	private Weighted weighted1 = new TestWeight(10L);
	private Weighted weighted2 = new TestWeight(25L);
	private Weighted weighted3 = new TestWeight(60L);
	
	private WeightedList<Weighted> weightedList;

	@Before
	public void setUp() throws Exception {
		weightedList = new WeightedList<>(Arrays.asList(weighted1
				, weighted2, weighted3), rng);
	}

	@Test
	public void testPick() {
		when(rng.nextLong(95L)).thenReturn(55L);
		
		Weighted expected = weighted3;
		Weighted actual = weightedList.pick();
		
		assertEquals(expected, actual);
	}

	@Data @Accessors(fluent=true)
	private static class TestWeight implements Weighted{
		private final Long weight;
	}

}
