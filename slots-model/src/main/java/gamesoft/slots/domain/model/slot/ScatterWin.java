package gamesoft.slots.domain.model.slot;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

@Data
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class ScatterWin implements CashWin {
	private static final String TYPE;
	private final Money amount;
	private final List<SymbolPosition> symbolPositions;
	@Getter private BigDecimal multiplier = BigDecimal.ONE;
	
	static{
		TYPE = ScatterWin.class.getSimpleName().replace("Win", "").toUpperCase();
	}
	
	public ScatterWin(Money amount, List<SymbolPosition> symbolPositions){
		this.amount = amount;
		this.symbolPositions = new ArrayList<>(symbolPositions);
	}
	
	@Override
	public List<SymbolPosition> symbolPositions() {
		return Collections.unmodifiableList(symbolPositions);
	}
	
	@Override
	public String type() {
		return TYPE;
	}
	
	public Money amount(){
		return amount.multipliedBy(multiplier, RoundingMode.UNNECESSARY);
	}

	@Override
	public void multiplyBy(BigDecimal factor) {
		multiplier = multiplier.multiply(factor);
		
	}
	
	public static ScatterWin noWin(){
		// TODO get currency unit from context
		return new ScatterWin(Money.zero(CurrencyUnit.USD), new ArrayList<SymbolPosition>());
	}

}
