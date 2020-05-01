package com.example.ficus.way;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;

import com.example.ficus.Image.ImageShow;
import com.example.ficus.R;
import com.example.ficus.db.CityView;
import com.example.ficus.db.Image;
import com.example.ficus.util.MywebView;

import java.util.List;

public class wayActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private List<Image> imageList;
    private String Imageurltest="https://h5.m.taobao.com/trip/wx-destination/pages/contents/index.html?_wx_tpl=https%3A%2F%2Fh5.m.taobao.com%2Fjs%2Ftrip%2Fwx-destination%2Fpages%2Fcontents%2Findex.js&destId=100266&contentType=doyenNotesModule&spm=181.9406239.dx_kingkong.4&scm=&ttid=&_preProjVer=2.4.1&_projVer=0.1.160";
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_way);
        Imageurltest=CityView.getWuhan();
        //queryFromServer(NetWork.getImageUrl(),"wuhan");
        WebView webView=(WebView)findViewById(R.id.way_webview);
        statusBarHide(wayActivity.this);
        try{
            test(webView);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
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
    private void test(WebView webView)throws InterruptedException {
        if(Imageurltest!=null)
        {
            MywebView mywebView=new MywebView(this,webView,Imageurltest);
            mywebView.loadUrl();
        }else{
            Thread.sleep(3000);
            //Toast.makeText(.this,"获取URL失败",Toast.LENGTH_SHORT).show();
        }
    }
}
