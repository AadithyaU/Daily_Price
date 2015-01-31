package com.androidbegin.menuviewpagertutorial;


import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Email extends Activity {
	private List<WorldPopulation> CartList = ShoppingCartHelper.getCartList();
	private ShoppingCartHelper quan;
	public static String country="\n";
	//public static String population="\nPopulation";
	//public static String rank="\nRank";
	public static ListView listViewCatalog;
	View focusview = null;
	boolean cancel = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.email_info);
		 if (android.os.Build.VERSION.SDK_INT > 9) {
			  StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			  StrictMode.setThreadPolicy(policy);
			  }
		 
	 CartList = ShoppingCartHelper.getCartList();
	 for(int i=0; i<CartList.size(); i++) {
			country = country+"\t\t"+CartList.get(i).getProduct();
			country = country+"\t\t"+CartList.get(i).getPrice();
			//country = country+"\t"+CartList.get(i).getRank();
			country = country+"\t\t"+quan.getProductQuantity(CartList.get(i));
			country=country+"\n";
		}		 
	/* for(int i=0; i<CartList.size(); i++) {
			population = population+"\t"+CartList.get(i).getPopulation();
		}		 
	 for(int i=0; i<CartList.size(); i++) {
			rank = rank+"\t"+CartList.get(i).getRank();
		}*/		 
	
	 final EditText fd1 = (EditText)findViewById(R.id.editText1);
	 final EditText fd2 = (EditText)findViewById(R.id.editText2);
	 final EditText fd3 = (EditText)findViewById(R.id.editText3);
	 final EditText fd4 = (EditText)findViewById(R.id.editText4);
	 final EditText fd5 = (EditText)findViewById(R.id.editText5);
	 final EditText fd6 = (EditText)findViewById(R.id.editText6);
	 //final ListView lw = (ListView)findViewById(R.id.ListViewCatalog);
	 /*
	 Button payment = (Button)findViewById(R.id.payment);
	 payment.setOnClickListener(new OnClickListener() {

	        public void onClick(View v) 
	        {	
	        	String t = "@";
	        	boolean t1 = fd4.toString().toLowerCase().contains("@");
	        	boolean t2 = fd4.toString().toLowerCase().endsWith(".com");
	        	

	        	if(fd1.getText().length() == 0) {
					fd1.setError("Invalid Name");
					focusview = fd1;
	        	}
	        	else if(fd2.getText().length() == 0) {
					fd2.setError("Invalid Area");
					
					focusview = fd1;
	        	}
	        	else if(fd3.getText().length() == 0) {
					fd3.setError("Invalid Address");
					focusview = fd1;
	        	}
	        	else if(fd5.getText().length() == 0) {
					fd5.setError("Invalid Mobile Number");
					focusview = fd1;
	        	}
	        	else if (fd5.getText().length() < 10 || fd5.getText().length() > 10) {
	        		fd5.setError("Invalid Mobile Number");
				focusview = fd1;
	        }
	        	else if (fd6.getText().length() > 8 || (fd6.getText().length() != 0 && fd6.getText().length() < 8 )) { 
	        		fd5.setError("Invalid Landline Number");
				focusview = fd1;
	        }
	        	else
	        	{	
	        	final ProgressDialog dialog = ProgressDialog.show(Email.this, "", "Placing order...", true);
	        	String field1 = fd1.getText().toString();
	            String field2 = fd2.getText().toString();
	            String field3 = fd3.getText().toString();
	            String field4 = fd4.getText().toString();
	            String field5 = fd5.getText().toString();
	            String field6 = fd6.getText().toString();
	            //String field7 = lw.getTextFilter().toString();
	        	
	            
	            
	            final GMailSender m = new GMailSender("bala94.n@gmail.com", "PrashantH"); 
	       	 
	  	      String[] toArr = {"bala94.n@gmail.com","anushya1995cool@gmail.com","aadi1194@gmail.com"}; 
	  	      m.setTo(toArr); 
	  	      m.setFrom("bala94.n@gmail.com"); 
	  	      m.setSubject("User Information"); 
	  	      m.setBody("Name: "+field1+"\nArea "+field2+"\nAddress: "+field3+"\nEmail id: "+field4+"\nMobile no: "+field5+"\nPhone no: "+field6+"\n\n\t\t"+"Product"+"\t\t"+"Price"+"\t\t"+"Qty"+"\n"+country); 
	        	
	  	    new AsyncTask<Void, Void, Void>()
	  	    {
	  	    	@Override
	  	    	 protected Void doInBackground(Void... params)
	  	        {
	  	    		 try { 
	  	    			 //m.addAttachment("/sdcard/filelocation"); 
	  	 
	  	    			 if(m.send()) { 
	  	    				runOnUiThread(new Runnable() {
	  	    					public void run() {
	  	    						 Toast.makeText(Email.this, "Your shopping is successful..!", Toast.LENGTH_LONG).show();
	  	    						 finish();
	  	    					}
	  	    					});
	  	    				 
	  	    			 } else { 
	  	    				runOnUiThread(new Runnable() {
	  	    					public void run() {
	  	    						 Toast.makeText(Email.this, "Shopping failed", Toast.LENGTH_LONG).show();
	  	    					    }
	  	    					});
	  	    			 } 
	  	    		 } catch(Exception e) { 
	  	    			runOnUiThread(new Runnable() {
  	    					public void run() {
  	    						 Toast.makeText(Email.this, "Sorry, a problem occured", Toast.LENGTH_LONG).show(); 
  	    					    }
  	    					});
  	    				 Log.e("MailApp", "Could not send email", e); 
	  	    		 } 
	  	    		
	  	    		 return null;
	  	        }
	  	    	@Override
	  	        protected void onPostExecute(Void result)
	  	        {
	  	            dialog.dismiss();
	  	  		  	Intent intent_name = new Intent();
  	            intent_name.setClass(getApplicationContext(),MainActivity.class);
  	            startActivity(intent_name);
  	       	
	  	            for(int i=0; i<CartList.size(); i++) {
		  	        	quan.removeProduct(CartList.get(i));  	
		  	        }
	  	        }
	  	    }.execute();
	  	    }
	  	    } 
	  	  }); 
	               */
    }
	
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.handwash_and__shampooact, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(android.view.MenuItem item) {
		
		if (item.getItemId() == R.id.confirm) {
			
			 final EditText fd1 = (EditText)findViewById(R.id.editText1);
			 final EditText fd2 = (EditText)findViewById(R.id.editText2);
			 final EditText fd3 = (EditText)findViewById(R.id.editText3);
			 final EditText fd4 = (EditText)findViewById(R.id.editText4);
			 final EditText fd5 = (EditText)findViewById(R.id.editText5);
			 final EditText fd6 = (EditText)findViewById(R.id.editText6);
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(fd1.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(fd2.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(fd3.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(fd4.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(fd5.getWindowToken(), 0);
				imm.hideSoftInputFromWindow(fd6.getWindowToken(), 0);
			String t = "@";
        	boolean t1 = fd4.toString().toLowerCase().contains("@");
        	boolean t2 = fd4.toString().toLowerCase().endsWith(".com");
        	
        	if(fd1.getText().length() == 0) {
				fd1.setError("Invalid Name");
				focusview = fd1;
        	}
        	else if(fd2.getText().length() == 0) {
				fd2.setError("Invalid Area");
				
				focusview = fd2;
        	}
        	else if(fd3.getText().length() == 0) {
				fd3.setError("Invalid Address");
				focusview = fd3;
        	}
        	else if(fd4.getText().length() != 0 && (!(fd4.getText().toString().endsWith(".com")) || !(fd4.getText().toString().contains("@")))) {
				fd4.setError("Invalid Email");
				focusview = fd4;
        	}
        	else if (fd5.getText().length() < 10 || fd5.getText().length() > 10) {
        		fd5.setError("Invalid Mobile Number");
			focusview = fd5;
        }
        	else if (fd6.getText().length() > 8 || (fd6.getText().length() != 0 && fd6.getText().length() < 8 )) { 
        		fd5.setError("Invalid Landline Number");
			focusview = fd6;
        }
        	else
        	{	
        		double subTotal = 0;
	    		  for(WorldPopulation np : CartList) {
	    			  int quantity;
					quantity = ShoppingCartHelper.getProductQuantity(np);
	    		   int i1 = Integer.parseInt(np.getPrice());
	    		   subTotal += quantity * i1;
	    		  } 
        	String s = "\nTotal items in Cart =  " + CartList.size() + "\n\n" + "Total Amount = " + subTotal + "\n";
        	final AlertDialog.Builder alert = new AlertDialog.Builder(this);	
        	TextView myMsg = new TextView(this);
        	myMsg.setText(s);
        	myMsg.setTextColor(0xffffffff);
        	myMsg.setGravity(Gravity.CENTER_HORIZONTAL);
        	String d = "\nYour Shopping is successful\n\n" + "\nTotal items in Cart =  " + CartList.size() + "\n\n" + "Total Amount = " + subTotal + "\n" ;
        	final TextView myMsg1 = new TextView(this);
	        	myMsg1.setText(d);
	        	myMsg1.setTextColor(0xffffffff);
	        	myMsg1.setGravity(Gravity.CENTER_HORIZONTAL);
					final AlertDialog.Builder alert1 = new AlertDialog.Builder(this);	
        	alert.setTitle("Cart Details")
        	.setView(myMsg)
        	.setCancelable(false)
        	.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					}
				})
        	.setPositiveButton("Place Order", new DialogInterface.OnClickListener()  {
				
				@Override
				
				public void onClick(DialogInterface dialog, int which) {
					final ProgressDialog dialog1 = ProgressDialog.show(Email.this, "", "Placing order...", true);
					//dialog.dismiss();
					String field1 = fd1.getText().toString();
		            String field2 = fd2.getText().toString();
		            String field3 = fd3.getText().toString();
		            String field4 = fd4.getText().toString();
		            String field5 = fd5.getText().toString();
		            String field6 = fd6.getText().toString();
		            //String field7 = lw.getTextFilter().toString();
		            final GMailSender m = new GMailSender("bala94.n@gmail.com", "PrashantH"); 
		       	 
		  	      String[] toArr = {"bala94.n@gmail.com","anushya1995cool@gmail.com","aadi1194@gmail.com"}; 
		  	      m.setTo(toArr); 
		  	      m.setFrom("bala94.n@gmail.com"); 
		  	      m.setSubject("User Information"); 
		  	      m.setBody("Name: "+field1+"\nArea "+field2+"\nAddress: "+field3+"\nEmail id: "+field4+"\nMobile no: "+field5+"\nPhone no: "+field6+"\n\n\t"+"Product"+"\t"+"Price"+"\t"+"Qty"+"\n"+country); 
		        	
		  	    new AsyncTask<Void, Void, Void>()
		  	    {
		  	    	@Override
		  	    	 protected Void doInBackground(Void... params)
		  	        {
		  	    		 try { 
		  	    			 //m.addAttachment("/sdcard/filelocation"); 
		  	 
		  	    			 if(m.send()) { 
		  	    				runOnUiThread(new Runnable() {
		  	    					public void run() {
		  	    						 //Toast.makeText(Email.this, "Your shopping is successful..!", Toast.LENGTH_LONG).show();
		  	    						alert1.setTitle("Shopping Details")
		  	    			        	.setView(myMsg1)
		  	    			        	.setCancelable(false)
		  	    	       	        	.setNegativeButton("Continue Shopping",new DialogInterface.OnClickListener() {
		  	    	       	        			public void onClick(DialogInterface dialog,int id) {
		  	    	       	        				dialog.cancel();
		  	    	       	        				finish();
		  	    	       	        				Intent i = new Intent(Email.this,MainActivity.class);
		  	    	       	        				startActivity(i);
		  	    	       	        			}
		  	    	       	        			})
		  	    	       	        		.setNeutralButton("Exit",new DialogInterface.OnClickListener() {
		  	    	       	        		public void onClick(DialogInterface dialog,int id) {
		  	    	       	        			dialog.dismiss();
		  	    	       	        			Intent intent = new Intent(Intent.ACTION_MAIN);
		  	    	       	        			intent.addCategory(Intent.CATEGORY_HOME);
		  	    	       	        			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		  	    	       	        			startActivity(intent);
		  	    	       	        		}
		  	    	       	       			}).show(); 
		  	    						finish();
		  	    					}
		  	    					});
		  	    				 
		  	    			 } else { 
		  	    				runOnUiThread(new Runnable() {
		  	    					public void run() {
		  	    						 Toast.makeText(Email.this, "Shopping failed", Toast.LENGTH_LONG).show();
		  	    					    }
		  	    					});
		  	    			 } 
		  	    		 } catch(Exception e) { 
		  	    			runOnUiThread(new Runnable() {
	  	    					public void run() {
	  	    						 Toast.makeText(Email.this, "Sorry, a problem occured", Toast.LENGTH_LONG).show(); 
	  	    						alert1.setTitle("Shopping Details")
	  	    			        	.setView(myMsg1)
	  	    	       	        	.setCancelable(false)
	  	    	       	        	.setNegativeButton("Conitnue Shopping",new DialogInterface.OnClickListener() {
	  	    	       	        			public void onClick(DialogInterface dialog,int id) {
	  	    	       	        				dialog.cancel();
	  	    	       	        				finish();
	  	    	       	        				Intent i = new Intent(Email.this,MainActivity.class);
	  	    	       	        				startActivity(i);
	  	    	       	        			}
	  	    	       	        			})
	  	    	       	        		.setNeutralButton("Exit",new DialogInterface.OnClickListener() {
	  	    	       	        		public void onClick(DialogInterface dialog,int id) {
	  	    	       	        			dialog.dismiss();
	  	    	       	        			Intent intent = new Intent(Intent.ACTION_MAIN);
	  	    	       	        			intent.addCategory(Intent.CATEGORY_HOME);
	  	    	       	        			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	  	    	       	        			finish();
	  	    	       	        			startActivity(intent);
	  	    	       	        		}
	  	    	       	       			}).show();
	  	    					}
	  	    					});
	  	    				 Log.e("MailApp", "Could not send email", e); 
		  	    		 } 
		  	    		
		  	    		 return null;
		  	        }
		  	    	@Override
		  	        protected void onPostExecute(Void result)
		  	        {
		  	            dialog1.dismiss();
		  	  	/*	  	Intent intent_name = new Intent();
	  	            intent_name.setClass(getApplicationContext(),MainActivity.class);
	  	            startActivity(intent_name);*/
	  	       	
		  	            for(int i=0; i<CartList.size(); i++) {
			  	        	quan.removeProduct(CartList.get(i));  	
			  	        }
		  	        }
		  	    }.execute();
		  	    dialog.dismiss();
					
				}
			}).show();
		}

}
		return super.onOptionsItemSelected(item);

	}
	    
}
