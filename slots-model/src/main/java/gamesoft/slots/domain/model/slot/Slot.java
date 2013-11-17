package gamesoft.slots.domain.model.slot;

import lombok.AccessLevel;
import lombok.Delegate;
import lombok.Getter;

@Getter(AccessLevel.PACKAGE)
@SuppressWarnings("PMD.UnusedPrivateField")
public class Slot {
	private final Reels reels;
	private final ChainModel model;
	private final boolean dualStateSpin;
	private final ReelSpinResultInterceptorChain reelResultInterceptor;
	private Forcer forcer;
	
	@Delegate
	private final SlotConfig config;
	
	Slot(){
		this(null, null, null, null,null, false);
	}
	
	public Slot(Reels reels, ChainModel model, ReelSpinResultInterceptorChain reelResultInterceptor
			, SlotConfig config, Forcer forcer, boolean dualStateSpin){
		this.reels = reels;
		this.model = model;
		this.reelResultInterceptor = reelResultInterceptor;
		this.config = config;
		this.forcer = forcer;
		this.dualStateSpin = dualStateSpin;
	}
	
	public SlotSpinResult spin(SlotBet bet, String result){
		ReelSpinResult spinResult = forcer.spin(reelResultInterceptor, reels, result);
		ModelResult modelResult = model.result(spinResult, bet);
		
		return new SlotSpinResult(bet, spinResult, modelResult, winSizeCalculator());
	}


}
