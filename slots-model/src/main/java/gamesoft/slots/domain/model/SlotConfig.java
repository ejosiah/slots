package gamesoft.slots.domain.model;

import java.util.List;

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
	private final int numberOfLines;
	private final int maxCoinsPerLine;
	
	// Reel config
	private final Class<Spinner> spinnerType;
	private final List<Object[][]> reels; 
}
