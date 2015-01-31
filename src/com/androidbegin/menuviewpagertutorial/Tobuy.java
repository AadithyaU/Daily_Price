package com.androidbegin.menuviewpagertutorial;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Tobuy extends ListActivity {

	// Declare Variables
	public static final String ROW_ID = "row_id";
	private static final String TITLE = "title";
	private ListView noteListView;
	private CursorAdapter noteAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Locate ListView
		noteListView = getListView();
		getActionBar().setTitle("Notes");
		// Prepare ListView Item Click Listener
		noteListView.setOnItemClickListener(viewNoteListener);

		// Map all the titles into the ViewTitleNotes TextView
		String[] from = new String[] { TITLE };
		int[] to = new int[] { R.id.ViewTitleNotes };

		// Create a SimpleCursorAdapter
		noteAdapter = new SimpleCursorAdapter(Tobuy.this,
				R.layout.list_note, null, from, to);

		// Set the Adapter into SimpleCursorAdapter
		
		setListAdapter(noteAdapter);
	}

	// Capture ListView item click
	OnItemClickListener viewNoteListener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {

			// Open ViewNote activity
			Intent viewnote = new Intent(Tobuy.this, ViewNote.class);

			// Pass the ROW_ID to ViewNote activity
			viewnote.putExtra(ROW_ID, arg3);
			startActivity(viewnote);
		}
	};

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

	// Create an options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Menu Title
		menu.add("Add New Notes")
				.setOnMenuItemClickListener(this.AddNewNoteClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return super.onCreateOptionsMenu(menu);
	}

	// Capture menu item click
	OnMenuItemClickListener AddNewNoteClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Open AddEditNotes activity
			Intent addnote = new Intent(Tobuy.this, AddEditNotes.class);
			startActivity(addnote);

			return false;

		}
	};

	// GetNotes AsyncTask
	private class GetNotes extends AsyncTask<Object, Object, Cursor> {
		DatabaseConnector dbConnector = new DatabaseConnector(Tobuy.this);

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
	
	@Override
	public void onBackPressed() {
		Intent i3 = new Intent (getBaseContext() , MainActivity.class);
		startActivity(i3);
	}
}
