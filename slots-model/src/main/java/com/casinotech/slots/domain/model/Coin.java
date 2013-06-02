package com.casinotech.slots.domain.model;

import java.math.BigDecimal;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

@ToString
@EqualsAndHashCode
public class Coin implements Comparable<Coin>{
	private final Money value;
	
	private Coin(Money value){
		this.value = value;
	}
	
	public Money moneyValue(){
		return value;
	}

	public static Coin parse(BigDecimal value, CurrencyUnit currency){
		return new Coin(Money.of(currency, value));
	}
	
	public static Coin parse(String amount){
		return new Coin(Money.parse(amount));
	}
	
	public BigDecimal amount(){
		return value.getAmount();
	}

	public int compareTo(Coin another) {
		validate(another);
		return value.compareTo(another.value);
	}
	
	private void validate(Coin coin){
		if(coin.value.getCurrencyUnit() != value.getCurrencyUnit()){
			throw new IllegalArgumentException("Incompatible curreny units"
					+ coin.value.getCurrencyUnit() + ", " + value.getCurrencyUnit());
		}
	}

	public CurrencyUnit currency() {
		return value.getCurrencyUnit();
	}
	
}
