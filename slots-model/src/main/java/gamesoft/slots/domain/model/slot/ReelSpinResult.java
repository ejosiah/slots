package gamesoft.slots.domain.model.slot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@EqualsAndHashCode
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class ReelSpinResult {
	public static final List<List<Symbol>> EMPTY_REEL = Collections.emptyList();
	private final List<List<Symbol>> initialReels;
	private final List<List<Symbol>> finalReels;
	private final boolean dualState;
	
	public static ReelSpinResult singleState(List<List<Symbol>> reels){
		return new ReelSpinResult(reels, reels, false);
	}
	
	
	public static ReelSpinResult dualState(List<List<Symbol>> initialReels, List<List<Symbol>> finalReels){
		return new ReelSpinResult(initialReels, finalReels, true);
	}
	
	public List<List<Symbol>> reels(){
		return finalReels;
	}
	
	public int noOfReels(){
		return finalReels.size();
	}
	
	public int count(Symbol symbol){
		int result = 0;
		for(List<Symbol> reel : reels()){
			for(Symbol reelSymbol : reel){
				if(symbol.equals(reelSymbol)){
					result++;
				}
			}
		}
		return result;
	}
	
	
	public List<SymbolPosition> findPositionsOf(Symbol symbol){
		List<SymbolPosition> symbolPositions = new ArrayList<>();
		
		for(int i = 0; i < reels().size(); i++){
			for(int j = 0; j < reels().get(i).size(); j++){
				Symbol reelSymbol = reels().get(i).get(j);
				if(symbol.equals(reelSymbol)){
					symbolPositions.add(new SymbolPosition(reelSymbol, i, j));
				}
			}
		}
		
		return symbolPositions;
	}
	
	public boolean contains(int reelIndex, Symbol symbol){
		return reels().get(reelIndex).contains(symbol);
	}
}
