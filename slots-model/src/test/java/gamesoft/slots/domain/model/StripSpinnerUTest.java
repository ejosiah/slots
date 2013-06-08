package gamesoft.slots.domain.model;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import gamesoft.slots.domain.model.Reel;
import gamesoft.slots.domain.model.StripSpinner;
import gamesoft.slots.domain.model.Symbol;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import gamesoft.random.RandomNumberGenerator;

@RunWith(MockitoJUnitRunner.class)
public class StripSpinnerUTest {
	
	@Mock private RandomNumberGenerator mockRNG;
	
	@InjectMocks
	private StripSpinner spinner;
	
	private Reel reel;
	

	@Before
	public void setUp() throws Exception {
		reel = new Reel(0, TestHelper.loadSymbols(), null);
		ReflectionTestUtils.setFiled("rng", reel, mockRNG);
	}


	@Test
	public void testSpin() {
		long numberOfSymbols = reel.numberOfSymbols();
		when(mockRNG.nextLong(numberOfSymbols)).thenReturn(10L);
		
		List<Symbol> expected = expected();
		List<Symbol> actual = spinner.spin(reel, 3);
		
		assertEquals(expected, actual);
		
	}
	
	public List<Symbol> expected(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newSymbol(7, "SYMBOL_7", 10));
		symbols.add(Symbol.newSymbol(11, "SCATTER", 11));
		symbols.add(Symbol.newSymbol(8, "SYMBOL_8", 12));

		return symbols;
	}
	
	@Test
	public void testWrapAroundSpin(){
		long numberOfSymbols = reel.numberOfSymbols();
		when(mockRNG.nextLong(numberOfSymbols)).thenReturn(75L);
		
		List<Symbol> expected = wrappedExpected();
		List<Symbol> actual = spinner.spin(reel, 3);
		
		assertEquals(expected, actual);
	}
	
	public List<Symbol> wrappedExpected(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newSymbol(5, "SYMBOL_5", 75));
		symbols.add(Symbol.newSymbol(4, "SYMBOL_4", 0));
		symbols.add(Symbol.newSymbol(2, "SYMBOL_2", 1));
		
		return symbols;
	}
	

}
