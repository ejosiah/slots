package gamesoft.slots.domain.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import gamesoft.slots.domain.model.Bet;
import gamesoft.slots.domain.model.BonusWin;
import gamesoft.slots.domain.model.LineWin;
import gamesoft.slots.domain.model.ModelResult;
import gamesoft.slots.domain.model.ReelSpinResult;
import gamesoft.slots.domain.model.ScatterWin;
import gamesoft.slots.domain.model.SlotSpinResult;
import gamesoft.slots.domain.model.SlotWin;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SlotSpinResultUTest {

	@Mock
	private ReelSpinResult spinResult;
	
	@Mock
	private Bet bet;
	
	private Money money = Money.parse("USD 0.01");
	
	@Mock
	private ModelResult modelResult;
	
	
	private SlotSpinResult result;
	
	@Mock
	private ScatterWin mockScatterWin;
	
	@Mock
	private WinSizeCalculator winSizeCalculator;
	
	private LineWin lineWin1;
	
	private LineWin lineWin2;
	
	private BonusWin mockBonusWin = mock(BonusWin.class);

	@Before
	public void setUp() throws Exception {
		 when(bet.value()).thenReturn(money);
		 lineWin1 = new LineWin(null ,Money.parse("USD 2.50"), 0, null);
		 lineWin2 = new LineWin(null ,Money.parse("USD 0.50"), 0, null);
		result = new SlotSpinResult(bet, spinResult, modelResult, winSizeCalculator);
	}

	private List<? extends SlotWin> mockWins(){
		return Arrays.asList(mockScatterWin, lineWin1, lineWin2, mockBonusWin);		
		
	}

	@Test
	public void testWinsForSubTypeOfSlotWin() {
		 doReturn(mockWins()).when(modelResult).wins();
		 when(bet.value()).thenReturn(money);
		 
		 LineWin actual = result.win(LineWin.class);
		 assertEquals(lineWin1, actual);
	}

	@Test
	public void testWins() {
		doReturn(mockWins()).when(modelResult).wins();
		doReturn(mockWins()).when(modelResult).wins();
		List<? extends SlotWin> expected = Arrays.asList(lineWin1, lineWin2);
		List<? extends SlotWin> actual =  result.wins(LineWin.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testWinSize() {
		Money totalWin = Money.parse("USD 13.00");
		doReturn(mockWins()).when(modelResult).wins();
		when(mockScatterWin.amount()).thenReturn(Money.parse("USD 10.00"));
		when(winSizeCalculator.evaluate(bet, totalWin)).thenReturn(WinSize.MEDIUM);
		
		WinSize expected = WinSize.MEDIUM;
		WinSize actual = result.winSize();
		
		assertEquals(expected, actual);
	}

	@Test
	public void testTotalWin() {
		doReturn(mockWins()).when(modelResult).wins();
		when(mockScatterWin.amount()).thenReturn(Money.parse("USD 10.00"));
		
		Money expected = Money.parse("USD 13.00");
		
		Money actual = result.totalWin();
		assertEquals(expected, actual);
	}

	@Test
	public void testTotalWinClassOfQextendsCashWin() {
		doReturn(mockWins()).when(modelResult).wins();
		
		Money expected = Money.parse("USD 3.00");
		
		Money actual = result.totalWin(LineWin.class);
		assertEquals(expected, actual);
	}

	@Test
	public void testAddMultiplier() {
		doReturn(mockWins()).when(modelResult).wins();
		
		BigDecimal multiplier = new BigDecimal("2");
		result.addMultiplier(multiplier);
		
		verify(mockScatterWin).multiplyBy(multiplier);
		assertEquals(Money.parse("USD 5.00"), lineWin1.amount());
		assertEquals(Money.parse("USD 1.00"), lineWin2.amount());
	}

	@Test
	public void testAnticipations() {
		result.anticipations();
		verify(modelResult).anticipate();
	}

	@Test
	public void testAnticipationWins() {
		result.anticipationWins();
		verify(modelResult).anticipateWins();
	}

	@Test
	public void testHasWin() {
		doReturn(mockWins()).when(modelResult).wins();
		when(mockScatterWin.amount()).thenReturn(Money.parse("USD 10.00"));
		
		assertTrue(result.hasWin());
	}

}
