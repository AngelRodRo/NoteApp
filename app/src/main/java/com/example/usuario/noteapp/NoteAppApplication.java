package com.example.usuario.noteapp;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;

import org.json.JSONObject;

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
        ParseQuery<ParseObject> query = ParseQuery.getQuery("TestObject");

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo","bar");
        testObject.saveInBackground();

        JSONObject data = new JSONObject();
        try {
            data.put("nombre","nadsas");
            data.put("information","kdakadsk");
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Hola error", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(getApplicationContext(),data.toString(), Toast.LENGTH_SHORT).show();
        ParsePush push = new ParsePush();
        push.setChannel("");
        push.setData(data);
        push.sendInBackground();
    }
}
