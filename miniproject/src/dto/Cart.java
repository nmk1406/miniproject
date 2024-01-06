package dto;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private List<Item> items;

	public Cart() {
		items = new ArrayList<>();
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public Item getItemById(int id) {
		for (Item item : items) {
			if (item.getProduct().getId() == id) {
				return item;
			}
		}
		return null;
	}
	
	public int getQuantityById(int id) {
		return getItemById(id).getQuantity();
	}
	
	public void addItem(Item item) {
		if (getItemById(item.getProduct().getId()) != null) {
			Item tempItem = getItemById(item.getProduct().getId());
			tempItem.setQuantity(tempItem.getQuantity() + item.getQuantity());
		} else {
			items.add(item);
		}
	}
	
	public void removeItem(int id) {
		if (getItemById(id) != null) {
			items.remove(getItemById(id));
		}
	}
	
	public double getTotalmoney() {
		double totalmoney = 0;
		for (Item item : items) {
			totalmoney += (item.getPrice() * item.getQuantity());
		}
		return totalmoney;
	}
	
	private Product getProductById(int id, List<Product> products) {
		for (Product product : products) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}
	
	public Cart(String txt, List<Product> products) {
		items = new ArrayList<>();
		try {
			if (txt != null && txt.length() != 0) {
				String[] txt1 = txt.split("/");
				for (String txt2 : txt1) {
					String[] txt3 = txt2.split(":");
					int id = Integer.parseInt(txt3[0]);
					int quantity = Integer.parseInt(txt3[1]);
					Product product = getProductById(id, products);
					Item item = new Item(product, quantity, product.getPrice() * 2);
					
					addItem(item);
				}
			}
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
	}
}