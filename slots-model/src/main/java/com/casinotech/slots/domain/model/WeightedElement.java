package com.casinotech.slots.domain.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent=true)
public class WeightedElement<T> implements Weighted{
	private final T element;
	private final Long weight;
}
