package gamesoft.slots.casino.adaptor.handler;

import java.util.List;

import gamesoft.casino.domain.model.game.GameActionValidator;
import gamesoft.casino.domain.model.game.GameEventPublisher;
import gamesoft.slots.casino.adaptor.SlotGame;
import gamesoft.slots.casino.adaptor.Spin;
import gamesoft.slots.domain.model.slot.SlotSpinResult;
import gamesoft.slots.domain.model.slot.event.SpinEvent;

public class SpinActionHandler extends SlotGameActionHandler<Spin> {

	public SpinActionHandler(SlotGame slotGame) {
		super(slotGame);
	}

	@Override
	public void handle(Spin spin) {
		SlotSpinResult spinResult = slotGame.baseSlot().spin(spin.getSlotBet(), spin.getForcedResult());
		GameEventPublisher.instance().publish(new SpinEvent(slotGame.gameReference(), slotGame.playerId(), spinResult));
	}

	@Override
	public List<GameActionValidator<Spin>> validators() {
		// TODO Auto-generated method stub
		return null;
	}

}
