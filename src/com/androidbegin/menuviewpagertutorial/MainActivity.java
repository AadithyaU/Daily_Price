package com.androidbegin.menuviewpagertutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.PushService;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.SearchView.OnQueryTextListener;
import android.support.v4.view.GravityCompat;

public class MainActivity extends SherlockFragmentActivity {

	// Declare Variables
	DrawerLayout mDrawerLayout;
	ListView mDrawerList;
	ActionBarDrawerToggle mDrawerToggle;
	MenuListAdapter mMenuAdapter;
	String[] title;
	String[] subtitle;
	int[] icon;
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	//EditText editsearch;
	EditText inputsearch;
	private List<WorldPopulation> worldpopulationlist = null;
	private List<WorldPopulation> snackslist = null;
	private List<WorldPopulation> skincarelist = null;
	Fragment fragment1 = new Fragment1();
	Fragment fragment2 = new Fragment2();
	Fragment fragment3 = new Fragment3();
	Fragment fragment4 = new Fragment4();
	Fragment fragment5 = new Fragment5();
	Fragment fragment6 = new Fragment6();
	Fragment fragment8 = new Fragment8();
	Fragment fragment9 = new Fragment9();
	Fragment fragment10 = new Fragment10();
	Fragment fragment11 = new Fragment11();
	Fragment fragment12 = new Fragment12();
	Fragment fragment13 = new Fragment13();
	Fragment fragment14 = new Fragment14();
	Fragment fragment15 = new Fragment15();
	//Fragment fragment7 = new Fragment7();
	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private List<WorldPopulation> mCartList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from drawer_main.xml
		setContentView(R.layout.drawer_main);
		//the next 3 lines of code are for push notification
		ParseAnalytics.trackAppOpened(getIntent());
        // inform the Parse Cloud that it is ready for notifications
        PushService.setDefaultPushCallback(this, MainActivity.class);
        ParseInstallation.getCurrentInstallation().saveInBackground();
		//new RemoteDataTask().execute();
		 //to clear cart
/*		 mCartList = ShoppingCartHelper.getCartList();
		for(int i=0; i<mCartList.size(); i++) 
			ShoppingCartHelper.removeProduct(mCartList.get(i));
*/
		// Get the Title
		mTitle = mDrawerTitle = getTitle();

		// Generate title
		title = new String[] {"Home","Fruits and Vegetables","Snacks","Skin care","BreakFast Food","Detergents","Handwash and Shampoo","Soap","Drinks and Juices","Masalas and Spices","Salt,Sugar &Jaggery","Tea and Coffee","Sanitary Needs","Refrigerated"};
		// Generate subtitle
	    subtitle = new String[] { "Store Locations", "Fruits,Veggies,Cookable Cultivated Products","Chocolates and Savouries","Cosmetic Skin Products","Breads,Cornflakes and others","Washing Powders and Soaps","Skin and Hair Cleansing","Bathing Soaps","All kinds of Drinks","Best Spices and Powders","Quality Brands","Best Tea and coffee","Sanitary Products and Cleaning Utilities","Products to be stored in Cooler"};
	  //  "detergents and soaps","drinks and juices","masalas and spices","skin care,handwash and shampoos","chocolates and savouries"
	   // ,"Subtitle Fragment 3","Subtitle Fragment 4","Subtitle Fragment 5","Subtitle Fragment 6","Subtitle Fragment 7"
	    // Generate icon
		icon = new int[] { R.drawable.ic_home, R.drawable.ic_veg ,R.drawable.ic_snacks,R.drawable.ic_skin,R.drawable.ic_food,R.drawable.ic_shampoo,R.drawable.ic_handwash,R.drawable.ic_soap,R.drawable.ic_drinks,R.drawable.ic_masala,R.drawable.ic_salt,R.drawable.ic_tea,R.drawable.ic_action_sanitary,R.drawable.ic_fridge };

		// Locate DrawerLayout in drawer_main.xml
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		// Locate ListView in drawer_main.xml
		mDrawerList = (ListView) findViewById(R.id.listview_drawer);

		// Set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);

		// Pass string arrays to MenuListAdapter
		mMenuAdapter = new MenuListAdapter(MainActivity.this, title, subtitle,
				icon);

