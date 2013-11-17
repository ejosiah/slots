package gamesoft.slots.domain.model.slot;

import static java.util.Collections.unmodifiableList;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

@Data
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
class LineWin implements CashWin, Comparable<LineWin> {
		
	@Getter(AccessLevel.NONE)
	private final PayoutCombo payoutCombo;
	private final Money amount;
	private final Integer line;
	private final List<SymbolPosition> symbolPositions;
	@Getter private BigDecimal multiplier = BigDecimal.ONE;
	private static final String TYPE;
	
	static{
		TYPE = LineWin.class.getSimpleName().replace("Win", "").toUpperCase();
	}

	@Override
	public List<SymbolPosition> symbolPositions() {
		return unmodifiableList(symbolPositions);
	}

	@Override
	public String type() {
		return TYPE;
	}
	
	public Money amount(){
		return amount.multipliedBy(multiplier, RoundingMode.UNNECESSARY);
	}

	@Override
	public int compareTo(LineWin other) {
		return amount.compareTo(other.amount());
	}


	@Override
	public void multiplyBy(BigDecimal factor) {
		multiplier = multiplier.multiply(factor);
		
	}

	public int numberOfSymbols() {
		return payoutCombo.occurrence();
	}

	public static LineWin noWin() {
		// TODO get currencyUnit from context
		return new LineWin(PayoutCombo.NO_PAYOUT, Money.zero(CurrencyUnit.USD), null, new ArrayList<SymbolPosition>());
	}
	
}
