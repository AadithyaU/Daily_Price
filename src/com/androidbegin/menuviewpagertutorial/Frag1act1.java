package com.androidbegin.menuviewpagertutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnActionExpandListener;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Frag1act1 extends Activity {
	// Declare Variables
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	//EditText editsearch;
	EditText inputsearch;
	private static List<WorldPopulation> worldpopulationlist = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_main);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setTitle("Fruits and Vegetables");
		new RemoteDataTask().execute();
	/*	
		Button viewShoppingCart = (Button) findViewById(R.id.ButtonViewCart);
		viewShoppingCart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent viewShoppingCartIntent = new Intent(getBaseContext(), ShoppingCartActivity.class);
				startActivity(viewShoppingCartIntent);
				viewShoppingCartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

			}
		});  
		   
		*/    
	}
	@Override
    protected void onSaveInstanceState(Bundle outState) {
        //outState.putParcelableArrayList("key", worldpopulationlist);
        super.onSaveInstanceState(outState);
    }
	
	// RemoteDataTask AsyncTask
	public class RemoteDataTask extends AsyncTask<Void, Void, Void> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Create a progressdialog
			mProgressDialog = new ProgressDialog(Frag1act1.this);
			// Set progressdialog title
			mProgressDialog.setTitle("Heritage Fresh");
			// Set progressdialog message
			mProgressDialog.setMessage("Getting Data...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		
		} 
		
		
		@Override
		protected Void doInBackground(Void... params) {
		// Create the array
			worldpopulationlist = new ArrayList<WorldPopulation>();
			try {
				ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
						"Product");
				//query.orderByAscending("Product");
				ob = query.find();
				for (ParseObject Product : ob) {
					// Locate images in Picture column
					ParseFile image = (ParseFile) Product.get("Picture");
					
					WorldPopulation map = new WorldPopulation();
					if ( Product.get("Category").equals("fruits and vegetables")) {
					map.setPrice((String) Product.get("Price"));
					map.setProduct((String) Product.get("Product"));
					map.setCategory((String) Product.get("Category"));
					map.setPicture(image.getUrl());
					worldpopulationlist.add(map);
					}
				}
			} catch (ParseException e) {
				Log.e("Error", e.getMessage());
				e.printStackTrace();
			} 
			return null;
		} 

		@Override
		protected void onPostExecute(Void result) {
			// Locate the listview in listview_main.xml
			 
			listview = (ListView) findViewById(R.id.listview);
			// Pass the results into ListViewAdapter.java

				adapter = new ListViewAdapter(Frag1act1.this,worldpopulationlist);
				listview.setAdapter(adapter);

			mProgressDialog.dismiss();
		}
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.frag1act1, menu);
	    MenuItem menucart = menu.findItem(R.id.menu_cart);
	    menucart.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				Intent inten = new Intent (getBaseContext() , ShoppingCartActivity.class);
				startActivity(inten);
				return false;
			}
		});
/*
		MenuItem menuSettings = menu.findItem(R.id.menu_settings);
		menuSettings.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				// TODO Auto-generated method stub
				// Do something here
				Toast.makeText(getApplicationContext(), "Nothing here!",
						Toast.LENGTH_LONG).show();
				return false;
			}
		});  
*/
		return true;
	}
}