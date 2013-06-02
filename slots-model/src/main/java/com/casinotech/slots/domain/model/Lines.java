package com.casinotech.slots.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Lines {
	
	private List<List<Integer>> vertices;
	
	public Lines(List<List<Integer>> vertexes){
		this.vertices = vertexes;
	}
	
	public List<Symbol> symbolsForLine(int index, List<List<Symbol>> fromReels){
		List<Integer> line = vertices.get(index);
		
		List<Symbol> symbols = new ArrayList<>();
		
		int reelIndex = 0;
		for(Integer vertex : line){
			Symbol symbol = fromReels.get(reelIndex).get(vertex);
			symbols.add(symbol);
			reelIndex++;
		}
		
		return symbols;
	}
	
	public Integer symbolPosition(int line, int reelIndex){
		return vertices.get(line).get(reelIndex);
	}
}
