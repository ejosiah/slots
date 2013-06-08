package gamesoft.slots.domain.model;

import gamesoft.random.RandomNumberGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
public class PayoutCombo implements Comparable<PayoutCombo> {
	private static final List<Symbol> NO_SYMBOLS = new ArrayList<>();
	public static final PayoutCombo NO_PAYOUT = new PayoutCombo(null, null, Win.NO_WIN, NO_SYMBOLS, true, null, null);
	private final Integer id;
	private final Integer occurrence;
	private final Win win;
	private final List<Symbol> symbols;
	private final boolean ignoreWild;
	private final Integer line;
	private final Integer coins;

	
	private PayoutCombo(Integer id, Integer occurrence, Win win, List<Symbol> symbols, boolean ignoreWild, Integer line, Integer coins){
		this.id = id;
		this.occurrence = occurrence;
		this.win = win;
		this.symbols = new ArrayList<>(symbols);
		this.ignoreWild = ignoreWild;
		this.line = line;
		this.coins = coins;
	}
	
	public static PayoutCombo createCombo(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, null, null);
	}
	
	public static PayoutCombo ignoreWildCombo(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), true, null, null);
	}
	
	public static PayoutCombo lineSpecificCombo(Integer id, Integer occurrence, Win win, Symbol symbol, int line){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, line, null);
	}

	public static PayoutCombo coinsSpecificCombo(Integer id, Integer occurrence, Win win, Symbol symbol, int coins){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, null, coins);
	}

	public static PayoutCombo mixedCombo(Integer id, Integer occurrence, Win win, List<Symbol> symbols){
		return new PayoutCombo(id, occurrence, win, symbols, false, null, null);
		
	}
	
	public boolean matches(List<Symbol> symbols, Integer...optional){
		switch(optional.length){
			case 0: return count(symbols) == occurrence;
			case 1: return count(symbols) == occurrence && line == optional[0];
			case 2: return count(symbols) == occurrence && coins == optional[1];
			default: return false;
		}
	}

	private int count(List<Symbol> symbols) {
		switch(symbols.get(0).order()){
		case FROM_LEFT :
			return countFromLeft(symbols);
		case FROM_RIGHT :
			return countFromRight(symbols);
		case ANY_DIRECTION :
			return countFromAnyDirection(symbols);
		default:
			throw new IllegalArgumentException("Unknown order");
		}
	}
	
	private int countFromLeft(List<Symbol> symbols){
		for(int i = 0; i < symbols.size(); i++){
			Symbol currentSymbol = symbols.get(i);
			if(NoMatchFor(currentSymbol)){
				return i;
			}
		}
		return symbols.size();
	}
	
	private int countFromRight(List<Symbol> symbols){
		for(int start = symbols.size() - 1, i = start; i >= 0; i--){
			Symbol currentSymbol = symbols.get(i);
			if(NoMatchFor(currentSymbol)){
				return start - i;
			}
		}
		return symbols.size();
	}
	
	private int countFromAnyDirection(List<Symbol> symbols){
		int occurrence = countFromLeft(symbols);
		return occurrence != this.occurrence ? countFromRight(symbols) : occurrence;
	}
	
	private boolean NoMatchFor(Symbol symbol){
		return ((ignoreWild && symbol.isWild()) 
				|| !this.contains(symbol) && !symbol.isWild());
	}

	private int count(List<Symbol> symbols, boolean fromLeft) {
		int size = symbols.size();
		int start = fromLeft ? 0 : size - 1;
		
		for(int i = start; condition(fromLeft, i, size);){
			Symbol currentSymbol = symbols.get(i);
			if((ignoreWild && currentSymbol.isWild()) 
					|| !this.contains(currentSymbol) && !currentSymbol.isWild()){
				return fromLeft ? i : start - i;
			}
			i = fromLeft ? i+1 : i-1;
		}
		return symbols.size();
	}
	
	private boolean contains(Symbol symbol){
		return symbols.contains(symbol);
	}
	
	private boolean condition(boolean fromLeft, int index, int size){
		return fromLeft ? index < size : index >= 0;
	}


	@Override
	public int compareTo(PayoutCombo other) {
		return this.win().compareTo(other.win());
	}

	public boolean hasWild() {
		for(Symbol symbol : symbols){
			if(symbol.isWild()){
				return true;
			}
		}
		return false;
	}
	
	public Win apply(BigDecimal wildMultiplier){
		if(hasWild()){
			return win.clone();
		}
		return win.multiply(wildMultiplier);
	}

}
