package gamesoft.slots.casino.adaptor.handler;

import gamesoft.casino.domain.model.game.GameAction;
import gamesoft.casino.domain.model.game.GameActionHandler;
import gamesoft.slots.casino.adaptor.SlotGame;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class SlotGameActionHandler<T extends GameAction> implements GameActionHandler<T> {
	protected SlotGame slotGame;
}
