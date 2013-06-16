package gamesoft.slots.domain.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@EqualsAndHashCode
@AllArgsConstructor
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class ReelSpinResult {
	private final List<List<Symbol>> initialReels;
	private final List<List<Symbol>> finalReels;
	private final boolean dualState;
	
	public ReelSpinResult(List<List<Symbol>> initialReels, List<List<Symbol>> finalReels){
		this(initialReels, finalReels, false);
	}
	
	public List<List<Symbol>> reels(){
		return finalReels;
	}
	
	public int noOfReels(){
		return finalReels.size();
	}
	
	public int count(Symbol symbol){
		return 0;
	}
	
	public List<SymbolPosition> findPositionsOf(Symbol symbol){
		return null;
	}
	
	public boolean contains(int reelIndex, Symbol symbol){
		return false;
	}
}
