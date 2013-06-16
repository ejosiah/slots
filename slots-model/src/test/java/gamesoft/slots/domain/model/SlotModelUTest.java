package gamesoft.slots.domain.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SlotModelUTest {
	private static final List<Boolean> NO_ANTICIPATE = Collections.emptyList();
	private static final BigDecimal NO_WILD_MULTIPLIER = BigDecimal.ONE;
	private static final int NO_OF_LINES = 5;
	private static final int LINE_1 = 0;
	private static final int LINE_2 = 1;
	private static final int LINE_3 = 2;
	private static final int LINE_4 = 3;
	private static final int LINE_5 = 4;
	private static final int REEL_1 = 0;
	private static final int REEL_2 = 1;
	private static final int REEL_3 = 2;
	private static final int REEL_4 = 3;
	private static final int REEL_5 = 4;
	private static final int NO_OF_COINS = 1;
	private static final Money BET_VALUE = Money.parse("USD 0.10");
	private static final int OCCURRENCE = 3;
	@Mock PayoutCombos payoutCombos;
	@Mock PayoutCombo payoutCombo1;
	@Mock PayoutCombo payoutCombo2;
	@Mock PayoutCombo payoutCombo3;
	@Mock Lines lines;
	@Mock ReelSpinResult reelSpinResult;
	@Mock Bet bet;
	@Mock List<List<Symbol>> reels;
	@Mock Symbol winSymbol1;
	@Mock Symbol winSymbol2;
	@Mock Symbol winSymbol3;
	@Mock Symbol winSymbol4;
	@Mock Symbol winSymbol5;
	
	SlotModel slotModel;
	

	@Before
	public void setUp() throws Exception {

	}


	@Test
	public void test() {
		slotModel = new SlotModel(payoutCombos, lines, NO_OF_LINES);
		List<Symbol> reel1 = Arrays.asList(winSymbol1, winSymbol1, winSymbol1);
		List<Symbol> reel2 = Arrays.asList(winSymbol2, winSymbol2, winSymbol2);
		List<Symbol> reel3 = Arrays.asList(winSymbol3, winSymbol3, winSymbol3);
		List<Symbol> reel4 = Arrays.asList(winSymbol1, winSymbol2, winSymbol3);
		List<Symbol> reel5 = Arrays.asList(winSymbol3, winSymbol1, winSymbol3);
		Query query1 = Query.with(reel1, LINE_1, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query2 = Query.with(reel2, LINE_2, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query3 = Query.with(reel3, LINE_3, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query4 = Query.with(reel4, LINE_4, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query5 = Query.with(reel5, LINE_5, NO_OF_COINS, NO_WILD_MULTIPLIER);

		when(bet.numberOfCoinsFor(LINE_1)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_2)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_3)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_4)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_5)).thenReturn(NO_OF_COINS);
		
		when(bet.betFor(LINE_1)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_2)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_3)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_4)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_5)).thenReturn(BET_VALUE);
		
		when(reelSpinResult.reels()).thenReturn(reels);
		
		when(lines.symbolsForLine(LINE_1, reels)).thenReturn(reel1);
		when(lines.symbolsForLine(LINE_2, reels)).thenReturn(reel2);
		when(lines.symbolsForLine(LINE_3, reels)).thenReturn(reel3);
		when(lines.symbolsForLine(LINE_4, reels)).thenReturn(reel4);
		when(lines.symbolsForLine(LINE_5, reels)).thenReturn(reel5);
		
		when(payoutCombos.getPayoutComboFor(query1)).thenReturn(payoutCombo1);
		when(payoutCombos.getPayoutComboFor(query2)).thenReturn(payoutCombo2);
		when(payoutCombos.getPayoutComboFor(query3)).thenReturn(payoutCombo3);
		when(payoutCombos.getPayoutComboFor(query4)).thenReturn(PayoutCombo.NO_PAYOUT);
		when(payoutCombos.getPayoutComboFor(query5)).thenReturn(PayoutCombo.NO_PAYOUT);
		
		when(payoutCombo1.win()).thenReturn(Win.of("100"));
		when(payoutCombo2.win()).thenReturn(Win.of("250"));
		when(payoutCombo3.win()).thenReturn(Win.of("500"));
		
		when(payoutCombo1.occurrence()).thenReturn(OCCURRENCE);
		when(payoutCombo2.occurrence()).thenReturn(OCCURRENCE);
		when(payoutCombo3.occurrence()).thenReturn(OCCURRENCE);
		
		
		when(lines.symbolPosition(LINE_1, REEL_1)).thenReturn(0);
		when(lines.symbolPosition(LINE_1, REEL_2)).thenReturn(1);
		when(lines.symbolPosition(LINE_1, REEL_3)).thenReturn(2);
		
		when(lines.symbolPosition(LINE_2, REEL_1)).thenReturn(0);
		when(lines.symbolPosition(LINE_2, REEL_2)).thenReturn(0);
		when(lines.symbolPosition(LINE_2, REEL_3)).thenReturn(0);
		
		when(lines.symbolPosition(LINE_3, REEL_1)).thenReturn(1);
		when(lines.symbolPosition(LINE_3, REEL_2)).thenReturn(1);
		when(lines.symbolPosition(LINE_3, REEL_3)).thenReturn(1);
		
		when(lines.symbolsForLine(LINE_1, reels)).thenReturn(reel1);
		when(lines.symbolsForLine(LINE_2, reels)).thenReturn(reel2);
		when(lines.symbolsForLine(LINE_3, reels)).thenReturn(reel3);

		
		
		ModelResult result = slotModel.result(reelSpinResult, bet);
		
		assertNotNull(result);
		Money expectedWins = Money.parse("USD 85");
		Money actualWins = sum(result.wins());
		
		assertEquals(expectedWins, actualWins);
		
		assertEquals(0, result.anticipate().size());
		assertEquals(0, result.anticipateWins().size());
	}
	

	private Money sum(List<? extends SlotWin> wins) {
		Money sum = Money.zero(CurrencyUnit.USD);
		for(SlotWin win : wins){
			CashWin cashWin = (CashWin)win;
			sum = sum.plus(cashWin.amount());
		}
		return sum;
	}
	
	@Test
	public void testNoWin(){
		slotModel = new SlotModel(payoutCombos, lines, NO_OF_LINES);
		List<Symbol> reel1 = Arrays.asList(winSymbol4, winSymbol1, winSymbol1);
		List<Symbol> reel2 = Arrays.asList(winSymbol2, winSymbol5, winSymbol2);
		List<Symbol> reel3 = Arrays.asList(winSymbol3, winSymbol1, winSymbol3);
		List<Symbol> reel4 = Arrays.asList(winSymbol1, winSymbol2, winSymbol3);
		List<Symbol> reel5 = Arrays.asList(winSymbol3, winSymbol1, winSymbol3);
		Query query1 = Query.with(reel1, LINE_1, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query2 = Query.with(reel2, LINE_2, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query3 = Query.with(reel3, LINE_3, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query4 = Query.with(reel4, LINE_4, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query5 = Query.with(reel5, LINE_5, NO_OF_COINS, NO_WILD_MULTIPLIER);
		
		when(reelSpinResult.reels()).thenReturn(reels);

		when(bet.numberOfCoinsFor(LINE_1)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_2)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_3)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_4)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_5)).thenReturn(NO_OF_COINS);
		
		when(bet.betFor(LINE_1)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_2)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_3)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_4)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_5)).thenReturn(BET_VALUE);
		
		when(lines.symbolsForLine(LINE_1, reels)).thenReturn(reel1);
		when(lines.symbolsForLine(LINE_2, reels)).thenReturn(reel2);
		when(lines.symbolsForLine(LINE_3, reels)).thenReturn(reel3);
		when(lines.symbolsForLine(LINE_4, reels)).thenReturn(reel4);
		when(lines.symbolsForLine(LINE_5, reels)).thenReturn(reel5);

		
		when(payoutCombos.getPayoutComboFor(query1)).thenReturn(PayoutCombo.NO_PAYOUT);
		when(payoutCombos.getPayoutComboFor(query2)).thenReturn(PayoutCombo.NO_PAYOUT);
		when(payoutCombos.getPayoutComboFor(query3)).thenReturn(PayoutCombo.NO_PAYOUT);
		when(payoutCombos.getPayoutComboFor(query4)).thenReturn(PayoutCombo.NO_PAYOUT);
		when(payoutCombos.getPayoutComboFor(query5)).thenReturn(PayoutCombo.NO_PAYOUT);
		
		ModelResult result = slotModel.result(reelSpinResult, bet);
		
		assertNotNull(result);
		assertEquals(0, result.wins().size());
		
		
		assertEquals(0, result.anticipate().size());
		assertEquals(0, result.anticipateWins().size());
	}
	
	@Test
	public void testAnticipateWithWin(){
		slotModel = new SlotModel(payoutCombos, lines, NO_OF_LINES);
		List<Symbol> reel1 = Arrays.asList(winSymbol1, winSymbol1, winSymbol1, winSymbol1, winSymbol1);
		List<Symbol> reel2 = Arrays.asList(winSymbol2, winSymbol2, winSymbol2);
		List<Symbol> reel3 = Arrays.asList(winSymbol3, winSymbol3, winSymbol3);
		List<Symbol> reel4 = Arrays.asList(winSymbol1, winSymbol2, winSymbol3);
		List<Symbol> reel5 = Arrays.asList(winSymbol3, winSymbol1, winSymbol3);
		Query query1 = Query.with(reel1, LINE_1, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query2 = Query.with(reel2, LINE_2, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query3 = Query.with(reel3, LINE_3, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query4 = Query.with(reel4, LINE_4, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query5 = Query.with(reel5, LINE_5, NO_OF_COINS, NO_WILD_MULTIPLIER);

		when(bet.numberOfCoinsFor(LINE_1)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_2)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_3)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_4)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_5)).thenReturn(NO_OF_COINS);
		
		when(bet.betFor(LINE_1)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_2)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_3)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_4)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_5)).thenReturn(BET_VALUE);
		
		when(reelSpinResult.reels()).thenReturn(reels);
		
		when(lines.symbolsForLine(LINE_1, reels)).thenReturn(reel1);
		when(lines.symbolsForLine(LINE_2, reels)).thenReturn(reel2);
		when(lines.symbolsForLine(LINE_3, reels)).thenReturn(reel3);
		when(lines.symbolsForLine(LINE_4, reels)).thenReturn(reel4);
		when(lines.symbolsForLine(LINE_5, reels)).thenReturn(reel5);
		
		when(payoutCombos.getPayoutComboFor(query1)).thenReturn(payoutCombo1);
		when(payoutCombos.getPayoutComboFor(query2)).thenReturn(payoutCombo2);
		when(payoutCombos.getPayoutComboFor(query3)).thenReturn(payoutCombo3);
		when(payoutCombos.getPayoutComboFor(query4)).thenReturn(PayoutCombo.NO_PAYOUT);
		when(payoutCombos.getPayoutComboFor(query5)).thenReturn(PayoutCombo.NO_PAYOUT);
		
		when(payoutCombo1.win()).thenReturn(Win.of("100"));
		when(payoutCombo2.win()).thenReturn(Win.of("250"));
		when(payoutCombo3.win()).thenReturn(Win.of("500"));
		
		when(payoutCombo1.occurrence()).thenReturn(5);
		when(payoutCombo2.occurrence()).thenReturn(OCCURRENCE);
		when(payoutCombo3.occurrence()).thenReturn(OCCURRENCE);
		
		
		when(lines.symbolPosition(LINE_1, REEL_1)).thenReturn(0);
		when(lines.symbolPosition(LINE_1, REEL_2)).thenReturn(1);
		when(lines.symbolPosition(LINE_1, REEL_3)).thenReturn(2);
		
		when(lines.symbolPosition(LINE_2, REEL_1)).thenReturn(0);
		when(lines.symbolPosition(LINE_2, REEL_2)).thenReturn(0);
		when(lines.symbolPosition(LINE_2, REEL_3)).thenReturn(0);
		
		when(lines.symbolPosition(LINE_3, REEL_1)).thenReturn(1);
		when(lines.symbolPosition(LINE_3, REEL_2)).thenReturn(1);
		when(lines.symbolPosition(LINE_3, REEL_3)).thenReturn(1);
		
		when(lines.symbolsForLine(LINE_1, reels)).thenReturn(reel1);
		when(lines.symbolsForLine(LINE_2, reels)).thenReturn(reel2);
		when(lines.symbolsForLine(LINE_3, reels)).thenReturn(reel3);

		
		
		ModelResult result = slotModel.result(reelSpinResult, bet);
		
		assertNotNull(result);
		Money expectedWins = Money.parse("USD 85");
		Money actualWins = sum(result.wins());
		
		assertEquals(expectedWins, actualWins);
		
		int lastItem = 4;
		assertEquals(5, result.anticipate().size());
		assertTrue(result.anticipate().get(lastItem));
		assertEquals(5, result.anticipateWins().size());
		assertTrue(result.anticipateWins().get(lastItem));
	}
	
	@Test
	public void testAnticipateWithNoWin(){
		slotModel = new SlotModel(payoutCombos, lines, NO_OF_LINES);
		List<Symbol> reel1 = Arrays.asList(winSymbol1, winSymbol1, winSymbol1, winSymbol1);
		List<Symbol> reel2 = Arrays.asList(winSymbol2, winSymbol2, winSymbol2);
		List<Symbol> reel3 = Arrays.asList(winSymbol3, winSymbol3, winSymbol3);
		List<Symbol> reel4 = Arrays.asList(winSymbol1, winSymbol2, winSymbol3);
		List<Symbol> reel5 = Arrays.asList(winSymbol3, winSymbol1, winSymbol3);
		Query query1 = Query.with(reel1, LINE_1, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query2 = Query.with(reel2, LINE_2, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query3 = Query.with(reel3, LINE_3, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query4 = Query.with(reel4, LINE_4, NO_OF_COINS, NO_WILD_MULTIPLIER);
		Query query5 = Query.with(reel5, LINE_5, NO_OF_COINS, NO_WILD_MULTIPLIER);

		when(bet.numberOfCoinsFor(LINE_1)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_2)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_3)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_4)).thenReturn(NO_OF_COINS);
		when(bet.numberOfCoinsFor(LINE_5)).thenReturn(NO_OF_COINS);
		
		when(bet.betFor(LINE_1)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_2)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_3)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_4)).thenReturn(BET_VALUE);
		when(bet.betFor(LINE_5)).thenReturn(BET_VALUE);
		
		when(reelSpinResult.reels()).thenReturn(reels);
		
		when(lines.symbolsForLine(LINE_1, reels)).thenReturn(reel1);
		when(lines.symbolsForLine(LINE_2, reels)).thenReturn(reel2);
		when(lines.symbolsForLine(LINE_3, reels)).thenReturn(reel3);
		when(lines.symbolsForLine(LINE_4, reels)).thenReturn(reel4);
		when(lines.symbolsForLine(LINE_5, reels)).thenReturn(reel5);
		
		when(payoutCombos.getPayoutComboFor(query1)).thenReturn(payoutCombo1);
		when(payoutCombos.getPayoutComboFor(query2)).thenReturn(payoutCombo2);
		when(payoutCombos.getPayoutComboFor(query3)).thenReturn(payoutCombo3);
		when(payoutCombos.getPayoutComboFor(query4)).thenReturn(PayoutCombo.NO_PAYOUT);
		when(payoutCombos.getPayoutComboFor(query5)).thenReturn(PayoutCombo.NO_PAYOUT);
		
		when(payoutCombo1.win()).thenReturn(Win.of("100"));
		when(payoutCombo2.win()).thenReturn(Win.of("250"));
		when(payoutCombo3.win()).thenReturn(Win.of("500"));
		
		when(payoutCombo1.occurrence()).thenReturn(4);
		when(payoutCombo2.occurrence()).thenReturn(OCCURRENCE);
		when(payoutCombo3.occurrence()).thenReturn(OCCURRENCE);
		
		
		when(lines.symbolPosition(LINE_1, REEL_1)).thenReturn(0);
		when(lines.symbolPosition(LINE_1, REEL_2)).thenReturn(1);
		when(lines.symbolPosition(LINE_1, REEL_3)).thenReturn(2);
		
		when(lines.symbolPosition(LINE_2, REEL_1)).thenReturn(0);
		when(lines.symbolPosition(LINE_2, REEL_2)).thenReturn(0);
		when(lines.symbolPosition(LINE_2, REEL_3)).thenReturn(0);
		
		when(lines.symbolPosition(LINE_3, REEL_1)).thenReturn(1);
		when(lines.symbolPosition(LINE_3, REEL_2)).thenReturn(1);
		when(lines.symbolPosition(LINE_3, REEL_3)).thenReturn(1);
		
		when(lines.symbolsForLine(LINE_1, reels)).thenReturn(reel1);
		when(lines.symbolsForLine(LINE_2, reels)).thenReturn(reel2);
		when(lines.symbolsForLine(LINE_3, reels)).thenReturn(reel3);

		
		
		ModelResult result = slotModel.result(reelSpinResult, bet);
		
		assertNotNull(result);
		Money expectedWins = Money.parse("USD 85");
		Money actualWins = sum(result.wins());
		
		assertEquals(expectedWins, actualWins);
		
		int lastItem = 4;
		assertEquals(5, result.anticipate().size());
		assertTrue(result.anticipate().get(lastItem));
		assertEquals(5, result.anticipateWins().size());
		assertFalse(result.anticipateWins().get(lastItem));
	}
	
	@Test
	public void testForDualStateReel(){
		fail("Not yet implemented!");
	}

}
