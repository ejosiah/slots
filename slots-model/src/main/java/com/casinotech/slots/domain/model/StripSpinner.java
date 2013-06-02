package com.casinotech.slots.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.casinotech.random.RandomNumberGenerator;

public class StripSpinner implements Spinner{
	
	private RandomNumberGenerator rng;

	@Override
	public List<Symbol> spin(Reel reel, int size) {
		WeightedList<Symbol> list = new WeightedList<>(reel.symbols(), rng);
		int stop = list.pick().index();
		
		List<Symbol> result = new ArrayList<Symbol>();
		for(int i = 0; i < size; i++){
			int index = (stop + i)% reel.numberOfSymbols();
			result.add(reel.symbolAt(index));
		}
		return result;
	}

}
