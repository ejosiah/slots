package gamesoft.slots.domain.model.slot;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import gamesoft.slots.domain.model.slot.ReelResultInterceptor;
import gamesoft.slots.domain.model.slot.ReelSpinResult;
import gamesoft.slots.domain.model.slot.ReelSpinResultInterceptorChain;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ReelSpinResultInterceptorChainUTest {
	
	@Mock ReelSpinResult reelSpinResult;
	@Mock ReelSpinResult reelSpinResult1;
	@Mock ReelSpinResult reelSpinResult2;
	@Mock ReelResultInterceptor interceptor1;
	@Mock ReelResultInterceptor interceptor2;
	
	List<ReelResultInterceptor> interceptors;
	ReelSpinResultInterceptorChain reelSpinResultInterceptorChain;
	
	@Before
	public void setUp() throws Exception{
		interceptors = Arrays.asList(interceptor1, interceptor2);
		reelSpinResultInterceptorChain = new ReelSpinResultInterceptorChain(interceptors);
	}
	

	@Test
	public void testIntercept() {
		when(interceptor1.intercept(reelSpinResult)).thenReturn(reelSpinResult1);
		when(interceptor2.intercept(reelSpinResult1)).thenReturn(reelSpinResult2);
		
		ReelSpinResult result = reelSpinResultInterceptorChain.intercept(reelSpinResult);
		verify(interceptor1).intercept(reelSpinResult);
		verify(interceptor2).intercept(reelSpinResult1);
		
		ReelSpinResult expected = reelSpinResult2;
		ReelSpinResult actual = result;
		
		assertEquals(expected, actual);
	}

}
