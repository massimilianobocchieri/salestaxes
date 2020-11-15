package io.bocchieri.codingtest.salestaxes.policy;

import io.bocchieri.codingtest.salestaxes.product.Product;

import java.math.BigDecimal;

public interface TaxesCalculator {
	BigDecimal calculate(Product product);
}
