package com.example.ficus.Hotel;
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

public class Hotel extends AppCompatActivity {
    private List<SetData> hotelLists=new ArrayList<>();
    private HotelAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    private SetData[] hotels={
            new SetData("Apple","300", R.drawable.apple),new SetData("Banana","344",R.drawable.banana),
            new SetData("Orange","545",R.drawable.orange),new SetData("Watermelon","456",R.drawable.watermelon),
            new SetData("Pear","999",R.drawable.pear),new SetData("Grape","3432",R.drawable.grape),
            new SetData("Pineapple","231",R.drawable.pineapple),new SetData("Strawberry","542",R.drawable.strawberry),
            new SetData("Cherry","86775",R.drawable.cherry),new SetData("Mango","321",R.drawable.mango),
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotellist);
        initHotel();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new HotelAdapter(hotelLists);
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
    private void initHotel()
    {
        hotelLists.clear();
        for(int i=0;i<50;i++)
        {
            Random random=new Random();
            int index=random.nextInt(hotels.length);
            hotelLists.add(hotels[index]);
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
                        initHotel();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}

