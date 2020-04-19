package com.example.ficus.Edit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.ficus.R;

public class Edit extends AppCompatActivity implements View.OnClickListener {

    private Button gaode_map, baidu_map, tencent_map;

    //这里的经纬度是直接获取的，在实际开发中从应用的地图中获取经纬度;
    private double latx = 39.9037448095;
    private double laty = 116.3980007172;
    private String mAddress = "北京天安门";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        gaode_map = (Button) findViewById(R.id.gaode_map);
        baidu_map = (Button) findViewById(R.id.baidu_map);
        tencent_map = (Button) findViewById(R.id.tencent_map);
        gaode_map.setOnClickListener(this);
        baidu_map.setOnClickListener(this);
        tencent_map.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.gaode_map:
                if (MapUtil.isGdMapInstalled()) {
                    MapUtil.openGaoDeNavi(Edit.this, 0, 0, null, latx, laty, mAddress);
                } else {
                    //这里必须要写逻辑，不然如果手机没安装该应用，程序会闪退，这里可以实现下载安装该地图应用
                    Toast.makeText(Edit.this, "尚未安装高德地图", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.baidu_map:
                if (MapUtil.isBaiduMapInstalled()){
                    MapUtil.openBaiDuNavi(Edit.this, 0, 0, null, latx, laty, mAddress);
                } else {
                    Toast.makeText(Edit.this, "尚未安装百度地图", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tencent_map:
                if (MapUtil.isTencentMapInstalled()){
                    MapUtil.openTencentMap(Edit.this, 0, 0, null, latx, laty, mAddress);
                } else {
                    Toast.makeText(Edit.this, "尚未安装腾讯地图", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
