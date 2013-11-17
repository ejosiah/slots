package gamesoft.slots.domain.model.slot.event;

import gamesoft.casino.domain.model.game.CoinSize;
import gamesoft.casino.domain.model.game.GameEvent;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Initialized extends GameEvent {

	private static final long serialVersionUID = 1L;
	private Long playerId;
	private List<CoinSize> supportedCoinSizes;
	private List<Integer> maxLines;
	private List<Integer> defaultLines;
	private List<List<Integer>> startingReels;
}
