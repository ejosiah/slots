package gamesoft.slots.domain.model;

import java.util.List;

import org.joda.money.Money;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@SuppressWarnings("PMD.UnusedPrivateField")
public class Bet {
	private final List<Integer> lines;
	private final Coin coinSize;
	
	public Bet(List<Integer> lines, Coin coinSize){
		this.lines = lines;
		this.coinSize = coinSize;
	}

	public Money value() {
		Money value = coinSize.moneyValue();
		Money total = Money.zero(value.getCurrencyUnit());
		for(Integer coins : lines){
			total = total.plus(value.multipliedBy(coins));
		}
		return total;
	}

	public Money betFor(int line) {
		Money value = coinSize.moneyValue();
		return value.multipliedBy(lines.get(line));
	}

	public Integer numberOfCoinsFor(int line) {
		return lines.get(line);
	}

	public boolean isMaxFor(Slot slot) {
		return lines.equals(slot.maxLineBets());
	}
	
	
}
