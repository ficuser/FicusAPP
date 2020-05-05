package com.example.ficus.Hotel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ficus.Image.ImageShow;
import com.example.ficus.R;
import com.example.ficus.util.MywebView;

public class XieCheng extends AppCompatActivity {

    private String Imageurltest;

    public static  String hotelUrl="hotelUrl";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiecheng);
        Intent intent=getIntent();
        String HotelUrl=intent.getStringExtra(hotelUrl);

        statusBarHide(XieCheng.this);
        WebView webView=(WebView)findViewById(R.id.webview);
        try {
            test(webView,HotelUrl);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private void test(WebView webView,String hotelUrl)throws InterruptedException {
        if(hotelUrl!=null)
        {
            MywebView mywebView=new MywebView(this,webView,hotelUrl);
            mywebView.loadUrl();
        }else{
            Thread.sleep(1500);
            Toast.makeText(this,"获取URL失败",Toast.LENGTH_SHORT).show();
        }
    }

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
}
