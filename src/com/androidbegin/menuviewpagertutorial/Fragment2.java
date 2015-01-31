package com.androidbegin.menuviewpagertutorial;

import java.lang.reflect.Field;
import java.util.Locale;

import com.actionbarsherlock.app.SherlockFragment;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.SearchView;
 
public class Fragment2 extends SherlockFragment {
    // Declare Variable
    private FragmentTabHost mTabHost;
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Create FragmentTabHost
        mTabHost = new FragmentTabHost(getActivity());
        //this.setHasOptionsMenu(true);
        // Locate fragment1.xml to create FragmentTabHost
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.layout.viewpager_main);
        //mTabHost.setup(getLocalActivityManager ActivityGroup);
        // Create Tab 1
        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("ABOUT"), FragmentTab2.class, null);
        // Create Tab 2
        //Intent intent= new Intent(getActivity(),FragmentTab1.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("LOCATIONS").setContent(intent));
        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("LOCATIONS"), FragmentTab1.class, null);
        

		this.setHasOptionsMenu(true);

        return mTabHost;
    }
 
    // Detach FragmentTabHost
    @Override
    public void onDetach() {
        super.onDetach();
 
        try {
            Field childFragmentManager = Fragment.class
                    .getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
 
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    // Remove FragmentTabHost 
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
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
				Intent inten = new Intent (getActivity() , ShoppingCartActivity.class);
				startActivity(inten);
				return false;
			}
		});

    }
}