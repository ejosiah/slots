package gamesoft.slots.domain.model;

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
	
	@Delegate
	private final SlotConfig config;
	
	Slot(){
		this(null, null, null, null, false);
	}
	
	public Slot(Reels reels, ChainModel model, ReelSpinResultInterceptorChain reelResultInterceptor
			, SlotConfig config, boolean dualStateSpin){
		this.reels = reels;
		this.model = model;
		this.reelResultInterceptor = reelResultInterceptor;
		this.dualStateSpin = dualStateSpin;
		this.config = config;
	}
	
	public SlotSpinResult spin(Bet bet, Forcer forcer){
		ReelSpinResult spinResult = forcer.spin(reelResultInterceptor, reels);
		ModelResult modelResult = model.result(spinResult, bet);
		
		return new SlotSpinResult(bet, spinResult, modelResult, winSizeCalculator());
	}


}
