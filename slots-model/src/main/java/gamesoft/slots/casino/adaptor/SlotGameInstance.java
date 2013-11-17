package gamesoft.slots.casino.adaptor;

import lombok.Delegate;
import gamesoft.casino.domain.model.game.CoinSize;
import gamesoft.casino.domain.model.game.Game;
import gamesoft.casino.domain.model.game.GameInstance;

public class SlotGameInstance implements GameInstance {
	private Long gameReference;
	private Long playerId;
	


	@Override
	public void initailize(Game game) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long gameReference() {
		return gameReference;
	}

	@Override
	public CoinSize currentCoinSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T stateData(Class<T> type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasState() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Long playerId() {
		return playerId;
	}
	

}
