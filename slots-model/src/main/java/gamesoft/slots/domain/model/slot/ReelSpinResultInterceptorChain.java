package gamesoft.slots.domain.model.slot;

import java.util.List;

public class ReelSpinResultInterceptorChain implements ReelResultInterceptor {
	
	private List<ReelResultInterceptor> interceptors;
	
	public ReelSpinResultInterceptorChain(List<ReelResultInterceptor> interceptors){
		this.interceptors = interceptors;
	}

	public ReelSpinResult intercept(ReelSpinResult reelSpinResult) {
		ReelSpinResult result = reelSpinResult;
		for(ReelResultInterceptor interceptor : interceptors){
			result = interceptor.intercept(result);
		}
		return result;
	}

}
