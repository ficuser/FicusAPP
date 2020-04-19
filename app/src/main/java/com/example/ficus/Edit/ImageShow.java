package com.example.ficus.Edit;

import android.app.ProgressDialog;
import android.net.Network;
import android.os.Bundle;
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
        private String Imageurltest;
        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_imageshow);
            queryFromServer(NetWork.getImageUrl(),"wuhan");
            WebView webView=(WebView)findViewById(R.id.webview);
            // String address="https://h5.m.taobao.com/trip/poi/photoes/index.html?mddId=100266&mddLevel=2&spm=181.9406239.dx_normalBanner.album&scm=&ttid=&_preProjVer=2.4.1&_projVer=0.2.28";
            try {
                Thread.sleep(3000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            test(webView);
        }
        private void test(WebView webView)
        {
            if(Imageurltest!=null)
            {
                MywebView mywebView=new MywebView(this,webView,Imageurltest);
                mywebView.loadUrl();
            }else{
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
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            //closeProgressDialog();
                            Toast.makeText(ImageShow.this,"加载失败",Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });

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
