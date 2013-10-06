package gamesoft.slots.domain.model;

import org.joda.money.Money;

public interface WinSizeCalculator {
	
	WinSize evaluate(Bet bet, Money totalWin);
	
}
