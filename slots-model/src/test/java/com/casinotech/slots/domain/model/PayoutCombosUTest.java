package com.casinotech.slots.domain.model;

import static org.junit.Assert.*;
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
public class PayoutCombosUTest {
	
	private List<PayoutCombo> combos = TestHelper.loadPayout();
	private List<Symbol> symbols = TestHelper.payoutSymbols();

	@Mock Query query;
	@Mock Lines lines;
	
	private PayoutCombos payoutCombos;
	
	@Before
	public void setup(){
		payoutCombos = new PayoutCombos(combos, lines);
	}

	@Test
	public void testFindById() {
		when(query.id()).thenReturn(2);
		
		PayoutCombo expected = combos.get(2);
		
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNoExsitentPayout(){
		when(query.id()).thenReturn(25);
		
		PayoutCombo expected = PayoutCombo.NO_PAYOUT;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindPayoutComboForReelsLineNumberOfCoinsAndLineHelper(){
		Symbol symbol1 = symbols.get(0);
		List<List<Symbol>> reels = buildWinningCombo();
		
		when(query.id()).thenReturn(null);
		when(query.symbols()).thenReturn(reels);
		when(query.numberOfCoins()).thenReturn(null);
		when(query.line()).thenReturn(3);
		
		when(lines.symbolsForLine(3, reels))
			.thenReturn(Arrays.asList(symbol1, symbol1, symbol1, symbol1, symbol1));
		
		PayoutCombo expected = combos.get(0);
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
		
	}
	
	public List<List<Symbol>> buildWinningCombo(){
		List<List<Symbol>> reels = new ArrayList<>();
		List<Symbol> reel1 = Arrays.asList(symbols.get(0), symbols.get(1), symbols.get(3));
		List<Symbol> reel2 = Arrays.asList(symbols.get(7), symbols.get(0), symbols.get(6));
		List<Symbol> reel3 = Arrays.asList(symbols.get(2), symbols.get(8), symbols.get(0));
		List<Symbol> reel4 = Arrays.asList(symbols.get(4), symbols.get(0), symbols.get(3));
		List<Symbol> reel5 = Arrays.asList(symbols.get(0), symbols.get(5), symbols.get(4));
		
		reels.add(reel1);
		reels.add(reel2);
		reels.add(reel3);
		reels.add(reel4);
		reels.add(reel5);
		
		return reels;
	}
	
	@Test
	public void testForWildMutiplierPayoutCombo(){
		fail("Not yet implemented!");
	}
	
	@Test
	public void testForProgressivePayoutCombo(){
		fail("Not yet implemented!");
	}
}
