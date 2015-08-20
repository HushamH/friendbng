package com.bhd.friendbang;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseCrashReporting;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by sdhond on 2015-07-08.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Crash Reporting.
        ParseCrashReporting.enable(this);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        // Add your initialization code here
        Parse.initialize(this, "KPmiNEQczB5mxOyCb8KZJ4zgIHGrRiVdY71WbnOd", "ucxRJ1LLX0E5O7OE9fcwEuGqL53FjJ5a3IwuQhX9");
        ParseUser.enableRevocableSessionInBackground();

        ParseACL defaultACL = new ParseACL();
        // Optionally enable public read access.
        // defaultACL.setPublicReadAccess(true);
        ParseACL.setDefaultACL(defaultACL, true);

        ParseUser.registerSubclass(User.class);
        ParseObject.registerSubclass(Group.class);
    }
}
