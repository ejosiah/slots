package gamesoft.slots.domain.model.slot;

import java.util.List;

public interface Spinner {
	
	List<Symbol> spin(Reel reel, int size);
}
