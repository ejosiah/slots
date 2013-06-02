package com.casinotech.slots.domain.model;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public final class DomainRegistry implements ApplicationContextAware {
	
	private static ApplicationContext context;
	
	DomainRegistry() {}

	public void setApplicationContext(ApplicationContext context) throws BeansException {
		DomainRegistry.context = context;
	}
}
