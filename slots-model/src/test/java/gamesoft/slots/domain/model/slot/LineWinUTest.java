package gamesoft.slots.domain.model.slot;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import gamesoft.slots.domain.model.slot.LineWin;
import gamesoft.slots.domain.model.slot.PayoutCombo;
import gamesoft.slots.domain.model.slot.SymbolPosition;

import java.math.BigDecimal;
import java.util.List;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class LineWinUTest {
	
	@Mock PayoutCombo payoutCombo;
	@Mock List<SymbolPosition> symbolPositions;
	Money amount1 = Money.parse("USD 50");
	Money amount2 = Money.parse("USD 100");
	int line = 1;
	
	LineWin lineWin;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testType() {
		LineWin lineWin = new LineWin(payoutCombo, amount1, line, symbolPositions);
		String expected = "LINE";
		String actual = lineWin.type();
		assertEquals(expected, actual);
	}

	@Test
	public void testCompareTo() {
		LineWin lineWin1 = new LineWin(payoutCombo, amount1, line, symbolPositions);
		LineWin lineWin2 = new LineWin(payoutCombo, amount2, line, symbolPositions);
		
		assertTrue(lineWin1.compareTo(lineWin2) < 0);
	}

	@Test
	public void testMultiplyBy() {
		final BigDecimal MULTIPLIER = new BigDecimal("2");
		Money amount = Money.parse("USD 50");
		
		LineWin lineWin = new LineWin(payoutCombo, amount, line, symbolPositions);
		assertEquals(amount, lineWin.amount());
		
		lineWin.multiplyBy(MULTIPLIER);
		
		Money expected = Money.parse("USD 100");
		Money actual = lineWin.amount();
		assertEquals(expected, actual);
	}

}
