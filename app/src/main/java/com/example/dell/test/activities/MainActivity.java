package com.example.dell.test.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dell.test.models.Movies;
import com.example.dell.test.R;
import com.example.dell.test.adapters.RVAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Movies> movies;
    private RecyclerView rv;
    String name;
    String text;
    String picture;
    RVAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this)
        {

            @Override
            public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {
                LinearSmoothScroller smoothScroller = new LinearSmoothScroller(getApplicationContext()) {

                    private static final float SPEED = 300f;// Change this value (default=25f)

                    @Override
                    protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                        return SPEED / displayMetrics.densityDpi;
                    }

                };
                smoothScroller.setTargetPosition(position);
                startSmoothScroll(smoothScroller);
            }

        };
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        try {

            JSONObject obj = new JSONObject(loadJSONFromAsset());
            JSONArray m_jArry = obj.getJSONArray("movies");
            movies = new ArrayList<>();

            for (int i = 0; i < m_jArry.length(); i++) {

                JSONObject jo_inside = m_jArry.getJSONObject(i);
                Log.d("img::", jo_inside.getString("img"));
                name= jo_inside.getString("name");
                text = jo_inside.getString("text");
                 picture= jo_inside.getString("img");

                movies.add(i,new Movies(name,text,picture));




            }


        } catch (JSONException e) {
            e.printStackTrace();

        }
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
            int position = 0;


            if(movies.size()== 0)
            {
                Toast.makeText(this,"Empty",Toast.LENGTH_LONG).show();
            }else {
                movies.remove(position);
                adapter.notifyItemRemoved(position);


            }

        }else if (id == R.id.action_add) {
            int position ;
            picture = "https://ds1.static.rtbf.be/article/image/370x208/2/c/9/fffc714065fb5bfdd158072d71dc7206-1498196050.jpg";

            if(movies.size()== 0)
            {
                position = 0;
                movies.add(position, new Movies("TEST", "TEST", picture));
                adapter.notifyItemInserted(position);



            }else {
                position = 1;
              movies.add(position, new Movies("TEST", "TEST", picture));
                adapter.notifyItemInserted(position);


            }



        }

        return super.onOptionsItemSelected(item);
    }


    private void initializeAdapter(){
       adapter = new RVAdapter(movies);

        rv.setAdapter(adapter);
    }


    public String loadJSONFromAsset() {
        String json;
        try {
            InputStream is = this.getAssets().open("movies.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }




}
