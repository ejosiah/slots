package gamesoft.slots.domain.model.slot;

import org.joda.money.Money;

public interface WinSizeCalculator {
	
	WinSize evaluate(SlotBet bet, Money totalWin);
	
}
