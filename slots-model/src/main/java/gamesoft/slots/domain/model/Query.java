package gamesoft.slots.domain.model;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class Query {
	public static final Query NULL = new Query(null, null, null, null, null);
	private final Integer id;
	private final List<Symbol> symbols;
	private final Integer line;
	private final Integer numberOfCoins;
	private final BigDecimal wildMultiplier;
	
	private Query(Integer id, List<Symbol> symbols, Integer line, Integer numberOfCoins, BigDecimal wildMultiplier){
		this.id = id;
		this.symbols = symbols;
		this.line = line;
		this.numberOfCoins = numberOfCoins;
		this.wildMultiplier = wildMultiplier;
	}
	
	public static Query with(List<Symbol> symbols, Integer line, Integer numberOfCoins, BigDecimal wildMultiplier){
		return new Query(null, symbols, line, numberOfCoins, wildMultiplier);
	}
	
	public static Query with(List<Symbol> symbols, Integer line, Integer numberOfCoins){
		return new Query(null, symbols, line, numberOfCoins, null);
	}
	
	public static Query with(List<Symbol> symbols){
		return new Query(null, symbols, null, null, null);
	}
	
	public static Query with(Integer id){
		return new Query(id, null, null, null, null);
	}
}
