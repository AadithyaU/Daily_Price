package com.androidbegin.menuviewpagertutorial;

import java.util.List;

import org.w3c.dom.ls.LSInput;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import com.androidbegin.menuviewpagertutorial.*;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ShoppingCartActivity extends Activity {
	
	private List<WorldPopulation> mCartList;
	private ProductAdapter mProductAdapter;
	private TextView tv;
    static Dialog d ;
	public static int nr = 0;
	public static ListView listViewCatalog;
	 ActionMode.Callback mCallback;    
     ActionMode mMode; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.shoppingcart);
		mCartList = ShoppingCartHelper.getCartList();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
		mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true , true , true);
		listViewCatalog.setAdapter(mProductAdapter);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		double sub = 0;
		// Make sure to clear the selections
		for(int i=0; i<mCartList.size(); i++) 
			mCartList.get(i).selected = false;
        listViewCatalog.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);       
        listViewCatalog.setMultiChoiceModeListener(new MultiChoiceModeListener() {
             
                         
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
             
            @Override
            public void onDestroyActionMode(ActionMode mode) {
                // TODO Auto-generated method stub
            	for(int i=0; i<mCartList.size(); i++) 
        			mCartList.get(i).selected = false;

            }
             
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                // TODO Auto-generated method stub
                 
                nr = 0;
                MenuInflater inflater = getMenuInflater();
                inflater.inflate(R.menu.finalactivity, menu);
                return true;
            }


             
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                // TODO Auto-generated method stub
                switch (item.getItemId()) {
                 
                    case R.id.item_delete:
                        nr = 0;
                        for(int i=mCartList.size()-1; i>=0; i--) {
        					
        					if(mCartList.get(i).selected) {
        						WorldPopulation wp = mCartList.get(i);
        						ShoppingCartHelper.setQuantity(wp, 0);
        						if(wp.getCategory().equals("snacks"))
        							ListViewAdapter1.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("breakfast food"))
        							  BreakfastAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("detergent"))
        							  DetergentsAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("drinks and juices"))
        							  DrinksandjuicesAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("tea, coffee and health drinks"))
        							  TeaandcoffeeAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("soaps and shampoos"))
        							  SoapAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("handwash and shampoo"))
        							  HandwashandshampooAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("masalas and spices"))
        							  MasalaandspicesAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("refrigerated"))
        							  RefrigeratedAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("salt, sugar and jaggery"))
        							  SaltsugarjaggeryAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("sanitary"))
        							  SanitaryAdapter.setQuantity(mCartList.get(i), 0);
        						  else if(wp.getCategory().equals("skin care"))
        							  SkincareAdapter.setQuantity(mCartList.get(i), 0);
        						  else
        							  ListViewAdapter.setQuantity(mCartList.get(i), 0);
        						mCartList.remove(i);
        					}
        				}
                        double subTotal = 0;
      	    		  for(WorldPopulation np : mCartList) {
      	    			  int quantity;
      					quantity = ShoppingCartHelper.getProductQuantity(np);
      	    		   int i1 = Integer.parseInt(np.getPrice());
      	    		   subTotal += quantity * i1;
      	    		  } 
      	    		  TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
      		  		  productPriceTextView.setText("Total:  ₹ " + subTotal + "      Total Items in Cart  " + mCartList.size());		  		  
      		  		  Toast.makeText(getApplicationContext(), "Removed from Cart Successfully", 100).show();
      				mProductAdapter.notifyDataSetChanged();
                        mode.finish();
                        break;
                    case R.id.item_delete_all:
                 
                    	 nr = 0;
                         for(int i=mCartList.size()-1; i>=0; i--) {
         						WorldPopulation wp = mCartList.get(i);
         						ShoppingCartHelper.setQuantity(wp, 0);
         						if(wp.getCategory().equals("snacks"))
         							ListViewAdapter1.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("breakfast food"))
         							  BreakfastAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("detergent"))
         							  DetergentsAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("drinks and juices"))
         							  DrinksandjuicesAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("tea, coffee and health drinks"))
         							  TeaandcoffeeAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("soaps and shampoos"))
         							  SoapAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("handwash and shampoo"))
         							  HandwashandshampooAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("masalas and spices"))
         							  MasalaandspicesAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("refrigerated"))
         							  RefrigeratedAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("salt, sugar and jaggery"))
         							  SaltsugarjaggeryAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("sanitary"))
         							  SanitaryAdapter.setQuantity(mCartList.get(i), 0);
         						  else if(wp.getCategory().equals("skin care"))
         							  SkincareAdapter.setQuantity(mCartList.get(i), 0);
         						  else
         							  ListViewAdapter.setQuantity(mCartList.get(i), 0);
         						mCartList.remove(i);
         					}
                          subTotal = 0;
       	    		  for(WorldPopulation np : mCartList) {
       	    			  int quantity;
       					quantity = ShoppingCartHelper.getProductQuantity(np);
       	    		   int i1 = Integer.parseInt(np.getPrice());
       	    		   subTotal += quantity * i1;
       	    		  } 
       	    		  TextView productPriceTextView1 = (TextView) findViewById(R.id.TextViewSubtotal);
       		  		  productPriceTextView1.setText("Total:  ₹ " + subTotal + "      Total Items in Cart  " + mCartList.size());
       		  		  if(mCartList.size() == 1)
       		  			Toast.makeText(getApplicationContext(), "Removed from Cart", 100).show();
       		  		  else 
       		  			  Toast.makeText(getApplicationContext(), "Removed all the items", 100).show();
       				mProductAdapter.notifyDataSetChanged();
                         mode.finish();
                         break;
                }
				return false;
            }
           
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position,
                    long id, boolean checked) {
            	
                 if (mCartList.get(position).selected) {
                        nr--;
                        mCartList.get(position).selected = false;  
                  } else {
                        nr++;
                        mCartList.get(position).selected = true;   
                    }
                    mode.setTitle(nr + " selected");
                 
            } 

        }); 
        
        
		
		
		// Create the list
	//	final ListView listViewCatalog = (ListView) findViewById(R.id.ListViewCatalog);
	//	mProductAdapter = new ProductAdapter(mCartList, getLayoutInflater(), true , true , true);
	//	listViewCatalog.setAdapter(mProductAdapter);
		 double subTotal = 0;
		  for(WorldPopulation p : mCartList) {
			  int quantity;
			  if(p.getCategory().equals("snacks"))
				  quantity = ListViewAdapter1.getProductQuantity(p);
			  else if(p.getCategory().equals("breakfast food"))
				  quantity = BreakfastAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("detergent"))
				  quantity = DetergentsAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("drinks and juices"))
				  quantity = DrinksandjuicesAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("tea, coffee and health drinks"))
				  quantity = TeaandcoffeeAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("soaps and shampoos"))
				  quantity = SoapAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("handwash and shampoo"))
				  quantity = HandwashandshampooAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("masalas and spices"))
				  quantity = MasalaandspicesAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("refrigerated"))
				  quantity = RefrigeratedAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("salt, sugar and jaggery"))
				  quantity = SaltsugarjaggeryAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("sanitary"))
				  quantity = SanitaryAdapter.getProductQuantity(p);
			  else if(p.getCategory().equals("skin care"))
				  quantity = SkincareAdapter.getProductQuantity(p);
			  else
				  quantity = ListViewAdapter.getProductQuantity(p);
		   int i = Integer.parseInt(p.getPrice());
		   subTotal += quantity * i;
		  }
		listViewCatalog.setClickable(true);   
		TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
		  productPriceTextView.setText("Total:  ₹ " + subTotal + "      Total Items in Cart  " + mCartList.size());
		
		  listViewCatalog.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position,
					long id) {
;
				show(position);	
				 double subTotal = 0;
	    		  for(WorldPopulation p : mCartList) {
	    			  int quantity;
	    			  if(p.getCategory().equals("snacks"))
	    				  quantity = ListViewAdapter1.getProductQuantity(p);
	    			  else if(p.getCategory().equals("breakfast food"))
	    				  quantity = BreakfastAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("detergent"))
	    				  quantity = DetergentsAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("drinks and juices"))
	    				  quantity = DrinksandjuicesAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("tea, coffee and health drinks"))
	    				  quantity = TeaandcoffeeAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("soaps and shampoos"))
	    				  quantity = SoapAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("handwash and shampoo"))
	    				  quantity = HandwashandshampooAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("masalas and spices"))
	    				  quantity = MasalaandspicesAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("refrigerated"))
	    				  quantity = RefrigeratedAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("salt, sugar and jaggery"))
	    				  quantity = SaltsugarjaggeryAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("sanitary"))
	    				  quantity = SanitaryAdapter.getProductQuantity(p);
	    			  else if(p.getCategory().equals("skin care"))
	    				  quantity = SkincareAdapter.getProductQuantity(p);
	    			  else
	    				  quantity = ListViewAdapter.getProductQuantity(p);
	    		   int i = Integer.parseInt(p.getPrice());
	    		   subTotal += quantity * i;
	    		  }
	    		  

	    		listViewCatalog.setClickable(true);   
	    		TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
	  		  productPriceTextView.setText("Total:  ₹ " + subTotal + "      Total Items in Cart  " + mCartList.size());

			}
		}); 
		listViewCatalog.setLongClickable(true);
		listViewCatalog.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position,
					long id) {
				
			      if (mMode != null) {
			            return false;
			       }
			        mMode = startActionMode(mCallback);

					WorldPopulation selectedProduct = mCartList.get(position);
					if(selectedProduct.selected == true) {
						selectedProduct.selected = false;
				        view.setSelected(false); 
					}
					else {
						selectedProduct.selected = true;
				        view.setSelected(true);
					}
					mProductAdapter.notifyDataSetInvalidated();
					return true;
				
		}});
}
	
	@Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      MenuInflater inflater = getMenuInflater();
	      inflater.inflate(R.menu.skin_careact, menu);
	      return super.onCreateOptionsMenu(menu);
	   }
 
 @Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if ( item.getItemId() == R.id.proceed) {
			if(mCartList.size() == 0) 
		  		  Toast.makeText(getApplicationContext(), "Cart is Empty", 100).show();
			else {
				Intent checkout = new Intent(getBaseContext(), Email.class);
				startActivity(checkout);
			}
		}

		return super.onOptionsItemSelected(item);
	}
	 public void show(final int position)
	    {
		 final AlertDialog.Builder alert = new AlertDialog.Builder(this);
	     	TextView t = new TextView(this);
	        LayoutInflater li = (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        View view = li.inflate(R.layout.numberpickerdialog, (ViewGroup) findViewById(R.id.numberPicker1));
	     	final NumberPicker num = (NumberPicker) view.findViewById(R.id.numberPicker1);
	        //NumberPicker num = new NumberPicker(this);
	     	//AlertDialog d = alert.create();
	    	alert.setCancelable(false)
	       	.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					dialog.cancel();
				}
			})        	
			.setPositiveButton("Set",new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog,int id) {
					 WorldPopulation pro = mCartList.get(position);
					int q = num.getValue();
					if( q != ShoppingCartHelper.getProductQuantity(pro)) {
		        	 if( q == 0) {
		        		 mCartList.remove(position);
		        		 ShoppingCartHelper.setQuantity(pro, 0);							        		 
		        		 if(pro.getCategory().equals("snacks"))
								ListViewAdapter1.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("breakfast food"))
								  BreakfastAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("detergent"))
								  DetergentsAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("drinks and juices"))
								  DrinksandjuicesAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("tea, coffee and health drinks"))
								  TeaandcoffeeAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("soaps and shampoos"))
								  SoapAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("handwash and shampoo"))
								  HandwashandshampooAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("masalas and spices"))
								  MasalaandspicesAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("refrigerated"))
								  RefrigeratedAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("salt, sugar and jaggery"))
								  SaltsugarjaggeryAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("sanitary"))
								  SanitaryAdapter.setQuantity(pro, 0);
							  else if(pro.getCategory().equals("skin care"))
								  SkincareAdapter.setQuantity(pro, 0);
							  else
								  ListViewAdapter.setQuantity(pro, 0);
		        		 
		        		 Toast.makeText(getApplicationContext(), "Removed from Cart Successfully", 100).show();
		        	 }
		        	 else {
		        		 if(pro.getCategory().equals("snacks"))
								ListViewAdapter1.setQuantity(pro, q);
							  else if(pro.getCategory().equals("breakfast food"))
								  BreakfastAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("detergent"))
								  DetergentsAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("drinks and juices"))
								  DrinksandjuicesAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("tea, coffee and health drinks"))
								  TeaandcoffeeAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("soaps and shampoos"))
								  SoapAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("handwash and shampoo"))
								  HandwashandshampooAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("masalas and spices"))
								  MasalaandspicesAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("refrigerated"))
								  RefrigeratedAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("salt, sugar and jaggery"))
								  SaltsugarjaggeryAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("sanitary"))
								  SanitaryAdapter.setQuantity(pro, q);
							  else if(pro.getCategory().equals("skin care"))
								  SkincareAdapter.setQuantity(pro, q);
							  else
								  ListViewAdapter.setQuantity(pro, q);
		        	  ShoppingCartHelper.setQuantity(pro, q);
		        	  Toast.makeText(getApplicationContext(), "Changed the Quantity Successfully", 100).show();
		        	 }
		        	  mProductAdapter.notifyDataSetInvalidated();
		        	  double subTotal = 0;
		    		  for(WorldPopulation p : mCartList) {
		    		   int quantity = ShoppingCartHelper.getProductQuantity(p);
		    		   int i = Integer.parseInt(p.getPrice());
		    		   subTotal += quantity * i;
		    		  }  
		    		  TextView productPriceTextView = (TextView) findViewById(R.id.TextViewSubtotal);
			  		  productPriceTextView.setText("Total:  ₹ " + subTotal + "      Total Items in Cart  " + mCartList.size());
					}
				}
			})
			.setTitle("Quantity Picker");
			//.setMessage("Choose the Quantity");
	     	num.setGravity(Gravity.CENTER);
	     	num.setMaxValue(100);
	     	num.setWrapSelectorWheel(true);
	        num.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
	     	num.setMinValue(0);
	     	//d.setCustomTitle(t);
	     	t.setText("Select the Quantity");
	    	t.setTextColor(0xffffffff);
	    	t.setGravity(Gravity.CENTER_HORIZONTAL);
	    	TextView t1 = new TextView(this);
	     	t1.setText("Quantity Picker");
	    	t1.setTextColor(0xffffffff);
	    	t1.setGravity(Gravity.CENTER_HORIZONTAL);
	        WorldPopulation p = mCartList.get(position);
	     	if(p.getCategory().equals("snacks"))
				  num.setValue(ListViewAdapter1.getProductQuantity(p));
			  else if(p.getCategory().equals("breakfast food"))
				  num.setValue(BreakfastAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("detergent"))
				  num.setValue(DetergentsAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("drinks and juices"))
				  num.setValue(DrinksandjuicesAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("tea, coffee and health drinks"))
				  num.setValue(TeaandcoffeeAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("soaps and shampoos"))
				  num.setValue(SoapAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("handwash and shampoo"))
				  num.setValue(HandwashandshampooAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("masalas and spices"))
				  num.setValue(MasalaandspicesAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("refrigerated"))
				  num.setValue(RefrigeratedAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("salt, sugar and jaggery"))
				  num.setValue(SaltsugarjaggeryAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("sanitary"))
				  num.setValue(SanitaryAdapter.getProductQuantity(p));
			  else if(p.getCategory().equals("skin care"))
				  num.setValue(SkincareAdapter.getProductQuantity(p));
			  else
				  num.setValue(ListViewAdapter.getProductQuantity(p));
	         alert.setView(view);
	         alert.show();
	}
		 @Override
			protected void onResume() {
				super.onResume();
				
				// Refresh the data
				if(mProductAdapter != null) {
					mProductAdapter.notifyDataSetChanged();
				}
			}	 
	}