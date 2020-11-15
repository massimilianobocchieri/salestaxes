package io.bocchieri.codingtest.salestaxes.order;

import io.bocchieri.codingtest.salestaxes.product.Product;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static io.bocchieri.codingtest.salestaxes.product.Product.ProductOrigin.LOCAL;
import static io.bocchieri.codingtest.salestaxes.product.Product.ProductType.OTHER;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderItemTest {
	private static Product SAMPLE_PRODUCT;

	@BeforeAll
	static void setUpAll() {
		SAMPLE_PRODUCT = new Product("product", OTHER, LOCAL, BigDecimal.valueOf(10));
	}

	@Test
	void totalTaxesShouldBeUnitaryTaxesMultipliedByProductQuantity() {
		assertEquals(BigDecimal.valueOf(2), new Order.OrderItem(2, SAMPLE_PRODUCT, BigDecimal.valueOf(1)).taxes());
	}

	@Test
	void shelfPriceShouldBeTotalTaxesProductPriceTimesQuantity() {
		assertEquals(BigDecimal.valueOf(22), new Order.OrderItem(2, SAMPLE_PRODUCT, BigDecimal.valueOf(1)).shelfPrice());
	}

}