package com.androidbegin.menuviewpagertutorial;

import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.SearchView;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentTab2 extends SherlockFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Get the view from fragmenttab2.xml
		this.setHasOptionsMenu(true);
		View view = inflater.inflate(R.layout.fragmenttab2, container, false);
		

		return view;
	}
	@Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.frag1act1, menu);
        
	    MenuItem menucart = menu.findItem(R.id.menu_cart);

	    menucart.setVisible(true);
	    menucart.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem arg0) {
				Intent inten = new Intent (getActivity(), ShoppingCartActivity.class);
				startActivity(inten);
				return false;
			}
		});
    }
}
