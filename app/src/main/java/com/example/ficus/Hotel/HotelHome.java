package com.example.ficus.Hotel;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.baidu.mapapi.map.Text;
import com.bumptech.glide.Glide;
import com.example.ficus.NetWork;
import com.example.ficus.R;
import com.example.ficus.db.Hotel;
import com.example.ficus.util.HttpUtil;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HotelHome extends AppCompatActivity {
    public static  String HOTEL_NAME="hotel_name";
    public static  String hotelStar="hotelStar";
    public static  String hotelImageUrl="hotelImageUrl";
    public static  String hotelAddress="hotelAddress";
    public static  String hotelTag="hotelTag";
    public static  String hotelScore="hotelScore";
    public static  String hotelCharm="hotelCharm";
    public static  String hotelLow="hotelLow";
    public static  String hotelEvaluate="hotelEvaluate";
    public static  String hotelPrice="hotelPrice";
    public static  String hotelUser="hotelUser";
    public static  String hotelUrl="hotelUrl";
    private List<Hotel> hotelList;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
        statusBarHide(this);
        Intent intent=getIntent();
        String HotelName=intent.getStringExtra(HOTEL_NAME);
        String HotelStar=intent.getStringExtra(hotelStar);
        String HotelImageUrl=intent.getStringExtra(hotelImageUrl);
        String HotelAddress=intent.getStringExtra(hotelAddress);
        String HotelTag=intent.getStringExtra(hotelTag);
        String HotelScore=intent.getStringExtra(hotelScore);
        String HotelCharm=intent.getStringExtra(hotelCharm);
        String HotelLow=intent.getStringExtra(hotelLow);
        String HotelEvaluate=intent.getStringExtra(hotelEvaluate);
        String HotelPrice=intent.getStringExtra(hotelPrice);
        String HotelUser=intent.getStringExtra(hotelUser);
        String HotelUrl=intent.getStringExtra(hotelUrl);

        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);

        ImageView hotelImageView=(ImageView)findViewById(R.id.hotel_image_view);
        TextView hotel_price=(TextView)findViewById(R.id.hotel_price);
        hotel_price.setText("￥"+HotelPrice+"起"+"             ");

        TextView hotel_start=(TextView)findViewById(R.id.hotel_start);
        hotel_start.setText(HotelStar);

        TextView hotel_addresss=(TextView)findViewById(R.id.hotel_address);
        hotel_addresss.setText(HotelAddress);

        TextView hotel_evaluate=(TextView)findViewById(R.id.user_evalueate);
        hotel_evaluate.setText(HotelUser+"             ");

        TextView hotel_scorce=(TextView)findViewById(R.id.score);
        hotel_scorce.setText(HotelScore);

        TextView charm=(TextView)findViewById(R.id.charm);
        charm.setText(HotelCharm);

        Glide.with(HotelHome.this)
                .load(HotelImageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .into(hotelImageView);
        TextView fruitContentText=(TextView)findViewById(R.id.fruit_content_text);

        setSupportActionBar(toolbar);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        //这里有点问题
        collapsingToolbarLayout.setTitle(HotelName);
        fruitContentText.setText(HotelEvaluate+HotelTag);
    }
    //状态栏隐藏
    public static void statusBarHide(Activity activity){
        // 代表 5.0 及以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
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

    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent=new StringBuilder();
        for(int i=0;i<5;i++)
        {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
