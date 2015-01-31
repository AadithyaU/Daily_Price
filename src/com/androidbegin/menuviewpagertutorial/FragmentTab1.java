package com.androidbegin.menuviewpagertutorial;

import java.util.ArrayList;
import java.util.Locale;

import com.actionbarsherlock.app.SherlockFragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
 
public class FragmentTab1 extends SherlockFragment {
    String[] area;
    String[] address;
    String[] contactno;
	EditText editsearch;
    //int[] flag;
    ListView list;
    ListViewAdapterme adapter;
    ArrayList<WorldPopulationme> arraylist = new ArrayList<WorldPopulationme>();
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmenttab1, container,
                false);

        
        // Generate sample data
        area = new String[] { "Aadhambakam", "Adyar", "Ayanavaram", "Choolaimedu", "Gopalapuram" };
 
        address = new String[] { "New No.17, Old No.16A, Secretariat Colony Main Road, Chennai 600088", "New Door No. 17/1, Old Door No.6/1. Indira Nagar First Avenue,Beside SBH, Opp to ICICI Bank, Chennai 600020", "No.9/5, VP Colony South Street,Ayanavaram Chennai 600023",
                "Ground Floor Commercial Shop,Great Pearl Apartments Chennai 600094","No. 174, Choolaimedu High Road,", "No 17&19, Conran Smith Road,Opp to HDFC BANK,Gopalapuram,Chennai." };
 
       // contactno = new String[] { "1,354,040,000", "1,210,193,422",
         //       "315,761,000", "237,641,326", "193,946,886" };
 
        //flag = new int[] { R.drawable.china, R.drawable.india,
          //      R.drawable.unitedstates, R.drawable.indonesia,
            //    R.drawable.brazil };
 
        // Locate the ListView in fragmenttab1.xml
       // list = (ListView) rootView.findViewById(R.id.listview);
 
        // Pass results to ListViewAdapterme Class
        //adapter = new ListViewAdapterme(getActivity(), area, address);
        // Binds the Adapter to the ListView
       // list.setAdapter(adapter);
        // Capture clicks on ListView items
        /*list.setOnItemClickListener(new OnItemClickListener() {
 
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                    int position, long id) {
                // Send single item click data to SingleItemViewme Class
                Intent i = new Intent(getActivity(), SingleItemViewme.class);
                // Pass all data rank
                i.putExtra("area", area);
                // Pass all data country
                i.putExtra("address", address);
                // Pass all data population
                //i.putExtra("population", contactno);
                // Pass all data flag
                //i.putExtra("flag", flag);
                // Pass a single position
                i.putExtra("position", position);
                // Open SingleItemViewme.java Activity
                startActivity(i);
            }
 
        });*/
				
        list = (ListView) rootView.findViewById(R.id.listview);
        if(arraylist.isEmpty())
        {
        for (int i = 0; i < area.length; i++) 
        {
            WorldPopulationme wp = new WorldPopulationme (area[i], address[i]);
            // Binds all strings into an array
            arraylist.add(wp);
        }
        }
        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapterme(rootView.getContext(), arraylist);
 
        // Binds the Adapter to the ListView
        list.setAdapter(adapter);
         
        // Locate the EditText in listview_main.xml
        editsearch = (EditText) rootView.findViewById(R.id.Search1);
 
        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {
 
            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
               adapter.filter(text);
            }
 
            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                    int arg2, int arg3) {
                // TODO Auto-generated method stub
            }
 
            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                    int arg3) {
                // TODO Auto-generated method stub
            }
        });
        
		editsearch = (EditText) rootView.findViewById(R.id.Search1); 

		editsearch.addTextChangedListener(new TextWatcher() {

			@Override
			public void afterTextChanged(Editable arg0) {
			String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
				adapter.filter(text);
		    }

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			} 

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
			}
		});	 
		this.setHasOptionsMenu(true);
        return rootView;
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
};
