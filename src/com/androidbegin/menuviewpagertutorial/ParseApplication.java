package com.androidbegin.menuviewpagertutorial;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
        Parse.initialize(this, "N8VKx1ooDtfNzpNu7yDfUFPwmmzi26jnscJnM0p7", "FaizT5bPXylmTjGtUZ6fOGFjn3x9WQx30sihdF6T");

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}

}
