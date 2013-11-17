package gamesoft.slots.casino.adaptor.handler;

import java.util.List;

import gamesoft.casino.domain.model.game.GameActionValidator;
import gamesoft.slots.casino.adaptor.Initialize;
import gamesoft.slots.casino.adaptor.SlotGame;

public class InitailizeHandler extends SlotGameActionHandler<Initialize> {

	public InitailizeHandler(SlotGame slotGame) {
		super(slotGame);
	}

	@Override
	public void handle(Initialize gameAction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<GameActionValidator<Initialize>> validators() {
		// TODO Auto-generated method stub
		return null;
	}

}
