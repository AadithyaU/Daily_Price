package com.androidbegin.menuviewpagertutorial;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.MenuItem.OnMenuItemClickListener;

public class AddEditNotes extends Activity {

	// Declare Variables
	private long rowID;
	private EditText title_edit;
	private EditText note_edit;
	private static final String TITLE = "title";
	private static final String NOTE = "note";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_note);

		// Locate the EditText in add_note.xml
		title_edit = (EditText) findViewById(R.id.titleEdit);
		note_edit = (EditText) findViewById(R.id.noteEdit);

		// Retrieve the Row ID from ViewNote.java
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			rowID = extras.getLong("row_id");
			title_edit.setText(extras.getString(TITLE));
			note_edit.setText(extras.getString(NOTE));
		}
	}

	// Create an ActionBar menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add("Save Note")
				.setOnMenuItemClickListener(this.SaveButtonClickListener)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

		return super.onCreateOptionsMenu(menu);
	}

	// Capture save menu item click
	OnMenuItemClickListener SaveButtonClickListener = new OnMenuItemClickListener() {
		public boolean onMenuItemClick(MenuItem item) {

			// Passes the data into saveNote() function
			if (title_edit.getText().length() != 0) {
				AsyncTask<Object, Object, Object> saveNoteAsyncTask = new AsyncTask<Object, Object, Object>() {
					@Override
					protected Object doInBackground(Object... params) {
						saveNote();
						return null;
					}

					@Override
					protected void onPostExecute(Object result) {
						// Close this activity
						finish();
					}
				};
				// Execute the saveNoteAsyncTask AsyncTask above
				saveNoteAsyncTask.execute((Object[]) null);
			}

			else {
				// Display a simple alert dialog that forces user to put in a title
				AlertDialog.Builder alert = new AlertDialog.Builder(
						AddEditNotes.this);
				alert.setTitle("Title is required");
				alert.setMessage("Put in a title for this note");
				alert.setPositiveButton("Okay", null);
				alert.show();
			}

			return false;

		}
	};
	// saveNote() function
	private void saveNote() {
		DatabaseConnector dbConnector = new DatabaseConnector(this);

		if (getIntent().getExtras() == null) {
			// Passes the data to InsertNote in DatabaseConnector.java
			dbConnector.InsertNote(title_edit.getText().toString(), note_edit
					.getText().toString());
		} else {
			// Passes the Row ID and data to UpdateNote in DatabaseConnector.java
			dbConnector.UpdateNote(rowID, title_edit.getText().toString(),
					note_edit.getText().toString());
		}
	}
}
