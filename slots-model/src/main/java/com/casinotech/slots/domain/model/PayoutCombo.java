package com.casinotech.slots.domain.model;

import java.util.List;

import com.casinotech.random.RandomNumberGenerator;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
public class PayoutCombo implements Comparable<PayoutCombo> {
	public static final PayoutCombo NO_PAYOUT = new PayoutCombo(null, null, Win.NO_WIN, null, true);
	private final Integer id;
	private final Integer occurrence;
	private final Win win;
	private final Symbol symbol;
	private final boolean ignoreWild;
	
	private PayoutCombo(Integer id, Integer occurrence, Win win, Symbol symbol, boolean ignoreWild){
		this.id = id;
		this.occurrence = occurrence;
		this.win = win;
		this.symbol = symbol;
		this.ignoreWild = ignoreWild;
	}
	
	public static PayoutCombo createCombo(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, symbol, false);
	}
	
	public static PayoutCombo ignoreWild(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, symbol, true);
	}
	
	public boolean matchs(List<Symbol> symbols){
		return count(symbols) == occurrence;
	}

	private int count(List<Symbol> symbols) {
		boolean fromLeft = true;
		boolean fromRight = !fromLeft;
		
		switch(symbol.order()){
		case FROM_LEFT :
			return count(symbols, fromLeft);
		case FROM_RIGHT :
			return count(symbols, fromRight);
		case ANY_DIRECTION :
			return count(symbols, RNGHolder.RNG().nextBoolean());
		default:
			throw new IllegalArgumentException("Unknown order");
		}
	}

	private int count(List<Symbol> symbols, boolean fromLeft) {
		int size = symbols.size();
		int start = fromLeft ? 0 : size - 1;
		
		for(int i = start; condition(fromLeft, i, size);){
			if((ignoreWild && symbols.get(i).isWild()) 
					|| !symbols.get(i).equals(symbol) && !symbols.get(i).isWild()){
				return i;
			}
			i = fromLeft ? i+1 : i-1;
		}
		return symbols.size();
	}
	
	private boolean condition(boolean fromLeft, int index, int size){
		return fromLeft ? index < size : index >= 0;
	}


	@Override
	public int compareTo(PayoutCombo other) {
		return this.win().compareTo(other.win());
	}
}
