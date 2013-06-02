package com.casinotech.slots.domain.model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import lombok.Cleanup;

public final class TestHelper {

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
		loadLines();
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
	
}
