package gamesoft.slots.domain.model.slot;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import gamesoft.slots.domain.model.slot.Reel;
import gamesoft.slots.domain.model.slot.StripSpinner;
import gamesoft.slots.domain.model.slot.Symbol;

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
public class WeightedReelSpinnerUTest {
	
	@Mock private RandomNumberGenerator mockRNG;
	
	@InjectMocks
	private StripSpinner spinner;
	
	private Reel reel;
	

	@Before
	public void setUp() throws Exception {
		reel = new Reel(0, TestHelper.weightedloadSymbols(), null);
		ReflectionTestUtils.setFiled("rng", reel, mockRNG);
	}


	@Test
	public void testSpin() {
		when(mockRNG.nextLong(3840L)).thenReturn(2959L);
		
		List<Symbol> expected = expected();
		List<Symbol> actual = spinner.spin(reel, 3);
		
		assertEquals(expected, actual);
		
	}
	
	public List<Symbol> expected(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newWeightedSymbol(7, "SYMBOL_7", 80L, 10));
		symbols.add(Symbol.newWeightedSymbol(11, "SCATTER", 5L, 11));
		symbols.add(Symbol.newWeightedSymbol(8, "SYMBOL_8", 57L, 12));
		
		return symbols;
	}
	
	@Test
	public void testWrapAroundSpin(){
		when(mockRNG.nextLong(3840L)).thenReturn(1698L);
		
		List<Symbol> expected = wrappedExpected();
		List<Symbol> actual = spinner.spin(reel, 3);
		
		assertEquals(expected, actual);
	}
	
	public List<Symbol> wrappedExpected(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newWeightedSymbol(5, "SYMBOL_5", 55l, 75));
		symbols.add(Symbol.newWeightedSymbol(4, "SYMBOL_4", 28l, 0));
		symbols.add(Symbol.newWeightedSymbol(2, "SYMBOL_2", 40l, 1));
		
		return symbols;
	}
	

}