		// Set the MenuListAdapter to the ListView
		mDrawerList.setAdapter(mMenuAdapter);

		// Capture listview menu item click
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// Enable ActionBar app icon to behave as action to toggle nav drawer
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {

			public void onDrawerClosed(View view) {
				// TODO Auto-generated method stub
				super.onDrawerClosed(view);
			}

			public void onDrawerOpened(View drawerView) {
				// TODO Auto-generated method stub
				// Set the title on the action when drawer open
				getSupportActionBar().setTitle(mDrawerTitle);
				super.onDrawerOpened(drawerView);
			}
		};

		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	/*	if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }*/
	}
	 @Override
	   public boolean onCreateOptionsMenu(Menu menu) {
	      MenuInflater inflater = getSupportMenuInflater();
	      inflater.inflate(R.menu.activity_main, menu);
	      return super.onCreateOptionsMenu(menu);
	   }
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		if (item.getItemId() == android.R.id.home) {

			if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
				mDrawerLayout.closeDrawer(mDrawerList);
			} else {
				mDrawerLayout.openDrawer(mDrawerList);
			}
		}
		else if ( item.getItemId() == R.id.menu_cart) {
			Intent i6 = new Intent( this, ShoppingCartActivity.class);
			startActivity(i6);
		}
		else if ( item.getItemId() == R.id.menu_notes) {
			Intent i6 = new Intent( this, Tobuy.class);
			startActivity(i6);
		}
		return super.onOptionsItemSelected(item);
	}

	// ListView click listener in the navigation drawer
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
				if(position==0)
					selectItem(position);
				else
				{
				ConnectivityManager connMgr = (ConnectivityManager) 
			        getSystemService(Context.CONNECTIVITY_SERVICE);
			    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
			    if (networkInfo != null && networkInfo.isConnected()) {
					selectItem(position);
			    } else {
			    	Toast.makeText(getBaseContext(), "No Network Available", Toast.LENGTH_SHORT).show();
			    }
				}
		}
	}

	private void selectItem(int position) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		// Locate Position
		switch (position) {
		case 0:
			ft.replace(R.id.content_frame, fragment2);
			break;
		case 1:
			ft.replace(R.id.content_frame, fragment1);
			break;
	    case 2:
			ft.replace(R.id.content_frame, fragment4);
			break;
	    case 3:
			ft.replace(R.id.content_frame, fragment3);
			break;
		case 4:
			ft.replace(R.id.content_frame, fragment5);
			break;
		case 5:
			ft.replace(R.id.content_frame, fragment6);
			break;
		case 6:
			ft.replace(R.id.content_frame, fragment8);
			break;
		case 7:
			ft.replace(R.id.content_frame, fragment9);
			break;
		case 8:
			ft.replace(R.id.content_frame, fragment10);
			break;
		case 9:
			ft.replace(R.id.content_frame, fragment11);
			break;
		case 10:
			ft.replace(R.id.content_frame, fragment12);
			break;
		case 11:
			ft.replace(R.id.content_frame, fragment13);
			break;
		case 12:
			ft.replace(R.id.content_frame, fragment14);
			break;
		case 13:
			ft.replace(R.id.content_frame, fragment15);
			break;
	/*	case 14:
			ft.replace(R.id.content_frame, fragment7);
			break;
		
	case 15:
			ft.replace(R.id.content_frame, fragment7);
			break;
		*/
		}
		ft.commit();
		mDrawerList.setItemChecked(position, true);
		// Get the title followed by the position
		setTitle(title[position]);
		// Close drawer
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getSupportActionBar().setTitle(mTitle);
	}
	

	/*public void onBackPressed()
	{
		Intent intent = new Intent(MainActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
	}*/
	

	@Override
	public void onBackPressed() {

		FragmentManager manager = getSupportFragmentManager();
		if (manager.getBackStackEntryCount() > 0) {
			// If there are back-stack entries, leave the FragmentActivity
			// implementation take care of them.
			manager.popBackStack();

		} else {
			// Otherwise, ask user if he wants to leave :)
			super.onBackPressed();
		}
	}
	
}
