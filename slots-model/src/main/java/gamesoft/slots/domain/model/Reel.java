package gamesoft.slots.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
public class Reel {
	private final int index;
	private final List<Symbol> symbols;
	private final Spinner spinner;
	
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
		int index = RNGHolder.RNG().nextInt(numberOfSymbols());
		return symbolAt(index);
	}
	
	public List<Symbol> symbols(){
		return new ArrayList<>(symbols);
	}

}
