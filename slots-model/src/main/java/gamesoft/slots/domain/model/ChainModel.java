package gamesoft.slots.domain.model;

import java.util.ArrayList;
import java.util.List;

public class ChainModel implements Model {
	protected final List<Model> models;
	
	public ChainModel(List<Model> models){
		this.models = models;
	}

	public ModelResult result(ReelSpinResult reelSpinResult, Bet bet) {
		List<SlotWin> wins = new ArrayList<>();
		List<Boolean> anticipate = new ArrayList<>();
		List<Boolean> anticipateWins = new ArrayList<>();
		
		for(Model model : models){
			ModelResult result = model.result(reelSpinResult, bet);
			wins.addAll(result.wins());
			anticipate.addAll(result.anticipate());
			anticipateWins.addAll(result.anticipateWins());
		}
		
		return new ModelResult(wins, new Anticipate(anticipate, anticipateWins));
	}

}
