package gamesoft.slots.domain.model;

public interface BonusWin extends FeatureWin  {
	
	<T> BonusGame<T> bonusGame();
}