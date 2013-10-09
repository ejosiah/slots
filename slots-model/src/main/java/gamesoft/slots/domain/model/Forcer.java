package gamesoft.slots.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

/**
 * Forces a spin result using user supplied result 
 * and bypasses the slots random spin algorithm
 * @author jay
 *
 */
@AllArgsConstructor
public class Forcer {
	
	private final Map<String, Object> result;
	private final Map<Integer, Symbol> symbols;
	
	public ReelSpinResult spin(ReelSpinResultInterceptorChain interceptor, Reels reels){
		ReelSpinResult reelSpinResult = null;
		if(result.containsKey("reels")){
			reelSpinResult = buildResult(reels.numberOfReels());
		}else{
			reelSpinResult = interceptor.intercept(reels.spin());
		}
		return reelSpinResult;
	}
	
	public ReelSpinResult buildResult(int noOfReels){
		List<List<Symbol>> reels = new ArrayList<>();
		
		for(int i = 0; i < noOfReels; i++){
			reels.add(new ArrayList<Symbol>());
		}
		
		List<List<Integer>>	forcedReels = (List<List<Integer>>) result.get("reels");
		for(int i = 0; i < forcedReels.size(); i++){
			List<Integer> reel = forcedReels.get(i);
			for(int j = 0; j < reel.size(); j++){
				int symbolId = reel.get(j);
				Symbol symbol = symbols.get(symbolId);
				
				reels.get(j).add(symbol);
			}
		}
		return ReelSpinResult.singleState(reels);
	}
}
