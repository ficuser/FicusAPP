package com.example.ficus.Hotel;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.example.ficus.NetWork;
import com.example.ficus.R;
import com.example.ficus.SetData;
import com.example.ficus.db.Hotel;
import com.example.ficus.util.HttpUtil;
import com.example.ficus.util.Utility;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HotelAcitivity extends AppCompatActivity {
    private boolean flag=false;
    private List<SetData> hotelLists=new ArrayList<>();
    private HotelAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressDialog progressDialog;
    private List<String> dataList=new ArrayList<>();
    private List<Hotel> hotels;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
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
        //LitePal.deleteAll(Hotel.class);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        GridLayoutManager layoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(layoutManager);
        queryHotel();
        adapter=new HotelAdapter(hotelLists,hotels);
        recyclerView.setAdapter(adapter);
        swipeRefresh=(SwipeRefreshLayout)findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(R.color.colorPrimary);
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public void onRefresh(){
                refreshFruits();
            }
        });
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
    private void queryHotel()
    {
        hotels= LitePal.findAll(Hotel.class);
        if(hotels.size()>0)
        {
            for(Hotel hotel :hotels){
                hotelLists.add(new SetData(hotel.getHotelHostelName(),hotel.getHotelPrice(),hotel.getHotelImageUrl()));
            }
            if(flag!=false){
                adapter.notifyDataSetChanged();
            }else
            {
            }
           // recyclerView.set
        }else{
            String address= NetWork.getHotelUrl();
            flag=true;
            queryFromServer(address,"wuhan");
        }
    }
    private void queryFromServer(String address,final String City)
    {
        /*根据传入的地址从服务器上查询数据*/
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                boolean resulct=false;
                if("wuhan".equals(City)){
                    resulct= Utility.handleHotelResponse(responseText);
                }
                if(resulct)
                {
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run(){
                            closeProgressDialog();
                            if("wuhan".equals(City))
                            {
                                queryHotel();
                            }
                        }
                    });
                }
            }
            public void onFailure(Call call, IOException e){
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        closeProgressDialog();
                        Toast.makeText(HotelAcitivity.this, "加载失败", Toast.LENGTH_SHORT).show();
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
    private void refreshFruits()
    {
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    Thread.sleep(2000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        //initHotel();
                        adapter.notifyDataSetChanged();
                        swipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}