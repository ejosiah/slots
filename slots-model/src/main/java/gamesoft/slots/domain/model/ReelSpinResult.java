package gamesoft.slots.domain.model;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
public class ReelSpinResult {
	private final List<List<Symbol>> initialReels;
	private final List<List<Symbol>> finalReels;
}
