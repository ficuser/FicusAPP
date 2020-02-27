package com.example.ficus.Tourist;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.ficus.R;
import com.example.ficus.SetData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tourist extends AppCompatActivity {

    private SetData[] fruits = {
            new SetData("Apple", R.drawable.apple),new SetData("Banana",R.drawable.banana),
            new SetData("Orange",R.drawable.orange),new SetData("Watermelon",R.drawable.watermelon),
            new SetData("Pear",R.drawable.pear),new SetData("Grape",R.drawable.grape),
            new SetData("Pineapple",R.drawable.pineapple),new SetData("Strawberry",R.drawable.strawberry),
            new SetData("Cherry",R.drawable.cherry),new SetData("Mango",R.drawable.mango),
    };

    private List<SetData> touristLists=new ArrayList<>();

    private TouristAdater adapter;

    private SwipeRefreshLayout swipeRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist);
        initFruits();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_tourist);
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new TouristAdater(touristLists);
        recyclerView.setAdapter(adapter);

        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshFruits();
            }
        });
    }
    private void initFruits()
    {
        touristLists.clear();
        for(int i=0;i<50;i++)
        {
            Random random=new Random();
            int index=random.nextInt(fruits.length);
            touristLists.add(fruits[index]);
        }
    }

    private void refreshFruits()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initFruits();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}




