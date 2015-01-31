package com.androidbegin.menuviewpagertutorial;


import java.util.Locale;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.MenuInflater;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.MenuItem.OnMenuItemClickListener;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

public class Fragment7 extends SherlockListFragment {
	
	public static final String ROW_ID = "row_id";
	private static final String TITLE = "title";
	private ListView noteListView;
	private CursorAdapter noteAdapter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.list_note, container, false);
		 final Intent i  = new Intent(getActivity(),Tobuy.class);
/*	        new Handler().postDelayed(new Runnable(){
	            @Override
	            public void run() {
	                startActivity(i);
	                getActivity().finish();
	            }
	        }, -1000000000); */
	/*	 noteListView.setOnItemClickListener(new OnItemClickListener() {
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {

					// Open ViewNote activity
					Intent viewnote = new Intent(getActivity(), ViewNote.class);

					// Pass the ROW_ID to ViewNote activity
					viewnote.putExtra(ROW_ID, arg3);
					startActivity(viewnote);
				}});
*/
			// Map all the titles into the ViewTitleNotes TextView
			String[] from = new String[] { TITLE };
			int[] to = new int[] { R.id.ViewTitleNotes };

			// Create a SimpleCursorAdapter
			noteAdapter = new SimpleCursorAdapter(getActivity(),
				R.layout.list_note, null, from, to);

			// Set the Adapter into SimpleCursorAdapter
			//noteAdapter = new CustomCursorAdapter(getActivity(), null, 0);
	        
			setListAdapter(noteAdapter);
			setHasOptionsMenu(true);
		return rootView;
	}
	
	OnItemClickListener viewNoteListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			// Open ViewNote activity
			Intent viewnote = new Intent(getActivity(), ViewNote.class);

			// Pass the ROW_ID to ViewNote activity
			viewnote.putExtra(ROW_ID, arg3);
			startActivity(viewnote);
		}
	};
	@Override
	public void onResume() {
		super.onResume();

		// Execute GetNotes Asynctask on return to MainActivity
		new GetNotes().execute((Object[]) null);
	}

	@Override
	public void onStop() {
		Cursor cursor = noteAdapter.getCursor();

		// Deactivates the Cursor
		if (cursor != null)
			cursor.deactivate();

		noteAdapter.changeCursor(null);
		super.onStop();
	}
/*
	@Override
	protected void onResume() {
		super.onResume();

		// Execute GetNotes Asynctask on return to MainActivity
		new GetNotes().execute((Object[]) null);
	}

	@Override
	protected void onStop() {
		Cursor cursor = noteAdapter.getCursor();

		// Deactivates the Cursor
		if (cursor != null)
			cursor.deactivate();

		noteAdapter.changeCursor(null);
		super.onStop();
	}
	*/


	// Capture menu item click
	OnMenuItemClickListener AddNewNoteClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Open AddEditNotes activity
			Intent addnote = new Intent(getActivity(), AddEditNotes.class);
			startActivity(addnote);

			return false;

		}
	};

	// GetNotes AsyncTask
	private class GetNotes extends AsyncTask<Object, Object, Cursor> {
		DatabaseConnector dbConnector = new DatabaseConnector(getActivity());

		@Override
		protected Cursor doInBackground(Object... params) {
			// Open the database
			dbConnector.open();

			return dbConnector.ListAllNotes();
		}

		@Override
		protected void onPostExecute(Cursor result) {
			noteAdapter.changeCursor(result);

			// Close Database
			dbConnector.close();
		}
	}

}

