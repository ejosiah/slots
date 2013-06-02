package com.casinotech.slots.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.casinotech.random.RandomNumberGenerator;

public class RandomSpinner implements Spinner {
	
	private RandomNumberGenerator rng;

	public List<Symbol> spin(Reel reel, int size) {
		List<Symbol> result = new ArrayList<Symbol>();
		for(int i = 0; i < size; i++){
			int index = rng.nextInt(reel.numberOfSymbols());
			result.add(reel.symbolAt(index));
		}
		return result;
	}

}
