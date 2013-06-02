package com.casinotech.slots.domain.model;

import java.util.HashMap;
import java.util.Map;

import com.casinotech.random.NativeRandomNumberGenerator;

public class Main {
	
	private static final Long SIMULATIONS = 1000000L;
	
	public static void main(String[] args){
		WeightedList<WeightedElement<String>> list = new WeightedList<>(new NativeRandomNumberGenerator());
		
		list.add(new WeightedElement<String>("apple", 4L));
		list.add(new WeightedElement<String>("orange", 10L));
		list.add(new WeightedElement<String>("mango", 20L));
		list.add(new WeightedElement<String>("berry", 2L));
		
		Map<String, Long> freqency = new HashMap<String, Long>();
		
		for(int i = 0; i < SIMULATIONS; i++){
			String element = list.pick().element();
			if(freqency.containsKey(element)){
				freqency.put(element, freqency.get(element)+1);
			}else{
				freqency.put(element, 1L);
			}
		}
		
		for(String element : freqency.keySet()){
			double probability = freqency.get(element) / SIMULATIONS.doubleValue();
			System.out.printf("probability: %s => %s\n", element, probability);
		}
	}
}
