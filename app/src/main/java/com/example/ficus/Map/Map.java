package com.example.ficus.Map;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.example.ficus.R;

import java.util.ArrayList;
import java.util.List;

public class Map extends AppCompatActivity{
    private  LocationClient mLocationClient;
    private TextView position;
    private MapView mapView;

    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mLocationClient =new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());

        SDKInitializer.initialize(getApplicationContext());//必须的东西

        setContentView(R.layout.activity_map);

        mapView=(MapView)findViewById(R.id.bmapView) ;//第二次加的
        baiduMap=mapView.getMap();//第三次加
        baiduMap.setMyLocationEnabled(true);//添加“我”

        position=(TextView)findViewById(R.id.position_text_view);
        List<String> permissionList=new ArrayList<>();
        if(ContextCompat.checkSelfPermission(Map.this,
                Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_COARSE_LOCATION);
        }

        if(ContextCompat.checkSelfPermission(Map.this,
                Manifest.permission.READ_PHONE_STATE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }

        if(ContextCompat.checkSelfPermission(Map.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)!=PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if(!permissionList.isEmpty()){
            String[] permissions=permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(Map.this,permissions,1);

        }else{
            requestLocation();
        }
    }

    private void navigateTo(BDLocation location)
    {
        if(isFirstLocate){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());
            MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);
            baiduMap.animateMapStatus(update);
            update=MapStatusUpdateFactory.zoomTo(16f);
            baiduMap.animateMapStatus(update);
            isFirstLocate=false;
        }
        MyLocationData.Builder locationBuilder=new MyLocationData.Builder();
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        baiduMap.setMyLocationData(locationData);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        mapView.onResume();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        mapView.onResume();
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);
    }

    private void  requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResulct){
        switch (requestCode){
            case 1:
                if(grantResulct.length>0){
                    for(int result:grantResulct){
                        if (result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this,"必须同意所有权限才可以使用本程序",Toast.LENGTH_SHORT).show();
                            finish();
                            return ;
                        }else{
                            Toast.makeText(this,"发生未知错误",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            default:
        }
    }

    public class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(final BDLocation bdLocation) {
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                Toast.makeText(Map.this,Integer.toString(bdLocation.getLocType()),Toast.LENGTH_SHORT).show();
                navigateTo(bdLocation);
            }
            /*            runOnUiThread(new Runnable(){
                @Override
                public void run() {
                    StringBuilder currentPosition=new StringBuilder();
                    currentPosition.append("纬度:").append(bdLocation.getLatitude()).append("\n");

                    currentPosition.append("经线:").append(bdLocation.getLongitude()).append("\n");

                    currentPosition.append("国家:").append(bdLocation.getCountry()).append("\n");

                    currentPosition.append("省:").append(bdLocation.getProvince()).append("\n");

                    currentPosition.append("市:").append(bdLocation.getCity()).append("\n");

                    currentPosition.append("定位方式：");

                    if(bdLocation.getLocType()==BDLocation.TypeGpsLocation){
                        currentPosition.append("GPS");
                    }else if(bdLocation.getLocType()==BDLocation.TypeNetWorkException){
                        currentPosition.append("网络");
                    }else{
                        currentPosition.append(Integer.toString(bdLocation.getLocType()));
                    }
                    position.setText(currentPosition);
                }
            });*/
        }
    }
}
