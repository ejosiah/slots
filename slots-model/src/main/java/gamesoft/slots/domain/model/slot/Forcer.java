package gamesoft.slots.domain.model.slot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;

/**
 * Forces a spin result using user supplied result 
 * and bypasses the slots random spin algorithm
 * @author jay
 *
 */
@AllArgsConstructor
public class Forcer {
	
	private final Map<Integer, Symbol> symbols;
	private final ForcerBuilder builder;
	
	public ReelSpinResult spin(ReelSpinResultInterceptorChain interceptor, Reels reels, String expectedResult){
		Map<String, Object> result = builder.build(expectedResult);
		ReelSpinResult reelSpinResult = null;
		if(result.containsKey("reels")){
			reelSpinResult = buildResult(reels.numberOfReels(), result);
		}else{
			reelSpinResult = interceptor.intercept(reels.spin());
		}
		return reelSpinResult;
	}
	
	public ReelSpinResult buildResult(int noOfReels, Map<String, Object> result){
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
