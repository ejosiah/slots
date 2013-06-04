package gamesoft.slots.domain.model;

import java.util.List;

public interface SlotWin{
	String type();
	List<SymbolPosition> symbolPositions();
}
