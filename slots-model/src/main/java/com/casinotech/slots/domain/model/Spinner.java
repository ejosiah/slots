package com.casinotech.slots.domain.model;

import java.util.List;

public interface Spinner {
	
	List<Symbol> spin(Reel reel, int size);
}
