package gamesoft.slots.domain.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.casinotech.random.RandomNumberGenerator;

@Component
@Scope("singleton")
public final class RNGHolder {
	
	private static RandomNumberGenerator RNG;
	
	RNGHolder(){
		
	}
	
	@Autowired
	public void setRNG(RandomNumberGenerator rng){
		RNG = rng;
	}
	
	public static RandomNumberGenerator RNG(){
		return RNG;
	}
}
