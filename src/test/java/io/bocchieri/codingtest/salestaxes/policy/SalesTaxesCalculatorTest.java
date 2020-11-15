package io.bocchieri.codingtest.salestaxes.policy;

import io.bocchieri.codingtest.salestaxes.product.Product;
import io.bocchieri.codingtest.salestaxes.product.Product.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static io.bocchieri.codingtest.salestaxes.product.Product.ProductOrigin.IMPORTED;
import static io.bocchieri.codingtest.salestaxes.product.Product.ProductOrigin.LOCAL;
import static io.bocchieri.codingtest.salestaxes.product.Product.ProductType.OTHER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SalesTaxesCalculatorTest {
	private SalesTaxesCalculator salesTaxesCalculator;

	@BeforeEach
	void setUp() {
		salesTaxesCalculator = new SalesTaxesCalculator();
	}

	@ParameterizedTest(name = "{index}) {0}")
	@CsvSource({"BOOK", "FOOD", "MEDICAL"})
	void noTaxForLocalAndBaseTaxExemptProduct(String productTypeDescription) {
		assertEquals(BigDecimal.ZERO,
				salesTaxesCalculator.calculate(new Product("product", ProductType.valueOf(productTypeDescription), LOCAL, BigDecimal.valueOf(10)))
		);
	}

	@Test
	void baseTaxOnlyForLocalNotExemptProduct() {
		assertEquals(new BigDecimal("1.00"),
				salesTaxesCalculator.calculate(new Product("product", OTHER, LOCAL, BigDecimal.valueOf(10)))
		);
	}

	@ParameterizedTest(name = "{index}) {0}")
	@CsvSource({"BOOK", "FOOD", "MEDICAL"})
	void importDutyOnlyForImportedBaseTaxExemptProduct(String productTypeDescription) {
		assertEquals(new BigDecimal("0.50"),
				salesTaxesCalculator.calculate(new Product("product", ProductType.valueOf(productTypeDescription), IMPORTED, BigDecimal.valueOf(10)))
		);
	}

	@Test
	void bothBaseTaxAndImportDutyForImportedNotExemptProduct() {
		assertEquals(new BigDecimal("1.50"),
				salesTaxesCalculator.calculate(new Product("product", OTHER, IMPORTED, BigDecimal.valueOf(10)))
		);
	}

	@Test
	void nearestFiveCentsUpRoundingIsApplied() {
		assertEquals(new BigDecimal("1.05"),
				salesTaxesCalculator.calculate(new Product("product", OTHER, LOCAL, BigDecimal.valueOf(10.123)))
		);
	}
}