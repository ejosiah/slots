package gamesoft.slots.domain.model.slot;

import java.lang.reflect.Field;
import java.util.Map;

import lombok.SneakyThrows;


public final class ReflectionTestUtils {
	
	@SneakyThrows
	public static final void setFiled(String fieldName, Object target, Object value){
		Field field = target.getClass().getDeclaredField(fieldName);
		field.setAccessible(true);
		field.set(target, value);
	}
	
	public static void main(String[] args){
		System.out.println(TestHelper.combos.get(32));
	}
}
