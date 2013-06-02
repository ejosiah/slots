package com.casinotech.slots.domain.model;

import org.joda.money.Money;

public interface WinSizeCalculator {
	
	WinSize calulate(Money bet, Money totalWin);
	
}
