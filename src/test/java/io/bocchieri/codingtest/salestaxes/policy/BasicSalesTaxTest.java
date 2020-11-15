package io.bocchieri.codingtest.salestaxes.policy;

import io.bocchieri.codingtest.salestaxes.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class BasicSalesTaxTest {

	private SalesTax.BasicSalesTax basicSalesTax;

	@BeforeEach
	void setUp() {
		basicSalesTax = new SalesTax.BasicSalesTax((value) -> value);
	}

	@Test
	void notApplicableProductShould() {
		Product product = new Product("product", Product.ProductType.BOOK, Product.ProductOrigin.LOCAL, BigDecimal.valueOf(10));

		assertFalse(basicSalesTax.isApplicable(product));
		assertEquals(BigDecimal.ZERO, basicSalesTax.calculate(product));
	}

	@Test
	void applicableProductShould() {
		Product product = new Product("product", Product.ProductType.OTHER, Product.ProductOrigin.LOCAL, BigDecimal.valueOf(10));

		assertTrue(basicSalesTax.isApplicable(product));
		assertEquals(BigDecimal.valueOf(1.0), basicSalesTax.calculate(product));
	}

	@Test
	void rateShould() {
		assertEquals(BigDecimal.valueOf(0.1), basicSalesTax.rate());
	}

}