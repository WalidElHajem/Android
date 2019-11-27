package com.example.dell.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dell.test.LinearLayoutManagerWithSmoothScroller;
import com.example.dell.test.Utils;
import com.example.dell.test.R;
import com.example.dell.test.adapters.RVAdapter;
import com.example.dell.test.models.Movie;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RVAdapter adapter;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManagerWithSmoothScroller(getApplicationContext()));
        recyclerView.smoothScrollToPosition(0);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeAdapter();
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
        if (id == R.id.action_remove) {
            adapter.removeItem(0);
        } else if (id == R.id.action_add) {
            adapter.addItem(new Movie("Test", "Test", "http://images.amcnetworks.com/amc.com/wp-content/uploads/2012/06/BB_S5B_004_L.jpg"));

        }

        return super.onOptionsItemSelected(item);
    }


    private void initializeAdapter() {
        try {
            adapter = new RVAdapter(Utils.LoadMoviesFromJson(getApplicationContext()));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        recyclerView.setAdapter(adapter);
    }
}
