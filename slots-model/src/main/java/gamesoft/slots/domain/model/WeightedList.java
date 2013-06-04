package gamesoft.slots.domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.casinotech.random.RandomNumberGenerator;

public class WeightedList<T extends Weighted> {
	private static final Comparator<Weighted> COMPARATOR = new weightedComparator();
	private List<T> entries;
	private RandomNumberGenerator rng;
	
	public WeightedList(RandomNumberGenerator rng){
		this(new ArrayList<T>(), rng);
	}
	
	public WeightedList(List<T> entries, RandomNumberGenerator rng){
		Collections.sort(entries, COMPARATOR);
		this.entries = entries;
		this.rng = rng;
	}
	
	public void add(T weighted){
		entries.add(weighted);
		Collections.sort(entries, COMPARATOR);
	}
	
	public T pick(){
		Long randomWeight = randomWeight();
		Long currentWeight = 0L;
		
		for(T entry : entries){
			currentWeight += entry.weight();
			
			if(currentWeight.compareTo(randomWeight) > 0){
				return entry;
			}
		}
		throw new RuntimeException("Unable to pick");
	}
	
	private Long randomWeight(){
		Long total = 0L;
		for(T entry : entries){
			total += entry.weight();
		}
		return rng.nextLong(total);
	}
	
	
	private static class weightedComparator implements Comparator<Weighted>{

		@Override
		public int compare(Weighted weighted1, Weighted weighted2) {
			return weighted1.weight().compareTo(weighted2.weight());
		}
		
	}

}
