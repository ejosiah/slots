package gamesoft.slots.domain.model.slot;

public interface BonusGame<T> extends Feature<T> {
	
	BonusResult play();
}
