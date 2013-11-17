package gamesoft.slots.domain.model.slot;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import gamesoft.slots.domain.model.slot.SlotBet;
import gamesoft.slots.domain.model.slot.CashWin;
import gamesoft.slots.domain.model.slot.ModelResult;
import gamesoft.slots.domain.model.slot.ReelSpinResult;
import gamesoft.slots.domain.model.slot.ScatterModel;
import gamesoft.slots.domain.model.slot.SlotWin;
import gamesoft.slots.domain.model.slot.Symbol;
import gamesoft.slots.domain.model.slot.SymbolPosition;

import java.util.Arrays;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ScatterModelUTest {
	private static final List<Boolean> NO_ANTICIPATE = Arrays.asList(false, false, false, false, false);
	private static final List<Boolean> ANTICIPATE = Arrays.asList(false, false, false, false, true);
	private static final Money BET_VALUE = Money.parse("USD 20.00");
	private static final List<Integer> WINS = Arrays.asList(0, 0, 0, 4, 8, 20);
	@Mock ReelSpinResult reelSpinResult;
	@Mock SlotBet bet;
	@Mock Symbol scatterSymbol;
	@Mock List<List<Symbol>> reels;
	@Mock List<SymbolPosition> symbolPositions;
	ScatterModel scatterModel;
	
	
	@Before
	public void setUp() throws Exception {
		scatterModel = new ScatterModel(WINS, scatterSymbol);
	}

	@Test
	public void test() {
		when(symbolPositions.toArray()).thenReturn(new Object[0]);
		when(reelSpinResult.count(scatterSymbol)).thenReturn(3);
		when(reelSpinResult.findPositionsOf(scatterSymbol)).thenReturn(symbolPositions);
		when(reelSpinResult.noOfReels()).thenReturn(5);
		when(reelSpinResult.contains(0, scatterSymbol)).thenReturn(true);
		when(reelSpinResult.contains(1, scatterSymbol)).thenReturn(true);
		when(reelSpinResult.contains(2, scatterSymbol)).thenReturn(true);
		when(reelSpinResult.contains(3, scatterSymbol)).thenReturn(false);
		when(reelSpinResult.contains(4, scatterSymbol)).thenReturn(false);
		when(bet.value()).thenReturn(BET_VALUE);
		
		ModelResult result = scatterModel.result(reelSpinResult, bet);
		assertNotNull(result);
		
		Money expected = Money.parse("USD 80.00");
		Money actual = sum(result.wins());
		
		assertEquals(expected, actual);
		assertEquals(NO_ANTICIPATE, result.anticipate());
		assertEquals(NO_ANTICIPATE, result.anticipateWins());
		
	}
	
	@Test
	public void testWinWithAnticipate(){
		when(symbolPositions.toArray()).thenReturn(new Object[0]);
		when(reelSpinResult.count(scatterSymbol)).thenReturn(5);
		when(reelSpinResult.findPositionsOf(scatterSymbol)).thenReturn(symbolPositions);
		when(reelSpinResult.noOfReels()).thenReturn(5);
		when(reelSpinResult.contains(0, scatterSymbol)).thenReturn(true);
		when(reelSpinResult.contains(1, scatterSymbol)).thenReturn(true);
		when(reelSpinResult.contains(2, scatterSymbol)).thenReturn(true);
		when(reelSpinResult.contains(3, scatterSymbol)).thenReturn(true);
		when(reelSpinResult.contains(4, scatterSymbol)).thenReturn(true);
		when(bet.value()).thenReturn(BET_VALUE);
		
		ModelResult result = scatterModel.result(reelSpinResult, bet);
		assertNotNull(result);
		
		Money expected = Money.parse("USD 400.00");
		Money actual = sum(result.wins());
		
		assertEquals(expected, actual);
		assertEquals(ANTICIPATE, result.anticipate());
		assertEquals(ANTICIPATE, result.anticipateWins());		
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
		when(symbolPositions.toArray()).thenReturn(new Object[0]);
		when(reelSpinResult.count(scatterSymbol)).thenReturn(0);
		when(reelSpinResult.findPositionsOf(scatterSymbol)).thenReturn(symbolPositions);
		when(reelSpinResult.noOfReels()).thenReturn(5);
		when(reelSpinResult.contains(0, scatterSymbol)).thenReturn(false);
		when(reelSpinResult.contains(1, scatterSymbol)).thenReturn(false);
		when(reelSpinResult.contains(2, scatterSymbol)).thenReturn(false);
		when(reelSpinResult.contains(3, scatterSymbol)).thenReturn(false);
		when(reelSpinResult.contains(4, scatterSymbol)).thenReturn(false);
		when(bet.value()).thenReturn(BET_VALUE);
		
		ModelResult result = scatterModel.result(reelSpinResult, bet);
		assertNotNull(result);
		
		assertEquals(0, result.wins().size());
		
		assertEquals(0, result.anticipate().size());
		assertEquals(0, result.anticipateWins().size());
	}

}
