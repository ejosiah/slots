package gamesoft.slots.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
@SuppressWarnings("PMD.UnusedPrivateField")
public class WeightedElement<T> implements Weighted{
	private final T element;
	private final Long weight;
}
