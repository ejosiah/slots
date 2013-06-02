package com.casinotech.slots.domain.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PayoutCombos {
	
	private final Map<Integer, PayoutCombo> payoutCombos = new HashMap<Integer, PayoutCombo>();
	
	public PayoutCombos(List<PayoutCombo> payoutCombos) {
		for(PayoutCombo payoutCombo : payoutCombos){
			this.payoutCombos.put(payoutCombo.id(), payoutCombo);
		}
	}

	public PayoutCombo getPayoutComboFor(Query query){
		PayoutCombo combo = null;
		if(query.id() != null){
			combo = getPayoutComobForId(query.id());
		}else if(query.symbols() != null && query.lineHelper() != null){
			combo = getPayoutComboFor(query.symbols(), query.lineHelper()
					, query.line(), query.numberOfCoins(), query.wildMultiplier());
		}
		return combo != null ? combo : PayoutCombo.NO_PAYOUT;
	}

	private PayoutCombo getPayoutComobForId(Integer id){
		return payoutCombos.get(id);
	}
	
	private PayoutCombo getPayoutComboFor(List<List<Symbol>> reels,Lines lineHelper
			, Integer line, Integer numberOfCoins, BigDecimal wildMultiplier) {
		
		List<Symbol> symbols = lineHelper.symbolsForLine(line, reels);
		List<PayoutCombo> matchingPayoutCombos = matchingPayoutCombos( symbols, numberOfCoins);
		
		if(matchingPayoutCombos.size() == 0){
			return null;
		}

		return matchingPayoutCombos.get(0);
	}

	private List<PayoutCombo> matchingPayoutCombos(List<Symbol> symbols, Integer numberOfCoins){
		
		List<PayoutCombo> combos = new ArrayList<>(payoutCombos.values());
		Collections.sort(combos);
		
		List<PayoutCombo> matchingCombos = new ArrayList<>();
		
		for(PayoutCombo combo : combos){
			if(combo.matchs(symbols)){
				matchingCombos.add(combo);
			}
		}
		return matchingCombos;
	}
	
	
	private PayoutCombo bestPayoutComb(List<PayoutCombo> PayoutCombos
			, List<Symbol> symbols, BigDecimal wildMultiplier) {
		return null;
	}

}
