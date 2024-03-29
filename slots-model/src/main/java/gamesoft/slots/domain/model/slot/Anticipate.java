package gamesoft.slots.domain.model.slot;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class Anticipate {
	private static final List<Boolean> EMPTY = new ArrayList<>();
	public static final Anticipate NONE = new Anticipate(EMPTY, EMPTY);
	
	private final List<Boolean> anticipate;
	private final List<Boolean> win;
}
