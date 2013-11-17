package gamesoft.slots.casino.adaptor;

import java.util.Currency;

import gamesoft.casino.domain.model.Money;
import gamesoft.casino.domain.model.game.Bet;
import gamesoft.slots.domain.model.slot.SlotBet;
import lombok.Data;

@Data
public class CasinoBetAdaptor implements Bet {
	private final SlotBet bet;
	
	@Override
	public Money value() {
		return new Money(bet.value().getAmount()
				, Currency.getInstance(bet.value().getCurrencyUnit().getCode()));
	}

}
