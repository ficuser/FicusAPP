package com.example.ficus.Tourist;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.example.ficus.R;
import com.example.ficus.SetData;

import java.util.ArrayList;
import java.util.List;


public class TouristView extends AppCompatActivity {
    private TouristViewAdapter adapter;
    private List<SetData> hotelList=new ArrayList<>();
    public static String touristTesturl="touristTesturl";
    public static String touristViewName="touristViewName";
    public static String touristImageView1="touristImageView1";
    public static String touristImageView2="touristImageView2";
    public static String touristImageView3="touristImageView3";
    public static String touristImageView4="touristImageView4";
    public static String touristImageView5="touristImageView5";
    public static String touristTouristGrade="touristTouristGrade";
    public static String touristViewPrice="touristViewPrice";

    private List<String> touristViewList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_touristview);
        statusBarHide(this);
        Button button=(Button)findViewById(R.id.touristlist_backButton);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
        Intent intent=getIntent();
        //touristViewList.add(intent.getStringExtra(touristImageView1));
        TextView touristviewName=(TextView)findViewById(R.id.name_touristview) ;
        touristviewName.setText("                "+intent.getStringExtra(touristViewName)+"              ");

        TextView touristviewstar=(TextView)findViewById(R.id.tour_start) ;
        touristviewstar.setText(intent.getStringExtra(touristTouristGrade)+"    ");

        TextView touristviewprice=(TextView)findViewById(R.id.touri_price) ;
        touristviewprice.setText(intent.getStringExtra(touristViewPrice));

        touristViewList.add(intent.getStringExtra(touristImageView2));
        touristViewList.add(intent.getStringExtra(touristImageView3));
        touristViewList.add(intent.getStringExtra(touristImageView4));
        touristViewList.add(intent.getStringExtra(touristImageView5));
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new TouristViewAdapter(touristViewList);
        recyclerView.setAdapter(adapter);
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

}
