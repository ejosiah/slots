package com.casinotech.slots.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
public class SymbolPosition{
	private final Symbol symbol;
	private final int reelIndex;
	private final int symbolIndex;

}
