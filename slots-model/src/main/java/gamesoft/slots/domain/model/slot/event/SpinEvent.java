package gamesoft.slots.domain.model.slot.event;

import gamesoft.casino.domain.model.game.GameEvent;
import gamesoft.slots.domain.model.slot.SlotSpinResult;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpinEvent extends GameEvent{
	private static final long serialVersionUID = 1L;
	private Long gameReference;
	private Long playerId;
	private SlotSpinResult spinResult;	
}
