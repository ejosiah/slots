package gamesoft.slots.domain.model.slot;

import static org.junit.Assert.*;
import static gamesoft.slots.domain.model.slot.TestHelper.*;
import static org.mockito.Mockito.*;
import gamesoft.slots.domain.model.slot.Direction;
import gamesoft.slots.domain.model.slot.PayoutCombo;
import gamesoft.slots.domain.model.slot.PayoutCombos;
import gamesoft.slots.domain.model.slot.Query;
import gamesoft.slots.domain.model.slot.Symbol;
import gamesoft.slots.domain.model.slot.Win;

import java.math.BigDecimal;
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
	
	@Mock PayoutCombo payoutCombo1;
	@Mock PayoutCombo payoutCombo2;
	@Mock PayoutCombo payoutCombo3;

	
	private PayoutCombos payoutCombos;
	
	@Before
	public void setup(){
		setUpDefaultMockActions();
		payoutCombos = new PayoutCombos(Arrays.asList(
				payoutCombo1, payoutCombo2, payoutCombo3));
	}
	
	private void setUpDefaultMockActions(){
		Symbol symbol = symbols.get(1);
		when(payoutCombo1.id()).thenReturn(1);
		when(payoutCombo2.id()).thenReturn(2);
		when(payoutCombo3.id()).thenReturn(3);
		
		when(query.id()).thenReturn(null);
		when(query.line()).thenReturn(null);
		when(query.numberOfCoins()).thenReturn(null);
		when(query.symbols()).thenReturn(null);

		
		
		when(payoutCombo1.payoutDirection()).thenReturn(Direction.FROM_LEFT);
		when(payoutCombo2.payoutDirection()).thenReturn(Direction.FROM_LEFT);
		when(payoutCombo3.payoutDirection()).thenReturn(Direction.FROM_LEFT);

		
		when(payoutCombo1.symbols()).thenReturn(Arrays.asList(symbol));
		when(payoutCombo2.symbols()).thenReturn(Arrays.asList(symbol));
		when(payoutCombo3.symbols()).thenReturn(Arrays.asList(symbol));
		
		when(payoutCombo1.occurrence()).thenReturn(5);
		when(payoutCombo2.occurrence()).thenReturn(4);
		when(payoutCombo3.occurrence()).thenReturn(3);

	}

	@Test
	public void testFindById() {
		when(query.id()).thenReturn(1);

		
		PayoutCombo expected = payoutCombo1;
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
		Symbol symbol1 = symbols.get(1);
		List<Symbol> symbols = Arrays.asList(symbol1, symbol1, symbol1);
		when(query.symbols()).thenReturn(symbols);
		
		when(payoutCombo1.matches(symbols)).thenReturn(false);
		when(payoutCombo2.matches(symbols)).thenReturn(false);
		when(payoutCombo3.matches(symbols)).thenReturn(false);
		
		PayoutCombo expected = PayoutCombo.NO_PAYOUT;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindPayoutComboForSymbols(){
		Symbol symbol1 = symbols.get(1);
		List<Symbol> symbols = Arrays.asList(symbol1, symbol1, symbol1);
		when(query.symbols()).thenReturn(symbols);
		
		when(payoutCombo1.matches(symbols)).thenReturn(false);
		when(payoutCombo2.matches(symbols)).thenReturn(false);
		when(payoutCombo3.matches(symbols)).thenReturn(true);
		
		PayoutCombo expected = payoutCombo3;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindLineSpecificPayoutCombo(){
		final int LINE = 1;
		Symbol symbol1 = symbols.get(1);
		List<Symbol> symbols = Arrays.asList(symbol1, symbol1, symbol1);
		when(query.symbols()).thenReturn(symbols);
		when(query.line()).thenReturn(LINE);
		
		when(payoutCombo1.matches(symbols, LINE)).thenReturn(true);
		when(payoutCombo2.matches(symbols, LINE)).thenReturn(false);
		when(payoutCombo3.matches(symbols, LINE)).thenReturn(false);
		
		PayoutCombo expected = payoutCombo1;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindCoinSpecificPayoutCombo(){
		final Integer NO_LINE = null;
		final int COINS = 5;
		Symbol symbol1 = symbols.get(1);
		List<Symbol> symbols = Arrays.asList(symbol1, symbol1, symbol1);
		when(query.symbols()).thenReturn(symbols);
		when(query.line()).thenReturn(NO_LINE);
		when(query.numberOfCoins()).thenReturn(COINS);
		
		when(payoutCombo1.matches(symbols, NO_LINE, COINS)).thenReturn(true);
		when(payoutCombo2.matches(symbols, NO_LINE, COINS)).thenReturn(false);
		when(payoutCombo3.matches(symbols, NO_LINE, COINS)).thenReturn(false);
		
		PayoutCombo expected = payoutCombo1;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLargestOfMatchingPayoutsPicked(){
		Symbol symbol1 = symbols.get(1);
		List<Symbol> symbols = Arrays.asList(symbol1, symbol1, symbol1);
		when(query.symbols()).thenReturn(symbols);
		
		when(payoutCombo1.matches(symbols)).thenReturn(true);
		when(payoutCombo2.matches(symbols)).thenReturn(true);
		when(payoutCombo3.matches(symbols)).thenReturn(true);
		
		when(payoutCombo1.compareTo(payoutCombo2)).thenReturn(1);
		when(payoutCombo1.compareTo(payoutCombo3)).thenReturn(1);
		
		when(payoutCombo2.compareTo(payoutCombo1)).thenReturn(-1);
		when(payoutCombo2.compareTo(payoutCombo3)).thenReturn(1);
		
		when(payoutCombo3.compareTo(payoutCombo1)).thenReturn(-1);
		when(payoutCombo3.compareTo(payoutCombo2)).thenReturn(-1);
		
		PayoutCombo expected = payoutCombo1;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testForWildMutiplierPayoutCombo(){
		final BigDecimal WILD_MULTIPLIER = new BigDecimal("2");
		Symbol symbol1 = symbols.get(1);
		List<Symbol> symbols = Arrays.asList(symbol1, symbol1, symbol1);
		when(query.symbols()).thenReturn(symbols);
		when(query.wildMultiplier()).thenReturn(WILD_MULTIPLIER);
		
		when(payoutCombo1.matches(symbols)).thenReturn(true);
		when(payoutCombo2.matches(symbols)).thenReturn(false);
		when(payoutCombo3.matches(symbols)).thenReturn(true);
		
		when(payoutCombo1.compareTo(payoutCombo2)).thenReturn(1);
		when(payoutCombo1.compareTo(payoutCombo3)).thenReturn(0);
		
		when(payoutCombo2.compareTo(payoutCombo1)).thenReturn(-1);
		when(payoutCombo2.compareTo(payoutCombo3)).thenReturn(-1);
		
		when(payoutCombo3.compareTo(payoutCombo1)).thenReturn(0);
		when(payoutCombo3.compareTo(payoutCombo2)).thenReturn(1);

		
		when(payoutCombo1.apply(WILD_MULTIPLIER)).thenReturn(Win.of("500"));
		when(payoutCombo3.apply(WILD_MULTIPLIER)).thenReturn(Win.of("350"));
		
		PayoutCombo expected = payoutCombo1;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(query);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNoQuery(){
		PayoutCombo expected = PayoutCombo.NO_PAYOUT;
		PayoutCombo actual = payoutCombos.getPayoutComboFor(Query.NULL);
		assertEquals(expected, actual);
	}

}
