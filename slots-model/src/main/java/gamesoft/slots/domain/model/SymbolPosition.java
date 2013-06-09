package gamesoft.slots.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class SymbolPosition{
	private final Symbol symbol;
	private final int reelIndex;
	private final int symbolIndex;

}
