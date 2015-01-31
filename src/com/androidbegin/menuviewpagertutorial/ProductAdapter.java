package com.androidbegin.menuviewpagertutorial;

import java.util.List;

import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;

public class ProductAdapter extends BaseAdapter {
	
	private List<WorldPopulation> wp;
	private LayoutInflater mInflater;
	private boolean mShowCheckbox;
	private boolean mShowQuantity;
	private boolean mShowSubTotal;
	
	public ProductAdapter(List<WorldPopulation> list, LayoutInflater inflater, boolean showCheckbox, boolean showQuantity , boolean showSubTotal) {
		wp = list;
		mInflater = inflater;
		mShowCheckbox = showCheckbox;
		mShowQuantity = showQuantity;
		mShowSubTotal = showSubTotal;
	}

	@Override
	public int getCount() {
		return wp.size();
	}

	@Override
	public Object getItem(int position) {
		return wp.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewItem item;
		
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item,
					null);
			item = new ViewItem();
			//item.productImageView = (ImageView) convertView.findViewById(R.id.ImageViewItem);
			item.productTitle = (TextView) convertView.findViewById(R.id.TextViewItem);
	 		item.productQuantity = (TextView) convertView.findViewById(R.id.textViewQuantity);
			//item.productCheckbox = (CheckBox) convertView.findViewById(R.id.CheckBoxSelected);
			item.productSubTotal = (TextView) convertView.findViewById(R.id.SubTotal);
			
			convertView.setTag(item);
		} else {
			item = (ViewItem) convertView.getTag();
		}
		final WorldPopulation curProduct = wp.get(position);
		int temp = Integer.parseInt(curProduct.getPrice());
		/*Button buttonchange = (Button) convertView.findViewById(R.id.changeQuantity);
		buttonchange.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//show(curProduct);
			}
		});
		
		*/
		/*CheckBox c = (CheckBox) convertView.findViewById(R.id.CheckBoxSelected);
		c.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if ( isChecked ) 
					curProduct.selected = true;
				else
					curProduct.selected = false;			
			}
		}); */
				//item.productImageView.setImageDrawable(curProduct.productImage);
		item.productTitle.setText(curProduct.getProduct());
		if (mShowQuantity) {
			item.productQuantity.setText("Quantity:   "+ ShoppingCartHelper.getProductQuantity(curProduct));
		} else {
			item.productQuantity.setVisibility(View.GONE);
		}
		if(!mShowCheckbox) {
			item.productCheckbox.setVisibility(View.GONE);
		} else {
			if(curProduct.selected == true);
				//item.productCheckbox.setChecked(true);
			//else
				//item.productCheckbox.setChecked(false);
		}
		if (mShowSubTotal) {
			item.productSubTotal.setText("SubTotal:  ₹ "+ (temp) * (ShoppingCartHelper.getProductQuantity(curProduct)));
		} else {
			item.productSubTotal.setVisibility(View.GONE);
		}
		
		return convertView;
	}
	/*
	 public void show(WorldPopulation pro)
	    {

	         final Dialog d = new Dialog();
	         d.setTitle("Quantity Picker");
	         d.setContentView(R.layout.dialog);
	         Button b1 = (Button) d.findViewById(R.id.button1);
	         Button b2 = (Button) d.findViewById(R.id.button2);
	         final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
	         
	         np.setMaxValue(100); // max value 100
	         np.setMinValue(0);   // min value 0
	         np.setWrapSelectorWheel(true);
	         //np.setOnValueChangedListener(this);
	         b1.setOnClickListener(new OnClickListener()
	         {
	          @Override
	          public void onClick(View v) {
	        	  int qua = np.getValue();
	        	  ListViewAdapter.setQuantity(pro, qua);
	        	  ShoppingCartHelper.setQuantity(pro, qua);
	              d.dismiss();
	           }    
	          });
	         b2.setOnClickListener(new OnClickListener()
	         {
	          @Override
	          public void onClick(View v) {
	              d.dismiss(); 
	           }    
	          });
	       d.show();
*/

	    
	
	
	private class ViewItem {
		//ImageView productImageView;
		TextView productTitle;
		CheckBox productCheckbox;
		TextView productQuantity;
		TextView productSubTotal;
	}
	

}
