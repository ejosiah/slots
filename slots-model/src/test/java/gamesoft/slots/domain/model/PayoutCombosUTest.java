package gamesoft.slots.domain.model;

import static org.junit.Assert.*;
import static gamesoft.slots.domain.model.TestHelper.*;
import static org.mockito.Mockito.*;
import gamesoft.slots.domain.model.PayoutCombo;
import gamesoft.slots.domain.model.PayoutCombos;
import gamesoft.slots.domain.model.Query;
import gamesoft.slots.domain.model.Symbol;

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
	
	private static final int NO_PAYOUT_ID = Integer.MAX_VALUE;

	@Mock Query query;
	
	private PayoutCombos payoutCombos;
	
	@Before
	public void setup(){
		payoutCombos = new PayoutCombos(new ArrayList<>(combos.values()));
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
		when(query.id()).thenReturn(NO_PAYOUT_ID);
		
		PayoutCombo expected = PayoutCombo.NO_PAYOUT;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNoPayout(){
		Symbol symbol9 = symbols.get(9);
		List<Symbol> symbolsOnLine = Arrays.asList(symbol9, symbol9);
		when(query.symbols()).thenReturn(symbolsOnLine);
		when(query.id()).thenReturn(null);
		
		PayoutCombo expected = PayoutCombo.NO_PAYOUT;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindPayoutComboForSymbols(){
		List<Symbol> symbolsOnLine = buildSymbolsOnLine();
		
		when(query.id()).thenReturn(null);
		when(query.symbols()).thenReturn(symbolsOnLine);
		when(query.numberOfCoins()).thenReturn(null);
		when(query.line()).thenReturn(null);
		
		PayoutCombo expected = combos.get(0);
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testFindLineSpecificPayoutCombo(){
		Symbol symbol3 = symbols.get(3);
		List<Symbol> symbolsOnLine = Arrays.asList(symbol3, symbol3, symbol3);
		
		when(query.id()).thenReturn(null);
		when(query.symbols()).thenReturn(symbolsOnLine);
		when(query.numberOfCoins()).thenReturn(null);
		when(query.line()).thenReturn(4);
		
		PayoutCombo expected = combos.get(30);
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindCoinSpecificPayoutCombo(){
		Symbol symbol5 = symbols.get(5);
		List<Symbol> symbolsOnLine = Arrays.asList(symbol5, symbol5, symbol5);
		
		when(query.id()).thenReturn(null);
		when(query.symbols()).thenReturn(symbolsOnLine);
		when(query.numberOfCoins()).thenReturn(20);
		when(query.line()).thenReturn(null);
		
		PayoutCombo expected = combos.get(31);
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	public List<Symbol> buildSymbolsOnLine(){
		Symbol symbol1 = symbols.get(1);
		return Arrays.asList(symbol1 , symbol1, symbol1, symbol1, symbol1);
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
