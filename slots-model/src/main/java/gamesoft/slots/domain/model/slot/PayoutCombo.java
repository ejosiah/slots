package gamesoft.slots.domain.model.slot;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class PayoutCombo implements Comparable<PayoutCombo> {
	private static final List<Symbol> NO_SYMBOLS = new ArrayList<>();
	public static final PayoutCombo NO_PAYOUT = new PayoutCombo(null, null, Win.NO_WIN, NO_SYMBOLS, true, null, null, null);
	private final Integer id;
	private final Integer occurrence;
	private final Win win;
	private final List<Symbol> symbols;
	private final boolean ignoreWild;
	private final Integer line;
	private final Integer coins;
	private final Direction payoutDirection;

	
	private PayoutCombo(Integer id, Integer occurrence, Win win, List<Symbol> symbols, boolean ignoreWild, Integer line, Integer coins, Direction order){
		this.id = id;
		this.occurrence = occurrence;
		this.win = win;
		this.symbols = new ArrayList<>(symbols);
		this.ignoreWild = ignoreWild;
		this.line = line;
		this.coins = coins;
		this.payoutDirection = order;
	}
	
	public static PayoutCombo createCombo(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, null, null, Direction.FROM_LEFT);
	}
	
	public static PayoutCombo ignoreWildCombo(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), true, null, null, Direction.FROM_LEFT);
	}
	
	public static PayoutCombo lineSpecificCombo(Integer id, Integer occurrence, Win win, Symbol symbol, int line){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, line, null, Direction.FROM_LEFT);
	}

	public static PayoutCombo coinsSpecificCombo(Integer id, Integer occurrence, Win win, Symbol symbol, int coins){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, null, coins, Direction.FROM_RIGHT);
	}

	public static PayoutCombo mixedCombo(Integer id, Integer occurrence, Win win, List<Symbol> symbols){
		return new PayoutCombo(id, occurrence, win, symbols, false, null, null, Direction.FROM_LEFT);
	}
	
	public static PayoutCombo createComboFromRight(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, null, null, Direction.FROM_RIGHT);
	}
	
	public static PayoutCombo ignoreWildComboFromRight(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), true, null, null, Direction.FROM_RIGHT);
	}
	
	public static PayoutCombo lineSpecificComboFromRight(Integer id, Integer occurrence, Win win, Symbol symbol, int line){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, line, null, Direction.FROM_RIGHT);
	}
	
	public static PayoutCombo coinsSpecificComboFromRight(Integer id, Integer occurrence, Win win, Symbol symbol, int coins){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, null, coins, Direction.FROM_RIGHT);
	}
	
	public static PayoutCombo mixedComboFromRight(Integer id, Integer occurrence, Win win, List<Symbol> symbols){
		return new PayoutCombo(id, occurrence, win, symbols, false, null, null, Direction.FROM_RIGHT);
	}
	
	public static PayoutCombo createComboFromAnyDirection(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, null, null, Direction.ANY);
	}
	
	public static PayoutCombo ignoreWildComboFromAnyDirection(Integer id, Integer occurrence, Win win, Symbol symbol){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), true, null, null, Direction.ANY);
	}
	
	public static PayoutCombo lineSpecificComboFromAnyDirection(Integer id, Integer occurrence, Win win, Symbol symbol, int line){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, line, null, Direction.ANY);
	}
	
	public static PayoutCombo coinsSpecificComboFromAnyDirection(Integer id, Integer occurrence, Win win, Symbol symbol, int coins){
		return new PayoutCombo(id, occurrence, win, Arrays.asList(symbol), false, null, coins, Direction.ANY);
	}
	
	public static PayoutCombo mixedComboFromAnyDirection(Integer id, Integer occurrence, Win win, List<Symbol> symbols){
		return new PayoutCombo(id, occurrence, win, symbols, false, null, null, Direction.ANY);
	}
	
	public static PayoutCombo progressiveCombo(Integer id, Integer occcurence, Symbol symbol){
		return new PayoutCombo(id, occcurence, Win.PROGRESSSIVE, Arrays.asList(symbol), false, null, null, Direction.FROM_LEFT);
	}
	
	public static PayoutCombo progressiveComboFromRight(Integer id, Integer occcurence, Symbol symbol){
		return new PayoutCombo(id, occcurence, Win.PROGRESSSIVE, Arrays.asList(symbol), false, null, null, Direction.FROM_RIGHT);
	}
	
	public static PayoutCombo progressiveComboFromAnyDirection(Integer id, Integer occcurence, Symbol symbol){
		return new PayoutCombo(id, occcurence, Win.PROGRESSSIVE, Arrays.asList(symbol), false, null, null, Direction.ANY);
	}
	
	public boolean matches(List<Symbol> symbols, Integer...optional){
		switch(optional.length){
			case 0: return count(symbols) == occurrence;
			case 1: return count(symbols) == occurrence && line.equals(optional[0]);
			case 2: return count(symbols) == occurrence && coins.equals(optional[1]);
			default: return false;
		}
	}

	private int count(List<Symbol> symbols) {
		switch(payoutDirection()){
		case FROM_LEFT :
			return countFromLeft(symbols);
		case FROM_RIGHT :
			return countFromRight(symbols);
		case ANY :
			return countFromAnyDirection(symbols);
		default:
			throw new IllegalArgumentException("Unknown order");
		}
	}
	
	private int countFromLeft(List<Symbol> symbols){
		for(int i = 0; i < symbols.size(); i++){
			Symbol currentSymbol = symbols.get(i);
			if(noMatchFor(currentSymbol)){
				return i;
			}
		}
		return symbols.size();
	}
	
	private int countFromRight(List<Symbol> symbols){
		for(int start = symbols.size() - 1, i = start; i >= 0; i--){
			Symbol currentSymbol = symbols.get(i);
			if(noMatchFor(currentSymbol)){
				return start - i;
			}
		}
		return symbols.size();
	}
	
	private int countFromAnyDirection(List<Symbol> symbols){
		int occurrences = countFromLeft(symbols);
		return occurrences != this.occurrence ? countFromRight(symbols) : occurrences;
	}
	
	private boolean noMatchFor(Symbol symbol){
		return ((ignoreWild && symbol.isWild()) 
				|| !this.contains(symbol) && !symbol.isWild());
	}
	
	private boolean contains(Symbol symbol){
		return symbols.contains(symbol);
	}

	@Override
	public int compareTo(PayoutCombo other) {
		int result = this.win().compareTo(other.win());
		return result == 0 ? occurrence.compareTo(other.occurrence) : result;
	}

	public boolean hasWild() {
		for(Symbol symbol : symbols){
			if(symbol.isWild()){
				return true;
			}
		}
		return false;
	}
	
	@SneakyThrows(CloneNotSupportedException.class)
	public Win apply(BigDecimal wildMultiplier){
		if(hasWild()){
			return win.clone();
		}
		return win.multiply(wildMultiplier);
	}

}
