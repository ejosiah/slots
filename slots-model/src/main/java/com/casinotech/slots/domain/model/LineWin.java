package com.casinotech.slots.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import org.joda.money.Money;

@Data
@Accessors(fluent=true)
class LineWin implements CashWin, Comparable<LineWin> {
	
	private final PayoutCombo payoutCombo;
	private final Money amount;
	private final int line;
	private final List<SymbolPosition> symbolPositions;
	private BigDecimal multiplier = BigDecimal.ONE;
	private static final String TYPE;
	
	static{
		TYPE = LineWin.class.getSimpleName().replace("Win", "").toUpperCase();
	}
	
	public LineWin(PayoutCombo payoutCombo, Money amount, int line, List<SymbolPosition> symbolPositions){
		this.payoutCombo = payoutCombo;
		this.amount = amount;
		this.line = line;
		this.symbolPositions = symbolPositions;
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

}
