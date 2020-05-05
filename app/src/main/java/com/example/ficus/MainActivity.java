package com.example.ficus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.ficus.Hotel.HotelAdapter;
import com.example.ficus.Image.ImageShow;
import com.example.ficus.Login.activity.LoginActivity;
import com.example.ficus.Fragment.MyFragmentPaperAdapter;
import com.example.ficus.Hotel.HotelAcitivity;
import com.example.ficus.Map.Edit;
import com.example.ficus.Map.Map;
import com.example.ficus.Tourist.TouristActivity;
import com.example.ficus.db.Hotel;
import com.example.ficus.db.User;
import com.example.ficus.util.HttpUtil;
import com.example.ficus.util.Utility;
import com.example.ficus.way.wayActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    //几个代表页面的常量
    public static final int PAGE_ONE = 0;
    public static final int PAGE_TWO = 1;
    public static final int PAGE_THREE = 2;
    public static final int PAGE_FOUR = 3;

    private ViewPager vpager;
    private boolean isContinue = true;//判断是否轮播
    private MyFragmentPaperAdapter mAdapter;
    private AtomicInteger what=new AtomicInteger(0);
    private ViewGroup group;

    public DrawerLayout mDraerLayout;
    private Context mContext;



    private boolean flag=false;
    private List<SetData> hotelLists=new ArrayList<>();
    private HotelAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;
    private ProgressDialog progressDialog;
    private List<String> dataList=new ArrayList<>();
    private List<Hotel> hotels;
    private List<User> users;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusBarHide(this);

        //viewpaper
        mAdapter = new MyFragmentPaperAdapter(getSupportFragmentManager());
        vpager = (ViewPager) findViewById(R.id.Tourist_paper);
        vpager.setAdapter(mAdapter);
        initMagicIndicator1();

        Button city_button=(Button)findViewById(R.id.city_button);
        city_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mDraerLayout.openDrawer(GravityCompat.START);
            }
        });
        /*
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        mDraerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        //NavigationView navView=(NavigationView)findViewById(R.id.nav_view);
        /*    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        app:headerLayout="@layout/nav_content"
        />*/
        /*
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                mDraerLayout.closeDrawers();
                return true;
            }
        });*/

        FloatingActionButton fab= findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                users= LitePal.findAll(User.class);
                if(users.size()>0)
                {
                    Toast.makeText(MainActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else{
                   // Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent it_login_to_main = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(it_login_to_main);
                }
            }
        });

        Button hotel=findViewById(R.id.hotel_button);
        hotel.getBackground().setAlpha(100);
        hotel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, HotelAcitivity.class);
                startActivity(intent);
            }
        });
        Button tourist=findViewById(R.id.tourist_button);
        tourist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent=new Intent(MainActivity.this, TouristActivity.class);
                startActivity(intent);
            }
        });

        Button text=findViewById(R.id.text_button);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"正在加载,请稍后....",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this, wayActivity.class);
                startActivity(intent);
            }
        });

        Button map=findViewById(R.id.map_button);
        map.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent =new Intent (MainActivity.this,Map.class);
                startActivity(intent);
                /*
                Intent intent=new Intent(MainActivity.this, Map.class);
                startActivity(intent);*/
            }
        });

        Button edit=findViewById(R.id.edit_button);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"正在加载，剩余2.5s",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent (MainActivity.this, ImageShow.class);
                startActivity(intent);
                /*
                Intent intent =new Intent (MainActivity.this, Edit.class);
                startActivity(intent);*/
            }
        });
        try {
            queryHotel();
        }catch (Exception e){
            Toast.makeText(this,"酒店数据加载异常！",Toast.LENGTH_SHORT).show();
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
            if(flag!=false) {
               // adapter.notifyDataSetChanged();
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
                        Toast.makeText(MainActivity.this, "酒店数据加载失败", Toast.LENGTH_SHORT).show();
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
    private void initMagicIndicator1() {
        MagicIndicator magicIndicator = this.findViewById(R.id.magic_indicator1);
        CircleNavigator circleNavigator = new CircleNavigator(this);
        circleNavigator.setCircleCount(4);
        circleNavigator.setCircleColor(Color.RED);
        circleNavigator.setCircleClickListener(new CircleNavigator.OnCircleClickListener() {
            @Override
            public void onClick(int index) {
                vpager.setCurrentItem(index);
            }
        });
        magicIndicator.setNavigator(circleNavigator);
        ViewPagerHelper.bind(magicIndicator,vpager);
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
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()){
            case android.R.id.home:
                mDraerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this,"You Click backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"You Click delete",Toast.LENGTH_SHORT).show();
                break;

            case R.id.settings:
                Toast.makeText(this,"you Click delete",Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}
