package gamesoft.slots.service.command;

import lombok.Data;

@Data
public class InitialiseGame implements Command {
	private String gameId;
}
