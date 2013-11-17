package gamesoft.slots.domain.model.slot;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.experimental.Accessors;

import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

@Getter
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class SlotSpinResult {
	private final ReelSpinResult spinResult;
	private final SlotBet bet;
	private final ModelResult modelResult;
	
	private final WinSizeCalculator winSizeCalculator;
	
	@Getter(value=AccessLevel.PRIVATE) 
	private final CurrencyUnit currency;
	
	@Getter(value=AccessLevel.PRIVATE) 
	private BigDecimal winMultiplier = BigDecimal.ONE;
	
	public SlotSpinResult(SlotBet bet, ReelSpinResult spinResult, ModelResult modelResult
			,WinSizeCalculator winSizeCalculator){
		this.bet = bet;
		this.spinResult = spinResult;
		this.modelResult = modelResult;
		this.currency = bet.value().getCurrencyUnit();
		this.winSizeCalculator = winSizeCalculator;
	}
	
	@SneakyThrows
	public <T extends SlotWin> T win(Class<T> type){
		for(SlotWin win : modelResult.wins()){
			if(win.getClass() == type){
				return (T)win;
			}
		}
		Method method = type.getDeclaredMethod("noWin", null);
		return (T)method.invoke(type);
	}

	
	public <T extends SlotWin> List<T> wins(Class<T> type){
		List<T> results = new ArrayList<>();
		for(SlotWin win : modelResult.wins()){
			if(win.getClass() == type){
				results.add((T)win);
			}
		}
		return results;
	}
	
	public List<SlotWin> wins(){
		return new ArrayList<>(modelResult.wins());
	}
	
	public WinSize winSize(){
		return winSizeCalculator.evaluate(bet, totalWin());
	}
	
	public Money totalWin(){
		List<SlotWin> wins = wins();
		Money total = Money.zero(currency);
		for(SlotWin win : wins){
			if(win instanceof CashWin){
				total = total.plus(((CashWin)win).amount());
			}
		}
		return total;
	}

	
	public Money totalWin(Class<? extends CashWin> type){
		List<? extends CashWin> cashWins = wins(type);
		Money total = Money.zero(currency);
		for(CashWin win : cashWins){
			total = total.plus(win.amount());
		}
		return total;
	}
	
	public void addMultiplier(BigDecimal multiplier){
		List<SlotWin> wins = wins();
		winMultiplier = winMultiplier.multiply(multiplier);
		for(SlotWin win : wins){
			if(win instanceof CashWin){
				((CashWin)win).multiplyBy(winMultiplier);
			}
		}
	}
	
	public List<Boolean> anticipations(){
		return new ArrayList<>(modelResult.anticipate());
	}
	
	public List<Boolean> anticipationWins(){
		return new ArrayList<>(modelResult.anticipateWins());
	}
	
	public boolean hasWin(){
		return totalWin().isPositive();
	}
	
}
