package gamesoft.slots.service.command;

import java.util.List;

import lombok.Data;

@Data
public class PlayGame implements Command {
	private Long playerId;
	private String gameId;
	private List<Integer> numberOfLines;
	private String coinSize;
}
