package com.casinotech.slots.domain.model;

public interface BonusGame<T> extends Feature<T> {
	
	BonusResult play();
}
