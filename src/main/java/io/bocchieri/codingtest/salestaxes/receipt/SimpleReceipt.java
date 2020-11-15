package io.bocchieri.codingtest.salestaxes.receipt;

import io.bocchieri.codingtest.salestaxes.order.Order;

public class SimpleReceipt implements Receipt {
	private static final String SPACE = " ";
	private static final String SEPARATOR = ": ";
	private static final String NEWLINE = System.lineSeparator();

	private final Order order;

	public SimpleReceipt(Order order) {
		this.order = order;
	}

	@Override
	public String details() {

		return formatItems()
				.append(formatSalesTaxes())
				.append(formatTotal())
				.toString();
	}

	private StringBuffer formatItems() {
		StringBuffer items = new StringBuffer();
		order.items().forEach(i -> appendFormattedItemData(items, i));
		return items;
	}

	private void appendFormattedItemData(StringBuffer items, Order.OrderItem item) {
		items.append(item.getQuantity())
				.append(SPACE)
				.append(item.getProduct().getName())
				.append(SEPARATOR)
				.append(item.shelfPrice())
				.append(NEWLINE);
	}

	private StringBuffer formatSalesTaxes() {
		return new StringBuffer()
				.append("Sales Taxes")
				.append(SEPARATOR)
				.append(order.taxes())
				.append(NEWLINE);
	}

	private StringBuffer formatTotal() {
		return new StringBuffer()
				.append("Total")
				.append(SEPARATOR)
				.append(order.cost())
				.append(NEWLINE);
	}
}
