package com.example.usuario.noteapp;

import android.app.ListActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.ParseQuery;
import com.parse.PushService;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ListActivity {

    String APPLICATION_ID = "0yItYhlK5WbjHvGlS4GTsHeQji977iz7dlwWh6P4";
    String CLIENT_KEY = "NVBcd4b2xzGBSqo1gi8tEJd8lMxltQckDLhRXiwf";
    private List<Note> posts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Parse.initialize(this, APPLICATION_ID, CLIENT_KEY);

        //ParsePush.subscribeInBackground("prueba");
        posts = new ArrayList<Note>();
        ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this,R.layout.list_item_layout,posts);
        setListAdapter(adapter);

        refreshPostList();

    }

    private void refreshPostList(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Post");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> postList, ParseException e) {

                if (e==null)
                {
                    posts.clear();
                    for (ParseObject post: postList){
                        Note note = new Note(post.getObjectId(),post.getString("title"),post.getString("content"));
                        posts.add(note);
                    }
                    ((ArrayAdapter<Note>) getListAdapter()).notifyDataSetChanged();

                    }else{
                        Log.d(getClass().getSimpleName(), "Error : " + e.getMessage());
                    }
                }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
