package com.androidbegin.menuviewpagertutorial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.content.res.Resources;
import android.util.Log;

public class ShoppingCartHelper {
	
	public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
	static List<ParseObject> ob;
	private static List<WorldPopulation> catalog;
	private static List<WorldPopulation> cart;
	private static Map<WorldPopulation, ShoppingCartEntry> cartMap = new HashMap<WorldPopulation, ShoppingCartEntry>();
	
	public static List<WorldPopulation> getCatalog(Resources res){
		if(catalog == null) {
			catalog = new Vector<WorldPopulation>();
			catalog = new ArrayList<WorldPopulation>();
			try {
				// Locate the class table named "Country" in Parse.com
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"Product");
				//query.orderByAscending("Product");
				ob = query.find();;
				for (ParseObject Product : ob) {
					
					ParseFile image = (ParseFile) Product.get("Picture");
					WorldPopulation map = new WorldPopulation();
					if ( Product.get("Category").equals("fruits and vegetables")) {
					map.setPrice((String) Product.get("Price"));
					map.setProduct((String) Product.get("Product"));
					map.setPicture(image.getUrl());
					catalog.add(map);
					}
				}
			} catch (ParseException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			}
			//catalog.add(new WorldPopulation("Dead or Alive", res
				//	.getDrawable(R.drawable.deadoralive),
					//"Dead or Alive by Tom Clancy with Grant Blackwood", 29.99));
			//catalog.add(new WorldPopulation("Switch", res
				//	.getDrawable(R.drawable.switchbook),
					//"Switch by Chip Heath and Dan Heath", 24.99));
			//catalog.add(new WorldPopulation("Watchmen", res
				//	.getDrawable(R.drawable.watchmen),
					//"Watchmen by Alan Moore and Dave Gibbons", 14.99));
		}
		
		return catalog;
	}
	public static void setQuantity(WorldPopulation product, int quantity) {
		// Get the current cart entry
		ShoppingCartEntry curEntry = cartMap.get(product);
		
		// If the quantity is zero or less, remove the products
		if(quantity <= 0) {
			if(curEntry != null)
				removeProduct(product);
			return;
		}
		
		// If a current cart entry doesn't exist, create one
		if(curEntry == null) {
			curEntry = new ShoppingCartEntry (product, quantity);
			cartMap.put(product, curEntry);
			return;
		}
		
		// Update the quantity
		curEntry.setQuantity(quantity);
	}
	public static void setPrice(WorldPopulation selectedProduct, int subTotal) {
		// Get the current cart entry
		ShoppingCartEntry curEntry = cartMap.get(selectedProduct);
		curEntry.setPrice(subTotal);
	}

	public static int getProductQuantity(WorldPopulation product) {
		// Get the current cart entry
		ShoppingCartEntry curEntry = cartMap.get(product);
		
		if(curEntry != null)
			return curEntry.getQuantity();
		
		return 0;
	}
	public static int getProductPrice(WorldPopulation product) {
		// Get the current cart entry
		ShoppingCartEntry curEntry = cartMap.get(product);
		
		if(curEntry != null)
			return curEntry.getPrice();
		
		return 0;
	}
	public static void removeProduct(WorldPopulation product) {
		cartMap.remove(product);
	}	

	public static List<WorldPopulation> getCartList() {
		List<WorldPopulation> cartList = new Vector<WorldPopulation>(cartMap.keySet().size());
		for(WorldPopulation p : cartMap.keySet()) {
			cartList.add(p);
		}
		
		return cartList;
	}
	public static List<WorldPopulation> getCart() {
		if(cart == null) {
			cart = new Vector<WorldPopulation>();
		}
		
		return cart;
	}

}
