package gamesoft.slots.domain.model.slot;

import gamesoft.random.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class Reel {
	private final int index;
	private final List<Symbol> symbols;
	private final Spinner spinner;
	
	@Inject
	private RandomNumberGenerator rng;
	
	public int numberOfSymbols(){
		return symbols.size();
	}
	
	public List<Symbol> spin(int size){
		return spinner.spin(this, size);
	}

	public Symbol symbolAt(int index) {
		return symbols.get(index);
	}
	
	public Symbol randomSymbol(){
		int index = rng.nextInt(numberOfSymbols());
		return symbolAt(index);
	}
	
	public List<Symbol> symbols(){
		return new ArrayList<>(symbols);
	}

	public int count(Symbol symbol) {
		int count = 0;
		for(Symbol reelSymbol : symbols){
			if(reelSymbol.equals(symbol)){
				count++;
			}
		}
		return count;
	}

}
