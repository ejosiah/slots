package com.casinotech.slots.domain.model;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static com.casinotech.slots.domain.model.Anticipate.NO_ANTICIPATE;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ChainModelUTest {

	@Mock private Model model1;
	@Mock private Model model2;
	@Mock private Model model3;
	@Mock private ReelSpinResult reelSpinResult;
	@Mock private Bet bet;
	@Mock private LineWin lineWin;
	@Mock private ScatterWin scatterWin;
	@Mock private BonusWin bonusWin;
	@Mock private Anticipate anticipate;
	
	private ChainModel chainModel;
	
	
	@Before
	public void setUp() throws Exception {
		chainModel = new ChainModel(Arrays.asList(model1, model2, model3));
	}

	@Test
	public void test() {
		when(model1.result(reelSpinResult, bet)).thenReturn(
				new ModelResult(new ArrayList<SlotWin>(Arrays.asList(lineWin)), anticipate));
		when(model2.result(reelSpinResult, bet)).thenReturn(
				new ModelResult(new ArrayList<SlotWin>(Arrays.asList(scatterWin)), NO_ANTICIPATE));
		when(model3.result(reelSpinResult, bet)).thenReturn(
				new ModelResult(new ArrayList<SlotWin>(Arrays.asList(bonusWin)), NO_ANTICIPATE));
		when(anticipate.anticipate()).thenReturn(new ArrayList<Boolean>());
		when(anticipate.win()).thenReturn(new ArrayList<Boolean>());
		
		ModelResult result = chainModel.result(reelSpinResult, bet);
		
		assertTrue(result.wins().containsAll(Arrays.asList(lineWin, scatterWin, bonusWin)));
	}

}
