package gamesoft.slots.domain.model.slot;

public interface BonusWin extends FeatureWin  {
	
	<T> BonusGame<T> bonusGame();
}
