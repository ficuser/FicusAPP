package com.example.ficus.Hotel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.ficus.R;
import com.example.ficus.SetData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HotelAcitivity extends AppCompatActivity {
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
        statusBarHide(this);
        Button button=(Button)findViewById(R.id.hotellist_backButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
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
    //状态栏隐藏
    public static void statusBarHide(Activity activity){
        // 代表 5.0 及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            decorView.setSystemUiVisibility(option);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            return;
        }
        // versionCode > 4.4  and versionCode < 5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
    private void initHotel2(){



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

