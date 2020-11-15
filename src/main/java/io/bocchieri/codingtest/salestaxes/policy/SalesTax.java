package io.bocchieri.codingtest.salestaxes.policy;

import io.bocchieri.codingtest.salestaxes.product.Product;

import java.math.BigDecimal;

public abstract class SalesTax {
	private final RoundingRules roundingRules;

	public SalesTax(RoundingRules roundingRules) {
		this.roundingRules = roundingRules;
	}

	public BigDecimal calculate(Product product) {
		return isApplicable(product) ? roundingRules.round(rate().multiply(product.getPrice())) : BigDecimal.ZERO;
	}

	protected abstract boolean isApplicable(Product product);

	protected abstract BigDecimal rate();


	public static class BasicSalesTax extends SalesTax {
		public BasicSalesTax(RoundingRules roundingRules) {
			super(roundingRules);
		}

		@Override
		protected boolean isApplicable(Product product) {
			return product.getType() == Product.ProductType.OTHER;
		}

		@Override
		protected BigDecimal rate() {
			return BigDecimal.valueOf(0.1);
		}
	}

	public static class ImportDuty extends SalesTax {
		public ImportDuty(RoundingRules roundingRules) { super(roundingRules); }

		@Override
		protected boolean isApplicable(Product product) {
			return product.getOrigin() == Product.ProductOrigin.IMPORTED;
		}

		@Override
		protected BigDecimal rate() {
			return BigDecimal.valueOf(0.05);
		}
	}
}
