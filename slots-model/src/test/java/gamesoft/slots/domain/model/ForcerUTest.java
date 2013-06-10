package gamesoft.slots.domain.model;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ForcerUTest {
	
	@Mock Reels reels;
	@Mock ReelSpinResultInterceptorChain interceptor;
	@Mock ReelSpinResult reelSpinResult;
	@Mock Slot slot;
	@Mock Map<String, Object> result;
	@Mock Map<Integer, Symbol> symbols;
	
	@Mock Symbol symbol1;
	@Mock Symbol symbol2;
	@Mock Symbol symbol3;
	@Mock Symbol symbol4;
	@Mock Symbol symbol5;
	@Mock Symbol symbol6;
	@Mock Symbol symbol7;
	@Mock Symbol symbol8;
	@Mock Symbol symbol9;
	
	private Forcer forcer;

	@Before
	public void setUp() throws Exception {
		forcer = new Forcer(result, symbols);
	}

	@Test
	public void testSpin() {
		when(result.containsKey("reels")).thenReturn(true);
		when(result.get("reels")).thenReturn(forcedResult());
		when(reels.numberOfReels()).thenReturn(5);
		when(symbols.get(1)).thenReturn(symbol1);
		when(symbols.get(2)).thenReturn(symbol2);
		when(symbols.get(3)).thenReturn(symbol3);
		when(symbols.get(4)).thenReturn(symbol4);
		when(symbols.get(5)).thenReturn(symbol5);
		when(symbols.get(6)).thenReturn(symbol6);
		when(symbols.get(7)).thenReturn(symbol7);
		when(symbols.get(8)).thenReturn(symbol8);
		when(symbols.get(9)).thenReturn(symbol9);
		
		ReelSpinResult expected = expected();
		ReelSpinResult actual = forcer.spin(interceptor, reels);
		
		assertEquals(expected, actual);
	}
	
	private List<List<Integer>> forcedResult(){
		return Arrays.asList(
				Arrays.asList(1, 8, 3, 5, 7)
				,Arrays.asList(2, 1, 9, 2, 6)
				,Arrays.asList(4, 7, 5, 4, 5));
	}
	
	private ReelSpinResult expected(){
		List<List<Symbol>> symbols = Arrays.asList(
				Arrays.asList(symbol1, symbol2, symbol4),
				Arrays.asList(symbol8, symbol1, symbol7),
				Arrays.asList(symbol3, symbol9, symbol5),
				Arrays.asList(symbol5, symbol2, symbol4),
				Arrays.asList(symbol7, symbol6, symbol5)
				);
		return new ReelSpinResult(symbols, symbols);
	}
	
	@Test
	public void testNoResults(){
		when(result.containsKey("reels")).thenReturn(false);
		when(reels.spin()).thenReturn(reelSpinResult);
		when(interceptor.intercept(reelSpinResult)).thenReturn(reelSpinResult);
		
		ReelSpinResult expected = reelSpinResult;
		ReelSpinResult actual = forcer.spin(interceptor, reels);
		
		assertEquals(expected, actual);
	}

}
