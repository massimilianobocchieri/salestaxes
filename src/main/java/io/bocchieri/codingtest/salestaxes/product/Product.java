package io.bocchieri.codingtest.salestaxes.product;

import java.math.BigDecimal;

public class Product {
	private final String name;
	private final ProductType type;
	private final ProductOrigin origin;
	private final BigDecimal price;

	public Product(String name, ProductType type, ProductOrigin origin, BigDecimal price) {
		this.name = name;
		this.type = type;
		this.origin = origin;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public ProductType getType() {
		return type;
	}

	public ProductOrigin getOrigin() {
		return origin;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public enum ProductType {
		BOOK, FOOD, MEDICAL, OTHER
	}

	public enum ProductOrigin {
		LOCAL, IMPORTED
	}
}
