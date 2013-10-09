package gamesoft.slots.domain.model;

import static org.junit.Assert.*;

import gamesoft.slots.domain.model.Win;

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
	public void testClone() throws Exception{
		Win ten = Win.of(BigDecimal.TEN);
		Win expected = ten;
		Win actual = ten.clone();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testCloneProgressive() throws Exception{
		Win win = Win.PROGRESSSIVE;
		Win expected = win;
		Win actual = win.clone();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testMultiply(){
		final BigDecimal MULTIPLIER = new BigDecimal("2");
		Win win = Win.of("10");
		Win expected = Win.of("20");
		Win actual = win.multiply(MULTIPLIER);
		assertEquals(expected, actual);
	}

}
