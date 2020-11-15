package io.bocchieri.codingtest.salestaxes.order;

import io.bocchieri.codingtest.salestaxes.policy.TaxesCalculator;
import io.bocchieri.codingtest.salestaxes.product.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Order {
	private final List<OrderItem> items;
	private final TaxesCalculator taxesCalculator;

	public Order(TaxesCalculator taxesCalculator) {
		this.taxesCalculator = taxesCalculator;
		this.items = new ArrayList<>();
	}

	public void addItem(int quantity, Product product) {
		items.add(new OrderItem(quantity, product, taxesCalculator.calculate(product)));
	}

	public List<OrderItem> items() {
		return new ArrayList<>(items);
	}

	public BigDecimal cost() {
		return items.stream()
				.map(OrderItem::shelfPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public BigDecimal taxes() {
		return items.stream()
				.map(OrderItem::taxes)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}


	public static class OrderItem {
		private final int quantity;
		private final Product product;
		private final BigDecimal taxesForUnity;

		public OrderItem(int quantity, Product product, BigDecimal taxesForUnit) {
			this.quantity = quantity;
			this.product = product;
			this.taxesForUnity = taxesForUnit;
		}

		public Product getProduct() {
			return product;
		}

		public Integer getQuantity() {
			return quantity;
		}

		public BigDecimal taxes() {
			return
					taxesForUnity
							.multiply(BigDecimal.valueOf(quantity));
		}

		public BigDecimal shelfPrice() {
			return product.getPrice()
					.multiply(BigDecimal.valueOf(quantity))
					.add(taxes());
		}
	}
}
