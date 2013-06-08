package gamesoft.slots.domain.model;

import gamesoft.random.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RandomSpinner implements Spinner {
	
	@Inject
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
