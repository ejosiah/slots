package gamesoft.slots.domain.model.slot;

import gamesoft.casino.domain.model.game.BetLimit;
import gamesoft.casino.domain.model.game.CoinSize;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent=true)
@AllArgsConstructor
@SuppressWarnings("PMD.UnusedPrivateField")
public class SlotConfig {
	private final List<Integer> defaultLinesBets;
	private final List<Integer> maxLineBets;
	private final List<List<Symbol>> startingSymbols;
	private final Map<Integer, Symbol> symbols;
	private final List<Integer> defaultLines;
	private final List<Integer> maxLines;
	private final List<CoinSize> supporedCoinSizes;
	private final List<BetLimit> supportedBetLimits;
	private final int numberOfLines;
	private final int maxCoinsPerLine;
	
	// Reel config
	private final Class<Spinner> spinnerType;
	private final List<Object[][]> reels;
	
	private final WinSizeCalculator winSizeCalculator;
}
