package com.casinotech.slots.infrastructure.factory;

import org.springframework.beans.factory.FactoryBean;

import com.casinotech.slots.domain.model.Slot;

public class SlotFactory implements FactoryBean<Slot> {
	private String slotId;
	
	public SlotFactory(String slotId){
		this.slotId = slotId;
	}

	public Slot getObject() throws Exception {
		// TODO retreive slot from data store
		return null;
	}

	public Class<?> getObjectType() {
		return Slot.class;
	}

	public boolean isSingleton() {
		return false;
	}

}
