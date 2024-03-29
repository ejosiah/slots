package gamesoft.slots.domain.model.slot;

import static org.junit.Assert.*;

import gamesoft.slots.domain.model.slot.PayoutCombo;
import gamesoft.slots.domain.model.slot.Symbol;
import gamesoft.slots.domain.model.slot.Win;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class PayoutComboUTest {

	private Win win = Win.of("10");
	private PayoutCombo payoutCombo;

	@Test
	public void testMatchsFromLeft() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbol(2, "SYMBOL_2", 1);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.createCombo(0, 4, win, symbol1);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, symbol1, symbol1, symbol2));
		
		boolean matches = payoutCombo.matches(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, wild, symbol1, symbol1, symbol2));
		
		matches = payoutCombo.matches(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, symbol2, symbol1, symbol1, symbol2));
		
		matches = payoutCombo.matches(symbols);
		assertFalse(matches);
	}
	
	@Test
	public void testMatchsFromLeftIgnoreWild() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.ignoreWildCombo(0, 5, win, symbol1);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, wild, symbol1, symbol1));
		
		boolean matches = payoutCombo.matches(symbols);
		assertFalse(matches);

	}
	
	@Test
	public void testMatchsFromRightIgnoreWild() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbol(2, "SYMBOL_2", 0);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.ignoreWildComboFromRight(0, 4, win, symbol1);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol2, symbol1, symbol1, symbol1, wild));
		
		boolean matches = payoutCombo.matches(symbols);
		assertFalse(matches);
		
	}
	
	@Test
	public void testMatchsFromAnyDirectionIgnoreWild() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbol(2, "SYMBOL_2", 0);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.ignoreWildComboFromAnyDirection(0, 4, win, symbol1);
				
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, wild, symbol1, symbol2));
		
		boolean matches = payoutCombo.matches(symbols);
		assertFalse(matches);
		
		symbols.addAll(Arrays.asList(symbol2, symbol1, wild, symbol1, symbol1));
		
		matches = payoutCombo.matches(symbols);
		assertFalse(matches);
		
	}
	
	@Test
	public void testMatchsFromRight() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbol(2, "SYMBOL_2", 1);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.createComboFromRight(0, 4, win, symbol1);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol2, symbol1, symbol1, symbol1, symbol1));
		
		boolean matches = payoutCombo.matches(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol2, wild, symbol1, symbol1, wild));
		
		matches = payoutCombo.matches(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, symbol2, symbol1, symbol1, symbol2));
		
		matches = payoutCombo.matches(symbols);
		assertFalse(matches);
	}
	
	@Test
	public void testMatchsFromAnyDirection() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbol(2, "SYMBOL_2", 1);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.createComboFromAnyDirection(0, 4, win, symbol1);
				
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, symbol1, symbol1, symbol2));
		
		boolean matches = payoutCombo.matches(symbols);
		assertTrue(matches);

		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol2, symbol1, symbol1, symbol1, symbol1));
		
		matches = payoutCombo.matches(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol2, symbol1, symbol1, symbol1, wild));
		
		matches = payoutCombo.matches(symbols);
		assertTrue(matches);
	}

	@Test
	public void testCompareTo() {
		Symbol symbol = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Win win1 = Win.of("500");
		
		PayoutCombo payoutCombo1 = PayoutCombo.createCombo(0, 5, win1, symbol);
		
		assertTrue(payoutCombo1.compareTo(payoutCombo1) == 0);
		
		Win win2 = Win.of("250");
		PayoutCombo payoutCombo2 = PayoutCombo.createCombo(0, 4, win2, symbol);
		
		assertTrue(payoutCombo1.compareTo(payoutCombo2) > 0);
		assertTrue(payoutCombo2.compareTo(payoutCombo1) < 0);
		
		PayoutCombo progressivePayout = PayoutCombo.progressiveCombo(0, 5, symbol);
		
		
		assertTrue(progressivePayout.compareTo(payoutCombo2) > 0);
		assertTrue(progressivePayout.compareTo(payoutCombo1) > 0);
	}
	
	@Test
	public void testLongerOfEqualMatchingPayoutsPicked(){
		Symbol symbol = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Win win = Win.of("500");
		
		PayoutCombo payoutCombo1 = PayoutCombo.createCombo(0, 4, win, symbol);		
		PayoutCombo payoutCombo2 = PayoutCombo.createCombo(0, 5, win, symbol);
		
		assertTrue(payoutCombo2.compareTo(payoutCombo1) > 0);
	}
	
	
	@Test
	public void testForLineSpecificPayoutCombo(){
		Symbol symbol = Symbol.newSymbol(0, "SYMBOL_1", 0);
		Win win = Win.of("10");
		int line = 1;
		payoutCombo = PayoutCombo.lineSpecificCombo(0, 3, win, symbol, line);
		
		boolean matches = payoutCombo.matches(Arrays.asList(symbol, symbol, symbol), line);
		assertTrue(matches);
		
		line = 2;
		matches = payoutCombo.matches(Arrays.asList(symbol, symbol, symbol), line);
		assertFalse(matches);
	}
	
	@Test
	public void testForCoinSpecificPayoutCombo(){
		Symbol symbol = Symbol.newSymbol(0, "SYMBOL_1", 0);
		Win win = Win.of("10");
		int coins = 5;
		payoutCombo = PayoutCombo.coinsSpecificCombo(0, 3, win, symbol, coins);
		
		boolean matches = payoutCombo.matches(Arrays.asList(symbol, symbol, symbol), null, coins);
		assertTrue(matches);
		
		coins = 2;
		matches = payoutCombo.matches(Arrays.asList(symbol, symbol, symbol), null, coins);
		assertFalse(matches);
	}
	
	@Test
	public void testForMixedPayoutCombo(){
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbol(2, "SYMBOL_2", 1);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.mixedCombo(0, 3, win, Arrays.asList(symbol1, symbol2, wild));
		
		List<Symbol> symbols = new ArrayList<>(Arrays.asList(symbol1, wild, symbol2));
		
		boolean matches = payoutCombo.matches(symbols);
		assertTrue(matches);
		
		symbols = new ArrayList<>(Arrays.asList(symbol1, symbol2));
		
		matches = payoutCombo.matches(symbols);
		assertFalse(matches);
	}
	
	@Test
	public void testHasWild(){
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbol(2, "SYMBOL_2", 1);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.mixedCombo(0, 3, win, Arrays.asList(symbol1, symbol2, wild));
		assertTrue(payoutCombo.hasWild());		

		payoutCombo = PayoutCombo.mixedCombo(0, 2, win, Arrays.asList(symbol1, symbol2));
		assertFalse(payoutCombo.hasWild());
	}
	
	@Test
	public void testApplyWildMultiplier(){
		Symbol symbol = Symbol.newSymbol(0, "SYMBOL_1", 0);
		Win win = Win.of("50");
		
		payoutCombo = PayoutCombo.createCombo(0, 3, win, symbol);
		
		BigDecimal wildMultiplier = new BigDecimal("2");
		Win expected = Win.of("100");
		Win actual = payoutCombo.apply(wildMultiplier);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testApplyHasNoEffectOnWildPayoutCombo(){
		Symbol wild = Symbol.wildSymbol(0, "WILD", 0);
		Win win = Win.of("1000");
		
		payoutCombo = PayoutCombo.createCombo(0, 5, win, wild);
		
		BigDecimal wildMultiplier = new BigDecimal("2");
		Win expected = Win.of("1000");
		Win actual = payoutCombo.apply(wildMultiplier);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testPaysOutOnAllWilds(){
		Symbol symbol = Symbol.newSymbol(1, "SYMOBL_", 0);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 0);
		Win win = Win.of("1000");
		
		payoutCombo = PayoutCombo.createCombo(0, 5, win, symbol);
		
		boolean matches = payoutCombo.matches(Arrays.asList(wild, wild, wild, wild, wild));
		assertTrue(matches);
	}


}
