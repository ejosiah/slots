package gamesoft.slots.domain.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReelSpinResultUTest {
	
	@Mock Symbol symbol_0;
	@Mock Symbol symbol_1;
	@Mock Symbol symbol_2;
	@Mock Symbol symbol_3;
	@Mock Symbol symbol_4;
	@Mock Symbol symbol_5;
	@Mock Symbol symbol_6;
	@Mock Symbol symbol_7;
	@Mock Symbol symbol_8;
	@Mock Symbol symbol_9;
	
	List<List<Symbol>> result = new ArrayList<>();
	
	ReelSpinResult reelSpinResult;

	@Before
	public void setUp() throws Exception {
		List<Symbol> reel1 = new ArrayList<>();
		reel1.add(symbol_3);
		reel1.add(symbol_9);
		reel1.add(symbol_7);
		
		List<Symbol> reel2 = new ArrayList<>();
		reel2.add(symbol_2);
		reel2.add(symbol_6);
		reel2.add(symbol_1);
		
		List<Symbol> reel3 = new ArrayList<>();
		reel3.add(symbol_7);
		reel3.add(symbol_4);
		reel3.add(symbol_0);
		
		List<Symbol> reel4 = new ArrayList<>();
		reel4.add(symbol_3);
		reel4.add(symbol_9);
		reel4.add(symbol_2);
		
		List<Symbol> reel5 = new ArrayList<>();
		reel5.add(symbol_5);
		reel5.add(symbol_7);
		reel5.add(symbol_4);
		
		result.add(reel1);
		result.add(reel2);
		result.add(reel3);
		result.add(reel4);
		result.add(reel5);
		
		reelSpinResult = ReelSpinResult.singleState(result);
	}

	@Test
	public void testCount() {
		int expected = 3;
		int actual = reelSpinResult.count(symbol_7);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testNumberOfReels(){
		int expected = 5;
		int actual = reelSpinResult.noOfReels();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testC(){
		int expected = 0;
		int actual = reelSpinResult.count(symbol_8);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testContainsSymbol(){
		boolean expected = true;
		boolean actual = reelSpinResult.contains(0, symbol_3);
		
		assertEquals(expected, actual);
		
		expected = false;
		actual = reelSpinResult.contains(1, symbol_3);
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindPositionOfSymbol(){
		List<SymbolPosition> expected = Arrays.asList(
				new SymbolPosition(symbol_7, 0, 2),
				new SymbolPosition(symbol_7, 2, 0),
				new SymbolPosition(symbol_7, 4, 1)
				);
		
		List<SymbolPosition> actual = reelSpinResult.findPositionsOf(symbol_7);
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testFindPositionOfMissingSymbol(){
		List<SymbolPosition> result = reelSpinResult.findPositionsOf(symbol_8);
		assertTrue(result.isEmpty());
	}

}
