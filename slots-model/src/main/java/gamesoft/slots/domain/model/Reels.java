package gamesoft.slots.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Reels {
	
	private final List<Reel> reels;
	private final List<Integer> symbolsPerReel;
	
	public Reels(List<Reel> reels, List<Integer> symbolsPerReel){
		this.reels = reels;
		this.symbolsPerReel = symbolsPerReel;
	}
	
	public Reels(List<Reel> reels, int symbolsPerReel){
		this(reels, buildSymbolsPerReel(symbolsPerReel, reels.size()));
	}
	
	private static List<Integer> buildSymbolsPerReel(int value, int count){
		List<Integer> symbolsPerReel = new ArrayList<Integer>();
		for(int i = 0; i < count; i++){
			symbolsPerReel.add(value);
		}
		return symbolsPerReel;
	}
	
	public int numberOfReels(){
		return reels.size();
	}
	
	public int numberOfSymbolsForReelWith(int index){
		return reels.get(index).numberOfSymbols();
	}
	
	public  ReelSpinResult spin(){
		List<List<Symbol>> result = new ArrayList<List<Symbol>>();
		for(int i = 0; i < reels.size(); i++){
			result.add(reels.get(i).spin(symbolsPerReel.get(i)));
		}
		return ReelSpinResult.singleState(result);
	}

}
