package gamesoft.slots.domain.model.slot;

import gamesoft.slots.domain.model.slot.Direction;
import gamesoft.slots.domain.model.slot.PayoutCombo;
import gamesoft.slots.domain.model.slot.Symbol;
import gamesoft.slots.domain.model.slot.Win;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Ignore;

import lombok.Cleanup;
import lombok.SneakyThrows;

@Ignore
@SuppressWarnings({"unchecked", "rawtypes"})
public final class TestHelper {
	
	public static Map<Integer, Symbol> symbols = new LinkedHashMap<>();
	public static Map<Integer, PayoutCombo> combos = new LinkedHashMap<>();
	
	static {
		Map<String, Object> slotConfig = loadSlotConfig();
		List<Map> symbolConfigs = (List<Map>) slotConfig.get("symbols");
		
		for(Map symbolConfig : symbolConfigs){
			
			boolean isWild = symbolConfig.get("wild") != null ? true : false;
			int id = new Integer(symbolConfig.get("id").toString());
			String code = symbolConfig.get("code").toString();
		
			if(isWild){
				symbols.put(id, Symbol.wildSymbol(id, code, 0));
			}else{
				symbols.put(id, Symbol.newSymbol(id, code, 0));
			}
		}
		
		List<Map> payouts = (List<Map>)slotConfig.get("payoutCombos");
		
		for(Map payout : payouts){
			int id = new Integer(payout.get("id").toString());
			Symbol symbol = payout.containsKey("symbol") ? symbols.get(new Integer(payout.get("symbol").toString())) : null;
			int occurrence = new Integer(payout.get("occurrence").toString());
			Win win = Win.of(payout.get("win").toString());
			String orderStr = payout.containsKey("order") ? payout.get("order").toString() : "FROM_LEFT";
			Direction direction = Direction.valueOf(orderStr);
			PayoutCombo combo = null;
			
			if(payout.containsKey("line")){
				int line = new Integer(payout.get("line").toString());
				switch(direction){
				case FROM_LEFT : 
					combo = PayoutCombo.lineSpecificCombo(id, occurrence, win, symbol, line);
					break;
				case FROM_RIGHT:
					combo = PayoutCombo.lineSpecificComboFromRight(id, occurrence, win, symbol, line);
					break;
				case ANY:
					combo = PayoutCombo.lineSpecificComboFromAnyDirection(id, occurrence, win, symbol, line);
					break;
				}
			}else if(payout.containsKey("coins")){
				int coins = new Integer(payout.get("coins").toString());
				switch(direction){
				case FROM_LEFT :
					combo = PayoutCombo.coinsSpecificCombo(id, occurrence, win, symbol, coins);
					break;
				case FROM_RIGHT :
					combo = PayoutCombo.coinsSpecificComboFromRight(id, occurrence, win, symbol, coins);
					break;
				case ANY :
					combo = PayoutCombo.coinsSpecificComboFromAnyDirection(id, occurrence, win, symbol, coins);
					break;
				}
			}else if(symbol == null){
				List<Integer> symbolIds = (List<Integer>) payout.get("symbols");
				List<Symbol> payoutSybmols = new ArrayList<>();
				for(Integer sid : symbolIds){
					payoutSybmols.add(symbols.get(sid));
				}
				switch(direction){
				case FROM_LEFT:
					combo = PayoutCombo.mixedCombo(id, occurrence, win, payoutSybmols);
					break;
				case FROM_RIGHT:
					combo = PayoutCombo.mixedComboFromRight(id, occurrence, win, payoutSybmols);
					break;
				case ANY:
					combo = PayoutCombo.mixedComboFromAnyDirection(id, occurrence, win, payoutSybmols);
					break;
				}
			}
			else{
				switch(direction){
				case FROM_LEFT:
					combo = PayoutCombo.createCombo(id, occurrence, win, symbol);
				case FROM_RIGHT:
					combo = PayoutCombo.createComboFromAnyDirection(id, occurrence, win, symbol);
				case ANY:
					combo = PayoutCombo.createComboFromAnyDirection(id, occurrence, win, symbol);
				}
			}
			combos.put(id, combo);
		}
	}


