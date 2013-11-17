package gamesoft.slots.casino.adaptor;

import gamesoft.casino.domain.model.game.BetLimit;
import gamesoft.casino.domain.model.game.CoinSize;
import gamesoft.casino.domain.model.game.Game;
import gamesoft.casino.domain.model.game.GameAction;
import gamesoft.casino.domain.model.game.GameActionHandler;
import gamesoft.slots.domain.model.slot.Slot;
import gamesoft.slots.domain.model.slot.SlotConfig;

import java.util.List;
import java.util.Map;

public class SlotGame implements Game {
	
	private SlotConfig config;
	private Map<String, GameActionHandler<GameAction>> handlers;
	private SlotGameInstance instance;
	private Slot baseSlot;

	@Override
	public void execute(GameAction gameAction) {
		handlers.get(gameAction).handle(gameAction);
	}

	@Override
	public void initialise() {
		if(instance != null){
			instance.initailize(this);
		}
	}

	@Override
	public List<CoinSize> supporedCoinSizes() {
		return config.supporedCoinSizes();
	}

	@Override
	public List<BetLimit> supportedBetLimits() {
		return config.supportedBetLimits();
	}

	@Override
	public void addGameActionHandler(String action,	GameActionHandler<GameAction> handler) {
		handlers.put(action, handler);
	}

	public Slot baseSlot(){
		return baseSlot;
	}

	@Override
	public Long gameReference() {
		return instance.gameReference();
	}

	@Override
	public Long playerId() {
		return instance.playerId();
	}

}
