package gamesoft.slots.casino.adaptor;

import gamesoft.casino.domain.model.game.Bet;
import gamesoft.casino.domain.model.game.ForceableGameAction;
import gamesoft.slots.domain.model.slot.SlotBet;
import lombok.Data;

@Data
public class Spin extends ForceableGameAction {
	
	private SlotBet slotBet;

	@Override
	public boolean requiresWager() {
		return true;
	}

	@Override
	public Bet bet() {
		return new CasinoBetAdaptor(slotBet);
	}
	
}
