package gamesoft.slots.domain.model.slot;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
@EqualsAndHashCode(exclude="index")
@SuppressWarnings("PMD.UnusedPrivateField")
public class Symbol implements Weighted {
	private static final long EQUAL_WEIGHT = 1L;
	
	private final int id;
	private final String code;
	
	@Accessors(fluent=false)
	private final boolean wild;
	
	private final Long weight;
	private final int index;
	
	private Symbol(int id, String code, boolean wild, Long weight, int index){
		this.id = id;
		this.code = code;
		this.wild = wild;
		this.weight = weight;
		this.index = index;
	}
	
	public static final Symbol newSymbol(int id, String code, int reelIndex){
		return new Symbol(id, code, false, EQUAL_WEIGHT, reelIndex);
	}
	
	public static final Symbol wildSymbol(int id, String code, int reelIndex){
		return new Symbol(id, code, true, EQUAL_WEIGHT, reelIndex);
	}
	
	public static final Symbol newWeightedSymbol(int id, String code, Long weight, int reelIndex){
		return new Symbol(id, code, false, weight, reelIndex);
	}
	
	public static final Symbol weightedWildSymbol(int id, String code, Long weight, int reelIndex){
		return new Symbol(id, code, true, weight, reelIndex);
	}
}
