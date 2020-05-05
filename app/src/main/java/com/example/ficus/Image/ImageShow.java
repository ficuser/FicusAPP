package com.example.ficus.Image;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ficus.NetWork;
import com.example.ficus.R;
import com.example.ficus.db.Image;
import com.example.ficus.util.HttpUtil;
import com.example.ficus.util.MywebView;
import com.example.ficus.util.Utility;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ImageShow extends AppCompatActivity  {
        private ProgressDialog progressDialog;
        private List<Image> imageList;
        //private  String Imageurltest="https://map.baidu.com/";
        private String Imageurltest="https://h5.m.taobao.com/trip/poi/photoes/index.html?mddId=100266&mddLevel=2&spm=181.9406239.dx_normalBanner.album&scm=&ttid=&_preProjVer=2.4.1&_projVer=0.2.28";
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_imageshow);
            //queryFromServer(NetWork.getImageUrl(),"wuhan");
            statusBarHide(ImageShow.this);
            WebView webView=(WebView)findViewById(R.id.webview);
            try {
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
            Toast.makeText(ImageShow.this,"获取URL失败",Toast.LENGTH_SHORT).show();
        }
    }
        /*根据传入的地址从服务器上查询数据*/
        private   void queryFromServer(String address,final String type){
            showProgressDialog();
            HttpUtil.sendOkHttpRequest(address, new Callback(){
                @Override
                public void onResponse(Call call, Response response)throws IOException {
                    String responseText=response.body().string();
                    Imageurltest= Utility.handleResponse(responseText).getImageUrl();
                }
                public void onFailure(Call call, IOException e){
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            closeProgressDialog();
                            Toast.makeText(ImageShow.this,"加载失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
            closeProgressDialog();
        }
        /*显示进度对话框*/
        private void showProgressDialog()
        {
            if(progressDialog==null)
            {
                progressDialog=new ProgressDialog(this);
                progressDialog.setMessage("正在加载........");
                progressDialog.setCanceledOnTouchOutside(false);
            }
            progressDialog.show();
        }
        /*关闭 进程对话框*/
        private void closeProgressDialog()
        {
            if(progressDialog!=null)
            {
                progressDialog.dismiss();
            }
        }
}
