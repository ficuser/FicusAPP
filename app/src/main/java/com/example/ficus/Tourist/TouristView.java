package com.example.ficus.Tourist;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.ficus.R;
import com.example.ficus.SetData;

import java.util.ArrayList;
import java.util.List;


public class TouristView extends AppCompatActivity {
    private TouristViewAdapter adapter;
    private List<SetData> hotelList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touristview);
        initTouristView();
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new TouristViewAdapter(hotelList);
        recyclerView.setAdapter(adapter);
    }

    private void initTouristView(){
        for(int i=0;i<2;i++)
        {
            SetData apple=new SetData(R.drawable.apple);
            hotelList.add(apple);
            SetData banana=new SetData(R.drawable.banana);
            hotelList.add(banana);
            SetData watermelong=new SetData(R.drawable.watermelon);
            hotelList.add(watermelong);
        }
    }
}
