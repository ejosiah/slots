package com.casinotech.slots.domain.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LinesUTest {
	
	private List<Symbol> symbols = TestHelper.payoutSymbols();
	
	@Mock List<List<Integer>> lines;
	
	@InjectMocks private Lines lineHelper;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSymbolsForLine() {
		when(lines.get(3)).thenReturn(Arrays.asList(0, 1, 2, 1, 0));
		
		List<Symbol> expected = expected();
		List<Symbol> actual = lineHelper.symbolsForLine(3, buildReels());
		
		assertEquals(expected, actual);
		
	}
	
	private List<List<Symbol>> buildReels(){
		List<List<Symbol>> reels = new ArrayList<>();
		List<Symbol> reel1 = Arrays.asList(symbols.get(0), symbols.get(1), symbols.get(3));
		List<Symbol> reel2 = Arrays.asList(symbols.get(7), symbols.get(0), symbols.get(6));
		List<Symbol> reel3 = Arrays.asList(symbols.get(2), symbols.get(8), symbols.get(4));
		List<Symbol> reel4 = Arrays.asList(symbols.get(4), symbols.get(1), symbols.get(3));
		List<Symbol> reel5 = Arrays.asList(symbols.get(6), symbols.get(5), symbols.get(4));
		
		reels.add(reel1);
		reels.add(reel2);
		reels.add(reel3);
		reels.add(reel4);
		reels.add(reel5);
		
		return reels;
	}
	
	private List<Symbol> expected(){
		return Arrays.asList(
			symbols.get(0),
			symbols.get(0),
			symbols.get(4),
			symbols.get(1),
			symbols.get(6)		
		);
	}

	@Test
	public void testsymbolPosition() {
		when(lines.get(3)).thenReturn(Arrays.asList(0, 1, 2, 1, 0));
		
		Integer expected = 2;
		Integer actual = lineHelper.symbolPosition(3, 2);
		assertEquals(expected, actual);
	}

}
