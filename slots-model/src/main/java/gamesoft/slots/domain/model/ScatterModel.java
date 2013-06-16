package gamesoft.slots.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.money.Money;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent=true)
public class ScatterModel implements Model{
	
	private final List<Integer> wins;
	private final Symbol scatterSymbol;

	public ModelResult result(ReelSpinResult reelSpinResult, Bet bet) {
		List<ScatterWin> wins = wins(reelSpinResult, bet);
		return new ModelResult(wins, anticipate(reelSpinResult, wins));
	}

	private List<ScatterWin> wins(ReelSpinResult reelSpinResult, Bet bet) {
		int noOfScatters = reelSpinResult.count(scatterSymbol);
		long multiplier = wins.get(noOfScatters);
		Money win = bet.value().multipliedBy(multiplier);
		
		List<ScatterWin> wins = new ArrayList<>();
		if(win.isPositive()){
			List<SymbolPosition> symbolPositions = reelSpinResult.findPositionsOf(scatterSymbol);
			wins.add(new ScatterWin(win, symbolPositions));
			
		}
		return wins;
	}

	private Anticipate anticipate(ReelSpinResult reelSpinResult, List<ScatterWin> wins) {
		boolean scatterWin = !wins.isEmpty();
		if(scatterWin){
			boolean shouldAnticipate = true;
			int limit = reelSpinResult.noOfReels() - 1;
			for(int i = 0; i < limit; i++){
				shouldAnticipate = reelSpinResult.contains(i, scatterSymbol) && shouldAnticipate;
			}
			int lastReel = reelSpinResult.noOfReels() - 1;
			boolean scatterOnLastReel = reelSpinResult.contains(lastReel, scatterSymbol);
			// TODO make anticipatin dynamie
			List<Boolean> anticipate = new ArrayList<>(Arrays.asList(false, false, false, false, shouldAnticipate));
			List<Boolean> anticipateWin = new ArrayList<>(Arrays.asList(false, false, false, false, scatterOnLastReel && scatterWin));
			return new Anticipate(anticipate, anticipateWin);
		}
		return Anticipate.NONE;
	}

}
