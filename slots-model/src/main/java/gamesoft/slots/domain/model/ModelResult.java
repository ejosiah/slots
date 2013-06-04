package gamesoft.slots.domain.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
public class ModelResult {
	private final List<SlotWin> wins;
	private final Anticipate anticipate;
	
	List<Boolean> anticipate(){
		return anticipate.anticipate();
	}
	
	List<Boolean> anticipateWins(){
		return anticipate.win();
	}

}
