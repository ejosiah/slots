package gamesoft.slots.domain.model.slot;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.*;
import gamesoft.slots.domain.model.slot.SlotBet;
import gamesoft.slots.domain.model.slot.Coin;
import gamesoft.slots.domain.model.slot.Slot;

import java.util.ArrayList;
import java.util.List;

import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BetUTest {
	
	private static final List<Integer> TEN_LINES;
	private static final List<Integer> TWENTY_FIVE_LINES;	
	private static final Coin COIN_SIZE = Coin.parse("USD 0.01");
	private SlotBet bet = new SlotBet(TEN_LINES, COIN_SIZE);
	
	@Mock
	private Slot mockSlot;
	
	static{
		TEN_LINES = new ArrayList<Integer>();
		TWENTY_FIVE_LINES = new ArrayList<Integer>();
		for(int i = 0; i < 25; i++){
			if(i < 10){
				TEN_LINES.add(1);
			}
			TWENTY_FIVE_LINES.add(1);
		}
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testBetValue() {
		Money expected = Money.parse("USD 0.10");
		assertThat(bet.value(), equalTo(expected));
	}
	
	@Test
	public void testBetForLine(){
		int line1 = 0;
		Money oneCent = Money.parse("USD 0.01");
		assertThat(bet.betFor(line1), equalTo(oneCent));
	}
	
	@Test
	public void testNumberOfCoinsForLine(){
		int line1 = 0;
		Integer one = 1;
		assertThat(bet.numberOfCoinsFor(line1), equalTo(one));
	}
	
	@Test
	public void testIsMaxBet(){
		when(mockSlot.maxLineBets()).thenReturn(TEN_LINES);
		
		assertTrue(bet.isMaxFor(mockSlot));
		
		when(mockSlot.maxLineBets()).thenReturn(TWENTY_FIVE_LINES);
		assertFalse(bet.isMaxFor(mockSlot));
		
	}

}
