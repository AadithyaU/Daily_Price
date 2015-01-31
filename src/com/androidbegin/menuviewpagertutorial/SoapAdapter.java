package com.androidbegin.menuviewpagertutorial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Vector;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class SoapAdapter extends BaseAdapter {

	// Declare Variables
	Context context;
	LayoutInflater inflater;
	ImageLoader imageLoader;
    private SparseBooleanArray mSelectedItemsIds;
	private static List<WorldPopulation> soaplist = null;
	public static final String PRODUCT_INDEX = "PRODUCT_INDEX";
	private static Map<WorldPopulation, ShoppingCartEntry> cartMap = new HashMap<WorldPopulation, ShoppingCartEntry>();
	private static ArrayList<WorldPopulation> arraylist;
	private boolean mShowCheckbox;
	private List<WorldPopulation> mCartList = ShoppingCartHelper.getCartList();


	public SoapAdapter(Context context,
			List<WorldPopulation> soaplist) {
		this.context = context;
		this.soaplist = soaplist;
		inflater = LayoutInflater.from(context);
		this.arraylist = new ArrayList<WorldPopulation>();
		this.arraylist.addAll(soaplist);
		imageLoader = new ImageLoader(context);
	}
	
	public class ViewHolder {
		TextView Price;
		TextView Product;
		ImageView Picture;
		WorldPopulation obj;
	}

	@Override
	public int getCount() {
		return soaplist.size();
	}

	@Override
	public WorldPopulation getItem(int position) {
		return soaplist.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	public static List<WorldPopulation> getCatalog(Resources res){
		//if(catalog == null) {
		//	catalog = new Vector<WorldPopulation>();
		//	catalog=SoapAdapter.arraylist;
			
		//}
		
		return soaplist;
	}

	public View getView(final int position, View view, ViewGroup parent) {
		final ViewHolder holder;
		if (view == null) {
			holder = new ViewHolder();
			view = inflater.inflate(R.layout.listview_item, null);
			// Locate the TextViews in listview_item.xml
			holder.Price = (TextView) view.findViewById(R.id.Price);
			holder.Product = (TextView) view.findViewById(R.id.Product);
			holder.Picture = (ImageView) view.findViewById(R.id.Picture);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		
		// Set the results into TextViews
		holder.Price.setText(soaplist.get(position).getPrice());
		holder.Product.setText(soaplist.get(position).getProduct());
		// Set the results into ImageView
		imageLoader.DisplayImage(soaplist.get(position).getPicture(),
				holder.Picture);

		final View temp = view;
		final ImageButton button1 = (ImageButton) view.findViewById(R.id.overflow);  
        final WorldPopulation selectedProduct = soaplist.get(position);
        for(WorldPopulation p : mCartList) {
      	  if(p.getCategory().equals(selectedProduct.getCategory()) && p.getProduct().equals(selectedProduct.getProduct())  && (ShoppingCartHelper.getProductQuantity(p) != SoapAdapter.getProductQuantity(selectedProduct))){
      		  SoapAdapter.setQuantity(selectedProduct, ShoppingCartHelper.getProductQuantity(p));
      		  ShoppingCartHelper.setQuantity(selectedProduct, ShoppingCartHelper.getProductQuantity(p));
      		  ShoppingCartHelper.setQuantity(p, 0);
      		  
      	  }
        }
        
        if(SoapAdapter.getProductQuantity(selectedProduct) != 0) {
        	temp.setBackgroundColor(0x5500cc99);
        }
        else {
			temp.setBackgroundColor(0x00000000);
        }
        button1.setOnClickListener(new OnClickListener() {  
         
         @Override  
         public void onClick(View v) {  
             if(SoapAdapter.getProductQuantity(selectedProduct) != 0) {
             	temp.setBackgroundColor(0x5500cc99);
             }
             else {
     			temp.setBackgroundColor(0x00000000);
             }
          //Creating the instance of PopupMenu  
          PopupMenu popup = new PopupMenu(context, button1);
          final WorldPopulation selectedProduct = soaplist.get(position);
          if(SoapAdapter.getProductQuantity(selectedProduct) == 0) { 
          //Inflating the Popup using xml file  
          popup.getMenuInflater().inflate(R.menu.breakfast, popup.getMenu());  
          //registering popup with OnMenuItemClickListener  
          popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {    
		 public boolean onMenuItemClick(android.view.MenuItem item) {  
			 
			 final AlertDialog.Builder alert = new AlertDialog.Builder(context);
		     TextView t = new TextView(context);
		     LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		     View view = li.inflate(R.layout.numberpickerdialog, (ViewGroup) temp.findViewById(R.id.numberPicker1));
		     final NumberPicker np = (NumberPicker) view.findViewById(R.id.numberPicker1);
	         np.setMaxValue(100); // max value 100
	         alert.setView(view);
	         np.setMinValue(0);   // min value 0
	         np.setValue(SoapAdapter.getProductQuantity(selectedProduct));
	         np.setWrapSelectorWheel(true);
	         np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	         alert.setCancelable(false)
	         .setTitle("Quantity Picker")
	       	.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			})    
	       	.setPositiveButton("Set",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					final int m = np.getValue();
		        	 int flag = 0;
		        	 if((SoapAdapter.getProductQuantity(selectedProduct) != 0) && m == 0) 
			      			Toast.makeText(context, "Removed from Cart Successfully", 500).show();
			      		else if (m != 0 && (SoapAdapter.getProductQuantity(selectedProduct) == 0)) {
			      			Toast.makeText(context, "Added to Cart Successfully", 500).show(); 
			      				temp.setBackgroundColor(0x5500cc99);
			      			}
			      		else if (m!=0 && (SoapAdapter.getProductQuantity(selectedProduct) != 0) && (m != SoapAdapter.getProductQuantity(selectedProduct)))
			      			Toast.makeText(context, "Changed the Quantity Successfully", 500).show();
						ShoppingCartHelper.setQuantity(selectedProduct, m);
						SoapAdapter.setQuantity(selectedProduct, m);
		              dialog.dismiss();	
				}
			});    
	         alert.show();
             return true;  
            }  
          });  

          popup.show();//showing popup menu  
         }  
          else {
        	  popup.getMenuInflater().inflate(R.menu.detergents_item_view, popup.getMenu());  
              //registering popup with OnMenuItemClickListener  
              popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {    
    		 public boolean onMenuItemClick(android.view.MenuItem item) {  
    			 if(item.getTitle().equals("Change Quantity")) {
    			 
	       
    			 final AlertDialog.Builder alert = new AlertDialog.Builder(context);
    			 TextView t = new TextView(context);
    			 LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    			 View view = li.inflate(R.layout.numberpickerdialog, (ViewGroup) temp.findViewById(R.id.numberPicker1));
    			 final NumberPicker np = (NumberPicker) view.findViewById(R.id.numberPicker1);
    		     np.setMaxValue(100); // max value 100
    	         np.setMinValue(0); 
    	         alert.setView(view);
    	         np.setValue(SoapAdapter.getProductQuantity(selectedProduct));
    	         np.setWrapSelectorWheel(true);
    	         np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
    	         alert.setCancelable(false)
    	         .setTitle("Quantity Picker")
    	       	.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog,int id) {
    					dialog.cancel();
    				}
    			})    
    	       	.setPositiveButton("Set",new DialogInterface.OnClickListener() {
    				public void onClick(DialogInterface dialog,int id) {
    					final int m = np.getValue();
       	        	 int flag = 0;
       	        	 if((SoapAdapter.getProductQuantity(selectedProduct) != 0) && m == 0) {
       		      			Toast.makeText(context, "Removed from Cart Successfully", 500).show();
       		      			temp.setBackgroundColor(0x00000000);
       	        	 }
       		      		else if (m != 0 && (SoapAdapter.getProductQuantity(selectedProduct) == 0)) {
       		      			Toast.makeText(context, "Added to Cart Successfully", 500).show();
       		      			temp.setBackgroundColor(0x5500cc99);
       		      		}
       		      		else if (m!=0 && (SoapAdapter.getProductQuantity(selectedProduct) != 0) && (m != SoapAdapter.getProductQuantity(selectedProduct))) {
       		      			Toast.makeText(context, "Changed the Quantity Successfully", 500).show();
       	        	 		temp.setBackgroundColor(0x5500cc99);
       	        	 }
       	          	ShoppingCartHelper.setQuantity(selectedProduct, m);
       					SoapAdapter.setQuantity(selectedProduct, m);
       	              dialog.dismiss();	               
    				}
    			});    
    	         alert.show();
    			 }
    			 else {
    				 SoapAdapter.setQuantity(selectedProduct, 0);
    				 ShoppingCartHelper.setQuantity(selectedProduct, 0);
    				 Toast.makeText(context, "Removed from Cart Successfully", 500).show();
    				temp.setBackgroundColor(0x00000000);
    			 }
    			 
    		        if(SoapAdapter.getProductQuantity(selectedProduct) != 0) {
    		        	temp.setBackgroundColor(0x5500cc99);
    		        }
    		        else {
    					temp.setBackgroundColor(0x00000000);
    		        }
                 return true;  
                }  
              });  

              popup.show();
          }
         }});

		
		
		
		
		return view;
	}
	
    public void remove(WorldPopulation object) {
        soaplist.remove(object);
        notifyDataSetChanged();
    }
 
    public List<WorldPopulation> getWorldPopulation() {
        return soaplist;
    }
 
    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }
 
    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }
 
    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }
 
    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }
 
    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
	
	public void filter(String charText) {
		charText = charText.toLowerCase(Locale.getDefault());
		soaplist.clear();
		if (charText.length() == 0) {
			soaplist.addAll(arraylist);
		} else {
			for (WorldPopulation wp : arraylist) {
				if (wp.getProduct().toLowerCase(Locale.getDefault()).contains(charText)) {
					soaplist.add(wp);
				}
			}
		}
		notifyDataSetChanged();
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
			curEntry = new ShoppingCartEntry(product, quantity);
			cartMap.put(product, curEntry);
			return;
		}		
		// Update the quantity
		curEntry.setQuantity(quantity);
	}
	public static int getProductQuantity(WorldPopulation product) {
		// Get the current cart entry
		ShoppingCartEntry curEntry = cartMap.get(product);		
		if(curEntry != null)
			return curEntry.getQuantity();	
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
}
