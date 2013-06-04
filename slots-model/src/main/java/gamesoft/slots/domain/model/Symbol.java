package gamesoft.slots.domain.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
@EqualsAndHashCode(exclude="index")
public class Symbol implements Weighted {
	private static final long EQUAL_WEIGHT = 1L;
	
	private final int id;
	private final String code;
	
	@Accessors(fluent=false)
	private final boolean wild;
	
	private final Long weight;
	private final int index;
	private final Order order;
	
	private Symbol(int id, String code, boolean wild, Long weight, int index, Order order){
		this.id = id;
		this.code = code;
		this.wild = wild;
		this.weight = weight;
		this.index = index;
		this.order = order;
	}
	
	public static final Symbol newSymbol(int id, String code, int reelIndex){
		return new Symbol(id, code, false, EQUAL_WEIGHT, reelIndex, Order.FROM_LEFT);
	}
	
	public static final Symbol wildSymbol(int id, String code, int reelIndex){
		return new Symbol(id, code, true, EQUAL_WEIGHT, reelIndex, Order.FROM_LEFT);
	}
	
	public static final Symbol newWeightedSymbol(int id, String code, Long weight, int reelIndex){
		return new Symbol(id, code, false, weight, reelIndex, Order.FROM_LEFT);
	}
	
	public static final Symbol weightedWildSymbol(int id, String code, Long weight, int reelIndex){
		return new Symbol(id, code, true, weight, reelIndex, Order.FROM_LEFT);
	}
	
	public static final Symbol newSymbolFromRight(int id, String code, int reelIndex){
		return new Symbol(id, code, false, EQUAL_WEIGHT, reelIndex, Order.FROM_RIGHT);
	}
	
	public static final Symbol wildSymbolFromRight(int id, String code, int reelIndex){
		return new Symbol(id, code, true, EQUAL_WEIGHT, reelIndex, Order.FROM_RIGHT);
	}
	
	public static Symbol newWeightedSymbolFromRight(int id, String code, Long weight, int reelIndex){
		return new Symbol(id, code, false, weight, reelIndex, Order.ANY_DIRECTION);
	}
	
	public static final Symbol weightedWildSymbolFromRight(int id, String code, Long weight, int reelIndex){
		return new Symbol(id, code, true, weight, reelIndex, Order.ANY_DIRECTION);
	}
	
	public static final Symbol newSymbolFromAnyDirection(int id, String code, int reelIndex){
		return new Symbol(id, code, false, EQUAL_WEIGHT, reelIndex, Order.ANY_DIRECTION);
	}
	
	public static final Symbol wildSymbolFromAnyDirection(int id, String code, int reelIndex){
		return new Symbol(id, code, true, EQUAL_WEIGHT, reelIndex, Order.ANY_DIRECTION);
	}
	
	public static Symbol newWeightedSymbolFromAnyDirection(int id, String code, Long weight, int reelIndex){
		return new Symbol(id, code, false, weight, reelIndex, Order.ANY_DIRECTION);
	}
	
	public static final Symbol weightedWildSymbolFromAnyDirection(int id, String code, Long weight, int reelIndex){
		return new Symbol(id, code, true, weight, reelIndex, Order.ANY_DIRECTION);
	}
}
