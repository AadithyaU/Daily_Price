package com.androidbegin.menuviewpagertutorial;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.widget.TextView;

public class ViewNote extends Activity {

	// Declare Variables
	private long rowID;
	private TextView TitleTv;
	private TextView NoteTv;
	private static final String TITLE = "title";
	private static final String NOTE = "note";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_note);

		// Locate the TextView in view_note.xml
		TitleTv = (TextView) findViewById(R.id.TitleText);
		NoteTv = (TextView) findViewById(R.id.NoteText);

		// Retrieve the ROW ID from MainActivity.java
		Bundle extras = getIntent().getExtras();
		rowID = extras.getLong(Tobuy.ROW_ID);
	}

	@Override
	protected void onResume() {
		super.onResume();

		// Execute LoadNotes() AsyncTask
		new LoadNotes().execute(rowID);
	}

	// LoadNotes() AsyncTask
	private class LoadNotes extends AsyncTask<Long, Object, Cursor> {
		// Calls DatabaseConnector.java class
		DatabaseConnector dbConnector = new DatabaseConnector(ViewNote.this);

		@Override
		protected Cursor doInBackground(Long... params) {
			// Pass the Row ID into GetOneNote function in
			// DatabaseConnector.java class
			dbConnector.open();
			return dbConnector.GetOneNote(params[0]);
		}

		@Override
		protected void onPostExecute(Cursor result) {
			super.onPostExecute(result);

			result.moveToFirst();
			// Retrieve the column index for each data item
			int TitleIndex = result.getColumnIndex(TITLE);
			int NoteIndex = result.getColumnIndex(NOTE);

			// Set the Text in TextView
			TitleTv.setText(result.getString(TitleIndex));
			NoteTv.setText(result.getString(NoteIndex));

			result.close();
			dbConnector.close();
		}
	}

	// Create an options menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Edit Note")
				.setOnMenuItemClickListener(this.EditButtonClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		menu.add("Delete Notes")
				.setOnMenuItemClickListener(this.DeleteButtonClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return super.onCreateOptionsMenu(menu);
	}

	// Capture edit menu item click
	OnMenuItemClickListener EditButtonClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Pass Row ID and data to AddEditNotes.java
			Intent addeditnotes = new Intent(ViewNote.this, AddEditNotes.class);

			addeditnotes.putExtra(Tobuy.ROW_ID, rowID);
			addeditnotes.putExtra(TITLE, TitleTv.getText());
			addeditnotes.putExtra(NOTE, NoteTv.getText());
			startActivity(addeditnotes);

			return false;

		}
	};

	// Capture delete menu item click
	OnMenuItemClickListener DeleteButtonClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Calls DeleteNote() Function
			DeleteNote();

			return false;

		}
	};

	private void DeleteNote() {

		// Display a simple alert dialog to reconfirm the deletion
		AlertDialog.Builder alert = new AlertDialog.Builder(ViewNote.this);
		alert.setTitle("Delete Item");
		alert.setMessage("Do you really want to delete this note?");

		alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int button) {
				final DatabaseConnector dbConnector = new DatabaseConnector(
						ViewNote.this);

				AsyncTask<Long, Object, Object> deleteTask = new AsyncTask<Long, Object, Object>() {
					@Override
					protected Object doInBackground(Long... params) {
						// Passes the Row ID to DeleteNote function in
						// DatabaseConnector.java
						dbConnector.DeleteNote(params[0]);
						return null;
					}

					@Override
					protected void onPostExecute(Object result) {
						// Close this activity
						finish();
					}
				};
				// Execute the deleteTask AsyncTask above
				deleteTask.execute(new Long[] { rowID });
			}
		});

		// Do nothing on No button click
		alert.setNegativeButton("No", null).show();
	}
}
