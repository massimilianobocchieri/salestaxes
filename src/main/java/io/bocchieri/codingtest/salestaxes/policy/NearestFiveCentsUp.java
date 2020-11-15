package io.bocchieri.codingtest.salestaxes.policy;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NearestFiveCentsUp implements RoundingRules {
	@Override
	public BigDecimal round(BigDecimal value) {
		return value
				.divide(BigDecimal.valueOf(0.05), 0, RoundingMode.UP)
				.multiply(BigDecimal.valueOf(0.05))
				.setScale(2, RoundingMode.UP);
	}
}