package com.casinotech.slots.domain.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SlotUTest {
	
	@Mock
	private Bet bet;
	
	@Mock
	private Reels reels;
	
	@Mock
	private ReelSpinResult reelSpinResult;
	
	@Mock
	private SlotConfig config;
	
	@Mock
	private ChainModel model;
	
	@Mock
	private ModelResult modelResult;
	
	@Mock
	private ReelSpinResultInterceptorChain interceptor;
	
	private Slot slot;

	@Before
	public void setUp() throws Exception {
		slot = new Slot(reels, model, interceptor, config, false);
	}

	@Test
	public void testSpin() {
		when(reels.spin()).thenReturn(reelSpinResult);
		when(model.result(reelSpinResult, bet)).thenReturn(modelResult);
		when(interceptor.intercept(reelSpinResult)).thenReturn(reelSpinResult);
		when(bet.value()).thenReturn(Money.zero(CurrencyUnit.USD));
		
		SlotSpinResult result = slot.spin(bet);
		verify(interceptor).intercept(reelSpinResult);
		assertEquals(modelResult, result.modelResult());
		assertEquals(reelSpinResult, result.spinResult());
		assertEquals(bet, result.bet());
		
	}
	
	@Test
	public void testSpinForceResult(){
		fail("Not yet implemented!");
	}
	
	@Test
	public void testDualSpinResult(){
		fail("Not yet implemented");
	}

}
