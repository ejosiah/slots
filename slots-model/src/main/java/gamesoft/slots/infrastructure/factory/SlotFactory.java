package gamesoft.slots.infrastructure.factory;

import gamesoft.slots.domain.model.Slot;

import org.springframework.beans.factory.FactoryBean;


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
