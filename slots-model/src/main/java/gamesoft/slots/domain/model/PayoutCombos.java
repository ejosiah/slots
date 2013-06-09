package gamesoft.slots.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;

public class PayoutCombos {
	
	private final Map<Integer, PayoutCombo> payoutCombos = new HashMap<Integer, PayoutCombo>();
	private static final Integer IGNORE = null;
	
	public PayoutCombos(List<PayoutCombo> payoutCombos) {
		for(PayoutCombo payoutCombo : payoutCombos){
			this.payoutCombos.put(payoutCombo.id(), payoutCombo);
		}
	}

	public PayoutCombo getPayoutComboFor(Query query){
		PayoutCombo combo = null;
		if(query.id() != null){
			combo = getPayoutComobForId(query.id());
		}else if(query.symbols() != null){
			combo = getPayoutComboFor(query.symbols(), query.line()
					, query.numberOfCoins(), query.wildMultiplier());
		}
		return combo != null ? combo : PayoutCombo.NO_PAYOUT;
	}

	private PayoutCombo getPayoutComobForId(Integer id){
		return payoutCombos.get(id);
	}
	
	private PayoutCombo getPayoutComboFor(List<Symbol> symbols, Integer line
			, Integer numberOfCoins, BigDecimal wildMultiplier) {
		
		List<PayoutCombo> matchingPayoutCombos = matchingPayoutCombos(symbols, numberOfCoins, line, wildMultiplier);
		
		if(matchingPayoutCombos.size() == 0){
			return null;
		}
		int biggestPayout = matchingPayoutCombos.size() - 1;
		return matchingPayoutCombos.get(biggestPayout);
	}

	private List<PayoutCombo> matchingPayoutCombos(List<Symbol> symbols, Integer coins, Integer line, BigDecimal wildMultiplier){
		
		List<PayoutCombo> combos = new ArrayList<>(payoutCombos.values());
		Collections.sort(combos);
		
		List<PayoutCombo> matchingCombos = new ArrayList<>();
		
		for(PayoutCombo combo : combos){
			if(match(combo, symbols, coins, line)){
				matchingCombos.add(combo);
			}
		}
		Collections.sort(matchingCombos, new Comparer(wildMultiplier));
		return matchingCombos;
	}
	
	private boolean match(PayoutCombo combo, List<Symbol> symbols, Integer coins, Integer line){
		if(line != null){
			return combo.matches(symbols, line);
		}else if(coins != null){
			return combo.matches(symbols, IGNORE, coins);
		}else{
			return combo.matches(symbols);
		}
	}
	
	@AllArgsConstructor
	private static final class Comparer implements Comparator<PayoutCombo>{
		private final BigDecimal wildMultiplier;

		@Override
		public int compare(PayoutCombo payout1, PayoutCombo payout2) {
			int result = payout1.compareTo(payout2);
			return result == 0 ? 
				payout1.apply(wildMultiplier).compareTo(
						payout2.apply(wildMultiplier))
					: result;
			
		}
		
	}
}
