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
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.BikingRouteOverlay;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.example.ficus.R;
import java.util.ArrayList;
import java.util.List;
import com.baidu.mapapi.overlayutil.DrivingRouteOverlay;
import com.baidu.mapapi.overlayutil.OverlayManager;
import com.baidu.mapapi.overlayutil.TransitRouteOverlay;
import com.baidu.mapapi.overlayutil.WalkingRouteOverlay;
import com.baidu.mapapi.search.core.RouteLine;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.DrivingRouteLine;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
public class Map extends AppCompatActivity{
    private  LocationClient mLocationClient;
    private TextView position;
    private MapView mapView;

    private BaiduMap baiduMap;
    private boolean isFirstLocate=true;
    //add

    //浏览路线节点相关
    //Button mBtnPre = null;//上一个节点
    ///Button mBtnNext = null;//下一个节点
    int nodeIndex = -1;//节点索引,供浏览节点时使用
    RouteLine route = null;
    OverlayManager routeOverlay = null;
    boolean useDefaultIcon = false;
    private TextView popupText = null;
    //搜索相关
    RoutePlanSearch mSearch = null;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mLocationClient =new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        CharSequence titleLable = "路线规划功能";
        SDKInitializer.initialize(getApplicationContext());//必须的东西

        setContentView(R.layout.activity_map);
        mapView=(MapView)findViewById(R.id.bmapView) ;//第二次加的
        baiduMap=mapView.getMap();//第三次加
        baiduMap.setMyLocationEnabled(true);//添加“我”

       // position=(TextView)findViewById(R.id.position_text_view);
        //权限申请
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

        }else {
            requestLocation();
            //连线
            /*
            //构建折线点坐标
            LatLng p1 = new LatLng(39.97923, 116.357428);
            LatLng p2 = new LatLng(39.94923, 116.397428);
            LatLng p3 = new LatLng(39.97923, 116.437428);
            List<LatLng> points = new ArrayList<LatLng>();
            points.add(p1);
            points.add(p2);
            points.add(p3);
            //设置折线的属性
            OverlayOptions mOverlayOptions = new PolylineOptions()
                    .width(10)
                    .color(0xAAFF0000)
                    .points(points);
            //在地图上绘制折线
            //mPloyline 折线对象
            Overlay mPolyline = baiduMap.addOverlay(mOverlayOptions);
            */
            //画半圆
            /*

            // 添加弧线坐标数据
            LatLng p1 = new LatLng(39.97923, 116.357428);//起点
            LatLng p2 = new LatLng(39.94923, 116.397428);//中间点
            LatLng p3 = new LatLng(39.97923, 116.437428);//终点

            //构造ArcOptions对象
            OverlayOptions mArcOptions = new ArcOptions()
                    .color(Color.RED)
                    .width(10)
                    .points(p1, p2, p3);

            //在地图上显示弧线
            Overlay mArc = baiduMap.addOverlay(mArcOptions);
             */
            //绘制圆
            /*
            //圆心位置
            LatLng center = new LatLng(39.90923, 116.447428);

            //构造CircleOptions对象
            CircleOptions mCircleOptions = new CircleOptions().center(center)
                    .radius(1400)
                    .fillColor(0xAA0000FF) //填充颜色
                    .stroke(new Stroke(5, 0xAA00ff00)); //边框宽和边框颜色

            //在地图上显示圆
            Overlay mCircle = baiduMap.addOverlay(mCircleOptions);
        */
            //绘制多边型
            /*
            //多边形顶点位置
            List<LatLng> points = new ArrayList<>();
            points.add(new LatLng(39.93923, 116.357428));
            points.add(new LatLng(39.91923, 116.327428));
            points.add(new LatLng(39.89923, 116.347428));
            points.add(new LatLng(39.89923, 116.367428));
            points.add(new LatLng(39.91923, 116.387428));

            //构造PolygonOptions
            PolygonOptions mPolygonOptions = new PolygonOptions()
                    .points(points)
                    .fillColor(0xAAFFFF00) //填充颜色
                    .stroke(new Stroke(5, 0xAA00FF00)); //边框宽度和颜色

            //在地图上显示多边形
            mBaiduMap.addOverlay(mPolygonOptions);
             */
        }

    }
    //将地图移到“我”的位置上
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
    //获取定位
    private void  requestLocation(){
        initLocation();
        mLocationClient.start();
    }

    //获取定位 5s一次
    private void initLocation(){
        LocationClientOption option=new LocationClientOption();
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        //option.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
        mLocationClient.setLocOption(option);
    }

    //权限判断
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
                            Toast.makeText(this,"发生未知错误，请再试一次",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                break;
            default:
        }
    }
    //判断请求类型
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
