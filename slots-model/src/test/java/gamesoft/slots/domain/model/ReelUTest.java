package gamesoft.slots.domain.model;

import static org.junit.Assert.*;

import java.util.List;

import gamesoft.slots.domain.model.Reel;
import gamesoft.slots.domain.model.Symbol;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
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
	private List<Symbol> testSymbols = TestHelper.loadSymbols();

	@Before
	public void setUp() throws Exception {
		reel = new Reel(0, testSymbols, null);
		ReflectionTestUtils.setFiled("rng", reel, mockRNG);;
	}

	@Test
	public void testRandomSymbol() {
		Mockito.when(mockRNG.nextInt(reel.numberOfSymbols())).thenReturn(37);
		
		Symbol expected = Symbol.newSymbol(2, "SYMBOL_2", 37);
		Symbol actual = reel.randomSymbol();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCount(){
		Symbol symbol = testSymbols.get(0);
		int expected = 10;
		int actual = reel.count(symbol);
		
		assertEquals(expected, actual);
	}
	

}
