package com.casinotech.slots.domain.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent=true)
@ToString
public class Query {
	private final Integer id;
	private final List<List<Symbol>> symbols;
	private final Integer line;
	private final Integer numberOfCoins;
	private final Lines lineHelper;
	private final BigDecimal wildMultiplier;
	
	private Query(Integer id, List<List<Symbol>> symbols, Integer line, Integer numberOfCoins
			, Lines lineHelper, BigDecimal wildMultiplier){
		this.id = id;
		this.symbols = symbols;
		this.line = line;
		this.numberOfCoins = numberOfCoins;
		this.lineHelper = lineHelper;
		this.wildMultiplier = wildMultiplier;
	}
	
	public static Query with(List<List<Symbol>> symbols, Integer line, Integer numberOfCoins
			, Lines lineHelper, BigDecimal wildMultiplier){
		return new Query(null, symbols, line, numberOfCoins, lineHelper, wildMultiplier);
	}
	
	public static Query with(List<List<Symbol>> symbols, Integer line, Integer numberOfCoins
			, Lines lineHelper){
		return new Query(null, symbols, line, numberOfCoins, lineHelper, null);
	}
	
	public static Query with(List<List<Symbol>> symbols, Integer line, Integer numberOfCoins){
		return new Query(null, symbols, line, numberOfCoins, null, null);
	}
	
	public static Query with(List<List<Symbol>> symbols){
		return new Query(null, symbols, null, null, null, null);
	}
	
	public static Query with(Integer id){
		return new Query(id, null, null, null, null, null);
	}
}
