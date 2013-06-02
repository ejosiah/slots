package com.casinotech.slots.domain.model;

import java.math.BigDecimal;

import org.joda.money.Money;

public interface CashWin extends SlotWin {
	
	Money amount();
	
	void multiplyBy(BigDecimal factor);
	
	BigDecimal multiplier();
}
