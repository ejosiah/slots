package gamesoft.slots.domain.model.slot;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import gamesoft.slots.domain.model.slot.SlotBet;
import gamesoft.slots.domain.model.slot.ChainModel;
import gamesoft.slots.domain.model.slot.Forcer;
import gamesoft.slots.domain.model.slot.ModelResult;
import gamesoft.slots.domain.model.slot.ReelSpinResult;
import gamesoft.slots.domain.model.slot.ReelSpinResultInterceptorChain;
import gamesoft.slots.domain.model.slot.Reels;
import gamesoft.slots.domain.model.slot.Slot;
import gamesoft.slots.domain.model.slot.SlotConfig;
import gamesoft.slots.domain.model.slot.SlotSpinResult;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SlotUTest {
	
	@Mock
	private SlotBet bet;
	
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
	
	@Mock Forcer forcer;
	
	private Slot slot;

	@Before
	public void setUp() throws Exception {
		slot = new Slot(reels, model, interceptor, config, forcer, false);
	}

	@Test
	public void testSpin() {
		when(reels.spin()).thenReturn(reelSpinResult);
		when(model.result(reelSpinResult, bet)).thenReturn(modelResult);
		when(forcer.spin(interceptor, reels, "")).thenReturn(reelSpinResult);
		when(bet.value()).thenReturn(Money.zero(CurrencyUnit.USD));
		
		SlotSpinResult result = slot.spin(bet, "");
		
		assertEquals(modelResult, result.modelResult());
		assertEquals(reelSpinResult, result.spinResult());
		assertEquals(bet, result.bet());
		
	}
	
	@Ignore
	public void testDualSpinResult(){
		fail("Not yet implemented");
	}

}
