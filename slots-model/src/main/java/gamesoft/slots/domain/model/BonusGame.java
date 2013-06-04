package gamesoft.slots.domain.model;

public interface BonusGame<T> extends Feature<T> {
	
	BonusResult play();
}
