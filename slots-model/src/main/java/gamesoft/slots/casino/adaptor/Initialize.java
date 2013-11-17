package gamesoft.slots.casino.adaptor;

import gamesoft.casino.domain.model.game.Bet;
import gamesoft.casino.domain.model.game.GameAction;

public class Initialize implements GameAction{

	@Override
	public boolean requiresWager() {
		return false;
	}

	@Override
	public Bet bet() {
		throw new UnsupportedOperationException();
	}

}
