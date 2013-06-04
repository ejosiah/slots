package gamesoft.slots.domain.model;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.Mockito.when;

import gamesoft.slots.domain.model.RandomSpinner;
import gamesoft.slots.domain.model.Reel;
import gamesoft.slots.domain.model.Symbol;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import com.casinotech.random.RandomNumberGenerator;

@RunWith(MockitoJUnitRunner.class)
public class RandomSpinnerUTest {
	
	@Mock private RandomNumberGenerator mockRNG;
	
	@InjectMocks
	private RandomSpinner spinner;
	
	private Reel reel;

	@Before
	public void setUp() throws Exception {
		reel = new Reel(0, TestHelper.loadSymbols(), null);
	}

	@Test
	public void test() {
		int numberOfSymbols = reel.numberOfSymbols();
		List<Symbol> expected = expected();
		
		when(mockRNG.nextInt(numberOfSymbols)).thenAnswer(new RNGAnswer());
		
		List<Symbol> actual = spinner.spin(reel, 5);
		
		assertThat(actual, equalTo(expected));
		
	}
	
	public List<Symbol> expected(){
		List<Symbol> symbols = new ArrayList<Symbol>();
		symbols.add(Symbol.wildSymbol(10, "WILD", 36));
		symbols.add(Symbol.newSymbol(11, "SCATTER", 23));
		symbols.add(Symbol.newSymbol(8, "SYMBOL_8", 56));
		symbols.add(Symbol.newSymbol(5, "SYMBOL_5", 6));
		symbols.add(Symbol.newSymbol(9, "SYMBOL_9", 15));
		
		return symbols;
	}
	
	private static class RNGAnswer implements Answer<Integer>{
		private static int count;
		private int[] indexes = {36, 23, 56, 6, 15};
		
		public Integer answer(InvocationOnMock invocation) throws Throwable {
			return indexes[count++];
		}
		
	}

}
