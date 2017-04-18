package com.example.sheke.homecinema;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleAdapter.ListItemClickListener {
    public ProgressBar mBar;
    public List<Movie> made;
    public GridLayoutManager tess;
    public RecycleAdapter mAdapter;
    public RecyclerView nann;
    public static final int ITEMS_NUM = 100;
    public Context context;
    String sort = "popular";
    TextView bask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        mBar = (ProgressBar) findViewById(R.id.pb);
        bask = (TextView)findViewById(R.id.error);

        if (isInternetOn(getBaseContext())) {
            getMovieDetails();
        } else {
            bask.setVisibility(View.VISIBLE);
        }
    }

    private void getMovieDetails() {
        URL apiURL = QueryUtils.buildUrl(sort,this);
        new ConnectionCall().execute(apiURL);
    }

    public static boolean isInternetOn(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();

    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, MovieDetails.class);
        intent.putExtra("movieDetails", made.get(clickedItemIndex));
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
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


    class ConnectionCall extends AsyncTask<URL, Void, List<Movie>> {

        @Override
        public void onPreExecute() {
            mBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(URL... params) {

            String r = "";
            try {
                r = QueryUtils.getResponseFromHttpUrl(params[0]);


            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                return QueryUtils.sonng(r);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(List<Movie> makie) {
            nann = (RecyclerView) findViewById(R.id.cycle);
            tess = new GridLayoutManager(MainActivity.this, 2);
            nann.setHasFixedSize(true);
            nann.setLayoutManager(tess);
            mBar.setVisibility(View.INVISIBLE);
            mAdapter = new RecycleAdapter(made, MainActivity.this);
            nann.setAdapter(mAdapter);


        }
    }




   }



