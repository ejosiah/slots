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

import com.casinotech.random.RandomNumberGenerator;

@RunWith(MockitoJUnitRunner.class)
public class PayoutComboUTest {

	private Win win = Win.of("10");
	private PayoutCombo payoutCombo;

	@Mock RandomNumberGenerator rng;
	
	@Before
	public void setup(){
		RNGHolder holder = new RNGHolder();
		holder.setRNG(rng);
	}

	@Test
	public void testMatchsFromLeft() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbol(2, "SYMBOL_2", 1);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.createCombo(0, 5, win, symbol1);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, symbol1, symbol1, symbol1));
		
		boolean matches = payoutCombo.matchs(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, wild, symbol1, symbol1, wild));
		
		matches = payoutCombo.matchs(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, symbol2, symbol1, symbol1, symbol2));
		
		matches = payoutCombo.matchs(symbols);
		assertFalse(matches);
	}
	
	@Test
	public void testMatchsFromLeftIgnoreWild() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.ignoreWild(0, 5, win, symbol1);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, wild, symbol1, symbol1));
		
		boolean matches = payoutCombo.matchs(symbols);
		assertFalse(matches);

	}
	
	@Test
	public void testMatchsFromRightIgnoreWild() {
		Symbol symbol1 = Symbol.newSymbolFromRight(1, "SYMBOL_1", 0);
		Symbol wild = Symbol.wildSymbolFromRight(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.ignoreWild(0, 5, win, symbol1);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, symbol1, symbol1, wild));
		
		boolean matches = payoutCombo.matchs(symbols);
		assertFalse(matches);
		
	}
	@Test
	public void testMatchsFromAnyDirectionIgnoreWild() {
		Symbol symbol1 = Symbol.newSymbol(1, "SYMBOL_1", 0);
		Symbol wild = Symbol.wildSymbol(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.ignoreWild(0, 5, win, symbol1);
		when(rng.nextBoolean()).thenReturn(true);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, wild, symbol1, symbol1));
		
		boolean matches = payoutCombo.matchs(symbols);
		assertFalse(matches);
		
	}
	
	@Test
	public void testMatchsFromRight() {
		Symbol symbol1 = Symbol.newSymbolFromRight(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbolFromRight(2, "SYMBOL_2", 1);
		Symbol wild = Symbol.wildSymbolFromRight(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.createCombo(0, 5, win, symbol1);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, symbol1, symbol1, symbol1));
		
		boolean matches = payoutCombo.matchs(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, wild, symbol1, symbol1, wild));
		
		matches = payoutCombo.matchs(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, symbol2, symbol1, symbol1, symbol2));
		
		matches = payoutCombo.matchs(symbols);
		assertFalse(matches);
	}
	
	@Test
	public void testMatchsFromAnyDirection() {
		Symbol symbol1 = Symbol.newSymbolFromRight(1, "SYMBOL_1", 0);
		Symbol symbol2 = Symbol.newSymbolFromRight(2, "SYMBOL_2", 1);
		Symbol wild = Symbol.wildSymbolFromRight(0, "WILD", 2);
		
		payoutCombo = PayoutCombo.createCombo(0, 5, win, symbol1);
		
		when(rng.nextBoolean()).thenReturn(false);
		
		List<Symbol> symbols = new ArrayList<>();
		symbols.addAll(Arrays.asList(symbol1, symbol1, symbol1, symbol1, symbol1));
		
		boolean matches = payoutCombo.matchs(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, wild, symbol1, symbol1, wild));
		
		matches = payoutCombo.matchs(symbols);
		assertTrue(matches);
		
		symbols.clear();
		symbols.addAll(Arrays.asList(symbol1, symbol2, symbol1, symbol1, symbol2));
		
		matches = payoutCombo.matchs(symbols);
		assertFalse(matches);
	}

	@Test
	public void testCompareTo() {
		Symbol symbol = Symbol.newSymbolFromRight(1, "SYMBOL_1", 0);
		Win win1 = Win.of("500");
		
		PayoutCombo payoutCombo1 = PayoutCombo.createCombo(0, 5, win1, symbol);
		
		assertTrue(payoutCombo1.compareTo(payoutCombo1) == 0);
		
		Win win2 = Win.of("250");
		PayoutCombo payoutCombo2 = PayoutCombo.createCombo(0, 4, win2, symbol);
		
		assertTrue(payoutCombo1.compareTo(payoutCombo2) > 0);
		assertTrue(payoutCombo2.compareTo(payoutCombo1) < 0);
	}

}
