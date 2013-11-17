package gamesoft.slots.domain.model.slot;

import java.math.BigDecimal;

import org.joda.money.Money;

public interface CashWin extends SlotWin {
	
	Money amount();
	
	void multiplyBy(BigDecimal factor);
	
	BigDecimal multiplier();
}
