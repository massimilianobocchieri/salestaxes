package io.bocchieri.codingtest.salestaxes.policy;

import io.bocchieri.codingtest.salestaxes.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ImportDutyTest {

	private SalesTax.ImportDuty importDuty;

	@BeforeEach
	void setUp() {
		importDuty = new SalesTax.ImportDuty((value) -> value);
	}

	@Test
	void rateShould() {
		assertEquals(BigDecimal.valueOf(0.05), importDuty.rate());
	}

	@Test
	void importedProductShould() {
		Product product = new Product("product", Product.ProductType.OTHER, Product.ProductOrigin.IMPORTED, BigDecimal.valueOf(10));

		assertTrue(importDuty.isApplicable(product));
		assertEquals(new BigDecimal("0.50"), importDuty.calculate(product));

	}

	@Test
	void localProductShould() {
		Product product = new Product("product", Product.ProductType.OTHER, Product.ProductOrigin.LOCAL, BigDecimal.valueOf(10));

		assertFalse(importDuty.isApplicable(product));
		assertEquals(BigDecimal.ZERO, importDuty.calculate(product));
	}
}