package com.androidbegin.menuviewpagertutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.PopupMenu.OnMenuItemClickListener;
 
public class ListViewAdapterme extends BaseAdapter {
 
    // Declare Variables
    final Context mcontext;
    String[] area;
    String[] address;
    //String[] contactno;
    //int[] flag;
    LayoutInflater inflater;
    private List<WorldPopulationme> worldpopulationlist = null;
    //private ArrayList<WorldPopulationme> arraylist;
    ArrayList<WorldPopulationme> arraylist = new ArrayList<WorldPopulationme>();
 
    public ListViewAdapterme(Context context, String[] area, String[] address) {
           // String[] population, int[] flag) {
        this.mcontext = context;
        this.area = area;
        this.address = address;
        //this.contactno = contactno;
        //this.flag = flag;
        
		//this.worldpopulationlist = worldpopulationlist;
		//inflater = LayoutInflater.from(mContext);
		//this.arraylist = new ArrayList<WorldPopulation>();
		//this.arraylist.addAll(worldpopulationlist);
    }
    

    public ListViewAdapterme (Context context, List<WorldPopulationme> worldpopulationlist) {
        mcontext = context;
        this.worldpopulationlist = worldpopulationlist;
        inflater = LayoutInflater.from(mcontext);
        this.arraylist = new ArrayList<WorldPopulationme>();
        this.arraylist.addAll(worldpopulationlist);
    }
 
    public class ViewHolder {
		TextView area;
		TextView address;
	}
 
    public int getCount() {
        //return area.length;
    	return worldpopulationlist.size();
    }
    public WorldPopulationme getItem(int position) {
        return null;
    }
 
    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_itemme, null);
            holder.area = (TextView) view.findViewById(R.id.area);
            holder.address = (TextView) view.findViewById(R.id.address);
            view.setTag(holder);
            
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.area.setText(worldpopulationlist.get(position).getArea());
        holder.address.setText(worldpopulationlist.get(position).getAddress());
        
        final ImageButton b = (ImageButton) view.findViewById(R.id.overflow123);  
        b.setOnClickListener(new OnClickListener() {  
         
         @Override  
         public void onClick(View v) {  

          PopupMenu popup = new PopupMenu(mcontext,b);  
          popup.getMenuInflater().inflate(R.menu.detergentsact, popup.getMenu());  
          popup.setOnMenuItemClickListener(new OnMenuItemClickListener() {    
     		 public boolean onMenuItemClick(android.view.MenuItem item) {  
     			Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=" + worldpopulationlist.get(position).getAddress()));
     			mcontext.startActivity(geoIntent);
     			return true;  
     		 	}  
          });  
          popup.show();
         }  
        }); 
 /*
        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {
 
            @Override
            public void onClick(View arg0) {
                // Send single item click data to SingleItemView Class
                Intent intent = new Intent(mcontext, SingleItemViewme.class);
                // Pass all data rank
                intent.putExtra("area",(worldpopulationlist.get(position).getArea()));
                // Pass all data country
                intent.putExtra("address",(worldpopulationlist.get(position).getAddress()));
                mcontext.startActivity(intent);
            }
        });
 */
        return view;
    }
 

    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        worldpopulationlist.clear();
        if (charText.length() == 0) {
            worldpopulationlist.addAll(arraylist);
        } 
        else
        {
            for (WorldPopulationme wp : arraylist) 
            {
                if (wp.getArea().toLowerCase(Locale.getDefault()).contains(charText)) 
                {
                    worldpopulationlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }
    }
