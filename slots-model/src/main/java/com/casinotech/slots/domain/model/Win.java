package com.casinotech.slots.domain.model;

import java.math.BigDecimal;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
public class Win implements Comparable<Win>, Cloneable {
	
	public static final Win NO_WIN = new Win(BigDecimal.ZERO, false);
	public static final Win PROGRESSSIVE = new Win(BigDecimal.ZERO, true);
	
	@Accessors(fluent=true)
	private final BigDecimal size;
	
	private final boolean progressive;
	
	private Win(BigDecimal size, boolean progressive){
		this.size = size;
		this.progressive = progressive;
	}
	
	public static Win of(BigDecimal size){
		return new Win(size, false);
	}
	
	public static Win progressiveWin(){
		return PROGRESSSIVE;
	}
	
	public static Win of(String size){
		return new Win(new BigDecimal(size), false);
	}

	@Override
	public int compareTo(Win other) {
		if(progressive && other.progressive){
			return 0;
		}
		if(progressive && !other.progressive){
			return 1;
		}
		if(!progressive && other.progressive){
			return -1;
		}
		return size.compareTo(other.size);
	}
	
	@Override
	public Win clone(){
		return Win.of(size.toString());
	}
	
	public Win multiply(BigDecimal value){
		return Win.of(size.multiply(value));
	}
}
