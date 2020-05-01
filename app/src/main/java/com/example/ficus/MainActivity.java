package com.example.ficus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
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
import androidx.viewpager.widget.ViewPager;

import com.example.ficus.Image.ImageShow;
import com.example.ficus.Login.activity.LoginActivity;
import com.example.ficus.Fragment.MyFragmentPaperAdapter;
import com.example.ficus.Hotel.HotelAcitivity;
import com.example.ficus.Map.Edit;
import com.example.ficus.Map.Map;
import com.example.ficus.Tourist.TouristActivity;
import com.example.ficus.way.wayActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.circlenavigator.CircleNavigator;

import java.util.concurrent.atomic.AtomicInteger;

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
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDraerLayout.closeDrawers();
                return true;
            }
        });*/

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
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
