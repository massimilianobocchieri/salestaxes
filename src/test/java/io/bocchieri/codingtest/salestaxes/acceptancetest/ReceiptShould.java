package io.bocchieri.codingtest.salestaxes.acceptancetest;

import io.bocchieri.codingtest.salestaxes.order.Order;
import io.bocchieri.codingtest.salestaxes.policy.SalesTaxesCalculator;
import io.bocchieri.codingtest.salestaxes.receipt.SimpleReceipt;
import io.bocchieri.codingtest.salestaxes.utility.OrderItemFeeder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReceiptShould {

	private Order order;

	@BeforeEach
	void setUp() {
		order = new Order(new SalesTaxesCalculator());
	}

	@DisplayName("Receipt Details Acceptance Test")
	@ParameterizedTest(name = "{index}) {0}")
	@ArgumentsSource(TestArgumentsProvider.class)
	void should(String scenario, List<String> itemsDescriptions, String expectedResult) {
		OrderItemFeeder.feedAll(order, itemsDescriptions);

		assertEquals(expectedResult, new SimpleReceipt(order).details());
	}

	static class TestArgumentsProvider implements ArgumentsProvider {
		@Override
		public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
			return Stream.of(
					Arguments.of("Input 1",
							Arrays.asList("1 book at 12.49",
									"1 music CD at 14.99",
									"1 chocolate bar at 0.85"
							),
							"1 book: 12.49\n" +
									"1 music CD: 16.49\n" +
									"1 chocolate bar: 0.85\n" +
									"Sales Taxes: 1.50\n" +
									"Total: 29.83\n"),
					Arguments.of("Input 2",
							Arrays.asList("1 imported box of chocolates at 10.00",
									"1 imported bottle of perfume at 47.50"
							),
							"1 imported box of chocolates: 10.50\n" +
									"1 imported bottle of perfume: 54.65\n" +
									"Sales Taxes: 7.65\n" +
									"Total: 65.15\n"),
					Arguments.of("Input 3",
							Arrays.asList("1 imported bottle of perfume at 27.99",
									"1 bottle of perfume at 18.99",
									"1 packet of headache pills at 9.75",
									"1 imported box of chocolates at 11.25"
							),
							"1 imported bottle of perfume: 32.19\n" +
									"1 bottle of perfume: 20.89\n" +
									"1 packet of headache pills: 9.75\n" +
									"1 imported box of chocolates: 11.85\n" +
									"Sales Taxes: 6.70\n" +
									"Total: 74.68\n")
			);
		}
	}

}
