package gamesoft.slots.domain.model.slot;

public interface Model {
	
	ModelResult result(ReelSpinResult reelSpinResult, SlotBet bet);
}