	public static List<Symbol> loadSymbols() {		
		
		List<String> symbolCodes = codes();
		List<Symbol> symbols = new ArrayList<Symbol>();
		for(int i = 0; i < symbolCodes.size(); i++){
			String symbolCode = symbolCodes.get(i);
			if(symbolCodes.get(i).equals("WILD")){
				symbols.add(Symbol.wildSymbol(10, symbolCode, i));
			}else{
				
				symbols.add(Symbol.newSymbol(id(symbolCode), symbolCode, i));
			}
		}
		return symbols;
	}
	
	private static int id(String symbol){
		 int lastIndex = symbol.length() - 1;
		return symbol.substring(lastIndex).matches("\\d+") 
				? new Integer(symbol.substring(lastIndex)) : 11;
	}

	public static List<Symbol> weightedloadSymbols() {
		List<String> symbolCodes = codes();
		Map<String, Long> weights = weights();
		List<Symbol> symbols = new ArrayList<Symbol>();
		
		for(int i = 0; i < symbolCodes.size(); i++){
			String symbolCode = symbolCodes.get(i);
			Long weight = weights.get(symbolCode);
			if(symbolCodes.get(i).equals("WILD")){
				symbols.add(Symbol.weightedWildSymbol(10, symbolCode, weight, i));
			}else{
				symbols.add(Symbol.newWeightedSymbol(id(symbolCode), symbolCode, weight, i));
			}
		}
		return symbols;
	}
	
	private static Map<String, Long> weights(){
		@Cleanup
		Scanner scanner = new Scanner(StripSpinnerUTest.class.getResourceAsStream("/weights"));
		
		Map<String, Long> weights = new HashMap<>();
		while(scanner.hasNextLine()){
			String[] line = scanner.nextLine().split(",");
			weights.put(line[0].trim(), new Long(line[1].trim()));
		}
		return weights;
	}
	
	private static List<String> codes(){
		@Cleanup
		Scanner scanner = new Scanner(StripSpinnerUTest.class.getResourceAsStream("/testReel"));
		
		String str = "";
		while(scanner.hasNextLine()){
			str += scanner.nextLine(); 
		}
		
		String[] testData = str.split(",");
		return new ArrayList<String>(Arrays.asList(testData));
	}
	
	public static void main(String[] args) throws Exception{
	}
	
	public static List<PayoutCombo> loadPayout(){
		List<String> payout = payout();
		List<PayoutCombo> payoutCombos = new ArrayList<>();
		
		for(int i = 0; i < payout.size(); i++){
			String[] data = payout.get(i).split(",");
			Integer id = new Integer(data[0].substring(data[0].length() - 1));
			payoutCombos.add(PayoutCombo.createCombo(i, new Integer(data[1])
				, Win.of(data[2]), Symbol.newSymbol(id, data[0], 0)));
		}
		
		return payoutCombos;
	}
	
	private static List<String> payout(){
		@Cleanup
		Scanner scanner = new Scanner(StripSpinnerUTest.class.getResourceAsStream("/payoutCombo"));
		
		List<String> payout = new ArrayList<>();
		while(scanner.hasNextLine()){
			payout.add(scanner.nextLine());
		}
		
		return payout;
	}
	
	public static List<Symbol> payoutSymbols(){
		@Cleanup
		Scanner scanner = new Scanner(StripSpinnerUTest.class.getResourceAsStream("/payoutSymbols"));
		
		List<Symbol> symbols = new ArrayList<>();
		while(scanner.hasNextLine()){
			String code = scanner.nextLine();
			Integer id = new Integer(code.substring(code.length() - 1));
			Symbol symbol = Symbol.newSymbol(id, code, 0);
			symbols.add(symbol);
		}
		
		return symbols;
	}

	public static void loadLines() throws JsonParseException, JsonMappingException, IOException {
		InputStream src = TestHelper.class.getResourceAsStream("/lines.js");
		Map json = new ObjectMapper().readValue(src, Map.class);
		
		System.out.println(json.get("lines"));
	}
	
	@SneakyThrows
	private static Map<String, Object> loadSlotConfig(){
		InputStream src = TestHelper.class.getResourceAsStream("/payoutCombos.js");
		return new ObjectMapper().readValue(src, Map.class);
	}
	
}
