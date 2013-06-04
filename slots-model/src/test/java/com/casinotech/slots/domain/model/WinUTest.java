package com.casinotech.slots.domain.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class WinUTest {

	@Test
	public void testComparison() {
		Win ten = Win.of(BigDecimal.TEN);
		Win two = Win.of(new BigDecimal("2.0"));
		
		assertTrue("Noting is bigger than progressive", Win.PROGRESSSIVE.compareTo(ten) > 0);
		assertTrue("Noting is bigger than progressive", Win.PROGRESSSIVE.compareTo(two) > 0);
		
		assertTrue(ten.compareTo(Win.PROGRESSSIVE) < 0);
		assertTrue(two.compareTo(Win.PROGRESSSIVE) < 0);
		
		assertTrue(Win.PROGRESSSIVE.compareTo(Win.PROGRESSSIVE) == 0);
		
		assertTrue(ten.compareTo(ten) == 0);
		assertTrue(two.compareTo(two) == 0);
		
		assertTrue(ten.compareTo(two) > 0);
		assertTrue(two.compareTo(ten) < 0);
	}
	
	@Test
	public void testClone(){
		fail("Not yet implemented!");
	}
	
	@Test
	public void testMultiply(){
		fail("Not yet implemented!");
	}

}
