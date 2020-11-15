package io.bocchieri.codingtest.salestaxes.policy;

import io.bocchieri.codingtest.salestaxes.policy.SalesTax.BasicSalesTax;
import io.bocchieri.codingtest.salestaxes.policy.SalesTax.ImportDuty;
import io.bocchieri.codingtest.salestaxes.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class SalesTaxesCalculator implements TaxesCalculator {
	private static final RoundingRules roundingRules = new NearestFiveCentsUp();
	private static final List<SalesTax> taxes = new ArrayList<>(asList(new BasicSalesTax(roundingRules), new ImportDuty(roundingRules)));

	@Override
	public BigDecimal calculate(Product product) {
		return taxes.stream().map(t -> t.calculate(product))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}
}
