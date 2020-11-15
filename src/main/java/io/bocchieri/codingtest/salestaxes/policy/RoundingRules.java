package io.bocchieri.codingtest.salestaxes.policy;

import java.math.BigDecimal;

public interface RoundingRules {
	BigDecimal round(BigDecimal value);
}
