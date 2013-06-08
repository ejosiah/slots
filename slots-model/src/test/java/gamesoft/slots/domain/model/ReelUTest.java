package gamesoft.slots.domain.model;

import static org.junit.Assert.*;
import gamesoft.slots.domain.model.Reel;
import gamesoft.slots.domain.model.Symbol;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import gamesoft.random.RandomNumberGenerator;

@RunWith(MockitoJUnitRunner.class)
public class ReelUTest {
	
	@Mock RandomNumberGenerator mockRNG;
	
	private Reel reel;

	@Before
	public void setUp() throws Exception {
		reel = new Reel(0, TestHelper.loadSymbols(), null);
		ReflectionTestUtils.setFiled("rng", reel, mockRNG);;
	}

	@Test
	public void testRandomSymbol() {
		Mockito.when(mockRNG.nextInt(reel.numberOfSymbols())).thenReturn(37);
		
		Symbol expected = Symbol.newSymbol(2, "SYMBOL_2", 37);
		Symbol actual = reel.randomSymbol();
		
		assertEquals(expected, actual);
	}

}
