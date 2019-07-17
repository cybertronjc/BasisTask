package com.jagdishchoudhary.basistask.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.jagdishchoudhary.basistask.R;
import com.jagdishchoudhary.basistask.ZoomOutPageTransformer;
import com.jagdishchoudhary.basistask.model.Data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager2;
    LayoutInflater layoutInflater;
    RequestQueue requestQueue;
    private Data data;
    private ViewPagerAdapter mAdapter;
    private String urlJsonObj = "https://gist.githubusercontent.com/anishbajpai014/d482191cb4fff429333c5ec64b38c197/raw/b11f56c3177a9ddc6649288c80a004e7df41e3b9/HiringTask.json";
    private List<Data> dataList = new ArrayList<>();
    private Button btnRestart;
    private TextView postionText;
    private ProgressBar progressBar1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        postionText = (TextView)findViewById(R.id.postionText);
        progressBar1 = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar1.setVisibility(View.VISIBLE);

        btnRestart = (Button)findViewById(R.id.btnRestart);

        viewPager2 = (ViewPager2)findViewById(R.id.viewPager2);
        layoutInflater = getLayoutInflater();

        // Instantiate the cache
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024); // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        Network network = new BasicNetwork(new HurlStack());

// Instantiate the RequestQueue with the cache and network.
        requestQueue = new RequestQueue(cache, network);

// Start the queue
        requestQueue.start();

        loadData();

        viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        //Toast.makeText(MainActivity.this, "Current:" + viewPager2.getCurrentItem(), Toast.LENGTH_SHORT).show();
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                postionText.setText("Current Postion : " + (position + 1) + "/"+ dataList.size());
                progressBar1.setProgress(position+1);

            }
        });



        btnRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager2.setCurrentItem(0);

            }
        });

    }

    private void loadData() {
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlJsonObj, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Removing "/" from the Json.
                String jsonResponse = response.replace("/", "");

                // getting the Json data
                try {
                    Gson gson = new Gson();
                    JSONObject object = new JSONObject(jsonResponse);
                    JSONArray jsonArray = object.getJSONArray("data");

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        data = gson.fromJson(jsonObject.toString(), Data.class);
                        //Adding to the list
                        dataList.add(data);
                        progressBar.setVisibility(View.GONE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.this, "E:"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                mAdapter = new ViewPagerAdapter(dataList,layoutInflater, viewPager2);
                viewPager2.setAdapter(mAdapter);
                    progressBar1.setMax(dataList.size());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "Error : "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

//
    }

    @Override
    public void onBackPressed() {
        if (viewPager2.getCurrentItem() == 0) {
            super.onBackPressed();
        }
        else {
            viewPager2.setCurrentItem(viewPager2.getCurrentItem() - 1);
        }
    }
}
