package com.androidbegin.menuviewpagertutorial;

public class ShoppingCartEntry {
	
	private WorldPopulation mProduct;
	private int mQuantity;
	private int mPrice;
	
	public ShoppingCartEntry(WorldPopulation product, int quantity) {
		mProduct = product;
		mQuantity = quantity;
	}
	
	public WorldPopulation getProduct() {
		return mProduct;
	}
	
	public int getQuantity() {
		return mQuantity;
	}
	
	public void setQuantity(int quantity) {
		mQuantity = quantity;
	}

	public int getPrice() {
		return mPrice;
	}
	public void setPrice(int subTotal) {
		mPrice = subTotal;
	}

}
