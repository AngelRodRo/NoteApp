package com.example.usuario.noteapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.parse.Parse;
import com.parse.ParseObject;

/**
 * Created by Usuario on 22/12/2014.
 */
public class NoteAppApplication extends Activity {
    String APPLICATION_ID = "0yItYhlK5WbjHvGlS4GTsHeQji977iz7dlwWh6P4";
    String CLIENT_KEY = "NVBcd4b2xzGBSqo1gi8tEJd8lMxltQckDLhRXiwf";
    @Override
    public void onCreate(Bundle SavedInstanceState) {

        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_main);
        Parse.initialize(this,APPLICATION_ID,CLIENT_KEY);

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo","bar");
        testObject.saveInBackground();

    }
}
