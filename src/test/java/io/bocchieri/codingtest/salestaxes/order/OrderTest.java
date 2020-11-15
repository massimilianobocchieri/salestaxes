package io.bocchieri.codingtest.salestaxes.order;

import io.bocchieri.codingtest.salestaxes.policy.TaxesCalculator;
import io.bocchieri.codingtest.salestaxes.utility.OrderItemFeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderTest {

	private Order order;

	@BeforeEach
	void setUp() {
		TaxesCalculator flatTaxesCalculator = (p) -> BigDecimal.valueOf(1);
		order = new Order(flatTaxesCalculator);
	}

	@Test
	void itemsAreAdded() {
		OrderItemFeeder.feedAll(order, asList("1 other product at 10.00", "1 other book at 10.00"));

		assertEquals(2, order.items().size());
	}

	@Test
	void whenItemIsAddedUnitaryTaxesAreCalculated() {
		OrderItemFeeder.feed(order, "1 other product at 10.00");

		assertEquals(BigDecimal.valueOf(1), order.items().get(0).taxes());
	}

	@Test
	void totalCostShould() {
		OrderItemFeeder.feedAll(order, asList("1 other product at 10.00", "1 imported product at 20.00"));

		assertEquals(new BigDecimal("32.00"), order.cost());
	}

	@Test
	void totalTaxesShould() {
		OrderItemFeeder.feedAll(order, asList("2 other product at 10.00", "1 imported book at 20.00"));

		assertEquals(BigDecimal.valueOf(3), order.taxes());
	}
}