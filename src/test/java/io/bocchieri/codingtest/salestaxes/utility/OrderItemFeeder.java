package io.bocchieri.codingtest.salestaxes.utility;

import io.bocchieri.codingtest.salestaxes.order.Order;
import io.bocchieri.codingtest.salestaxes.product.Product;

import java.math.BigDecimal;
import java.util.List;

public class OrderItemFeeder {
	public static void feedAll(Order order, List<String> orderItemDescriptions) {
		orderItemDescriptions.forEach(oid -> feed(order, oid));
	}

	public static void feed(Order order, String orderItemDescription) {
		String[] parts = orderItemDescription.split(" at ");
		String[] quantityAndName = parts[0].split(" ", 2);
		int quantity = Integer.parseInt(quantityAndName[0]);
		String name = quantityAndName[1];
		BigDecimal price = new BigDecimal(parts[1]);

		order.addItem(quantity, new Product(name, productTypeFromName(name), productOriginFromName(name), price));
	}

	private static Product.ProductType productTypeFromName(String name) {
		if (name.contains("book"))
			return Product.ProductType.BOOK;
		else if (name.contains("chocolate"))
			return Product.ProductType.FOOD;
		else if (name.contains("pills"))
			return Product.ProductType.MEDICAL;
		else
			return Product.ProductType.OTHER;
	}

	private static Product.ProductOrigin productOriginFromName(String name) {
		return name.contains("imported") ? Product.ProductOrigin.IMPORTED : Product.ProductOrigin.LOCAL;
	}
}
