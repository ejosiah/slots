package com.casinotech.slots.domain.model;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReelsUTest {
	
	private static final int NO_OF_SYMBOLS = 3;
	@Mock
	private Reel mockReel1;
	
	@Mock
	private Reel mockReel2;
	
	@Mock
	private Reel mockReel3;
	
	@Mock
	private Reel mockReel4;
	
	@Mock
	private Reel mockReel5;
	
	private Reels reels;

	@Before
	public void setUp() throws Exception {
		reels = new Reels(Arrays.asList(
				mockReel1,
				mockReel2,
				mockReel3,
				mockReel4,
				mockReel5), NO_OF_SYMBOLS);
	}

	@Test
	public void testSpin() {
		when(mockReel1.spin(NO_OF_SYMBOLS)).thenReturn(reelResult1());
		when(mockReel2.spin(NO_OF_SYMBOLS)).thenReturn(reelResult2());
		when(mockReel3.spin(NO_OF_SYMBOLS)).thenReturn(reelResult3());
		when(mockReel4.spin(NO_OF_SYMBOLS)).thenReturn(reelResult4());
		when(mockReel5.spin(NO_OF_SYMBOLS)).thenReturn(reelResult5());
		
		ReelSpinResult result = reels.spin();
		assertThat(result, equalTo(expected()));
		
	}
	
	@SuppressWarnings("unchecked")
	private ReelSpinResult expected(){
		List<List<Symbol>> result = new ArrayList<List<Symbol>>();
		result.addAll(Arrays.asList(
				reelResult1(),
				reelResult2(),
				reelResult3(),
				reelResult4(),
				reelResult5()));
		
		return new ReelSpinResult(result, result);
	}
	
	public List<Symbol> reelResult1(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newSymbol(4, "SYMBOL_4", 73));
		symbols.add(Symbol.newSymbol(2, "SYMBOL_2", 74));
		symbols.add(Symbol.newSymbol(5, "SYMBOL_5", 75));

		
		return symbols;
	}
	
	public List<Symbol> reelResult2(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newSymbol(7, "SYMBOL_7", 10));
		symbols.add(Symbol.newSymbol(11, "SCATTER", 11));
		symbols.add(Symbol.newSymbol(8, "SYMBOL_8", 12));
		
		return symbols;
	}
	
	public List<Symbol> reelResult3(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newSymbol(7, "SYMBOL_7", 10));
		symbols.add(Symbol.newSymbol(11, "SCATTER", 11));
		symbols.add(Symbol.newSymbol(8, "SYMBOL_8", 12));
		
		return symbols;
	}
	
	public List<Symbol> reelResult4(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newSymbol(7, "SYMBOL_7", 10));
		symbols.add(Symbol.newSymbol(11, "SCATTER", 11));
		symbols.add(Symbol.newSymbol(8, "SYMBOL_8", 12));
		
		return symbols;
	}
	
	public List<Symbol> reelResult5(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.newSymbol(7, "SYMBOL_7", 10));
		symbols.add(Symbol.newSymbol(11, "SCATTER", 11));
		symbols.add(Symbol.newSymbol(8, "SYMBOL_8", 12));
		
		return symbols;
	}

}
