package gamesoft.slots.domain.model.slot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.joda.money.Money;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@AllArgsConstructor
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class SlotModel implements Model {
	private final PayoutCombos payoutCombos;
	private final Lines lines;
	private final int noOfLines;
	private final BigDecimal wildMultiplier;
	
	
	public SlotModel(PayoutCombos payoutCombos, Lines lines, int noOfLines){
		this(payoutCombos, lines, noOfLines, BigDecimal.ONE);
	}

	public ModelResult result(ReelSpinResult reelSpinResult, SlotBet bet) {
		List<LineWin> wins = wins(reelSpinResult, bet);
		return new ModelResult(wins, anticipate(wins, reelSpinResult));
	}

	private List<LineWin> wins(ReelSpinResult reelSpinResult, SlotBet bet) {
		List<LineWin> wins = new ArrayList<>();
		for(int i = 0; i < noOfLines(); i++){
			LineWin lineWin = lineWin(bet, reelSpinResult, i);
			if(lineWin != null){
				lineWin.multiplier(wildMultiplier);
				wins.add(lineWin);
			}
		}
		return wins;
	}

	private LineWin lineWin(SlotBet bet, ReelSpinResult reelSpinResult, int line) {
		List<Symbol> symbolsOnLine = lines.symbolsForLine(line, reelSpinResult.reels());
		int numberOfCoins = bet.numberOfCoinsFor(line);
		Query thisQuery = Query.with(symbolsOnLine, line, numberOfCoins, wildMultiplier);
		PayoutCombo payoutCombo = payoutCombos.getPayoutComboFor(thisQuery);
		
		if(payoutCombo == PayoutCombo.NO_PAYOUT){
			return null;
		}
		Money totalWin = bet.betFor(line).multipliedBy(payoutCombo.win().size(), RoundingMode.UNNECESSARY);
		List<SymbolPosition> symbolPositions = symbolPositions(payoutCombo, reelSpinResult.reels(), line);
		
		return new LineWin(payoutCombo, totalWin, line, symbolPositions);
	}

	private List<SymbolPosition> symbolPositions(PayoutCombo payoutCombo, List<List<Symbol>> fromReels, int line) {
		List<SymbolPosition> symbolPositions = new ArrayList<>();
		List<Symbol> symbolsOnLine = lines.symbolsForLine(line, fromReels);
		
		for(int i = 0; i < payoutCombo.occurrence(); i++){
			Symbol symbolOnLine = symbolsOnLine.get(i);
			int symbolIndex = lines.symbolPosition(line, i);
			SymbolPosition symbolPosition = new SymbolPosition(symbolOnLine, i, symbolIndex);
			symbolPositions.add(symbolPosition);
		}
		return symbolPositions;
	}

	private Anticipate anticipate(List<LineWin> wins, ReelSpinResult reelSpinResult) {
		for(LineWin win : wins){
			if(win.numberOfSymbols() > 3){  // TODO make anticipatin dynamie
				List<Boolean> anticipate = new ArrayList<>(Arrays.asList(false, false, false, false, true));
				boolean aWin = win.numberOfSymbols() == 5;
				List<Boolean> anticipateWin = new ArrayList<>(Arrays.asList(false, false, false, false, aWin));
				return new Anticipate(anticipate, anticipateWin);
			}
		}
		return Anticipate.NONE;
	}

}
