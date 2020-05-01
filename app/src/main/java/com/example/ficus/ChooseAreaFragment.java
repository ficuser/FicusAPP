package com.example.ficus;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ficus.db.City;
import com.example.ficus.db.Province;
import com.example.ficus.util.HttpUtil;
import com.example.ficus.util.Utility;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ChooseAreaFragment extends Fragment {

    private static final int LEVEL_PROVINCE=0;
    private static final int LEVEL_CITY=1;
    private static final int LEVEL_COUNTY=2;

    private TextView titleText;
    private ArrayAdapter<String> adapter;
    private List<String> dataList=new ArrayList<>();

    private List<Province> provinceList;
    private List<City> cityList;
    private City selectedCity;

    private Button backButton;
    private ListView listView;
    private Province selectedProvince;
    private int currentLevel;
    private ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container, Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.choose_area,container,false);
        LitePal.deleteAll(Province.class);
        titleText=(TextView)view.findViewById(R.id.title_text);
        backButton=(Button) view.findViewById(R.id.back_button);
        listView=(ListView) view.findViewById(R.id.list_view);
        adapter=new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,dataList);
        listView.setAdapter(adapter);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle sacedInstanceState) {

        super.onActivityCreated(sacedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int  position, long id) {
                if(currentLevel==LEVEL_PROVINCE) {
                    selectedProvince = provinceList.get(position);
                    queryCities();
                }else if(currentLevel==LEVEL_CITY)
                {
                    selectedCity=cityList.get(position);
                    if(getActivity() instanceof MainActivity){
                        Intent intent =new Intent(getActivity(),MainActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                    }else if(getActivity() instanceof MainActivity){
                        MainActivity activity=(MainActivity) getActivity();
                        activity.mDraerLayout.closeDrawers();
                    }
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public  void onClick(View view)
            {
                if(currentLevel==LEVEL_COUNTY)
                {
                    queryCities();
                }else if(currentLevel==LEVEL_CITY)
                {
                    queryProvinces();
                }
            }
        });
        queryProvinces();
    }
    private void queryCities(){
        titleText.setText(selectedProvince.getProvinceName());
        backButton.setVisibility(View.VISIBLE);
        cityList=LitePal.where("provinceId=?",String.valueOf(selectedProvince.getId())).find(City.class);
        if(cityList.size()>0)
        {
            dataList.clear();
            for(City city:cityList)
            {
                dataList.add(city.getCityName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVEL_CITY;
        }else{
            int provinceCode=selectedProvince.getProvinceCode();
            String address="http://guolin.tech/api/china/"+provinceCode;
            queryFromServer(address,"city");
        }
    }

    private void queryProvinces(){
        titleText.setText("中国");
        backButton.setVisibility(View.GONE);
        provinceList= LitePal.findAll(Province.class);
        if(provinceList.size()>0)
        {
            dataList.clear();
            for(Province province:provinceList){
                dataList.add(province.getProvinceName());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            currentLevel=LEVEL_PROVINCE;
        }else{
            String address="http://guolin.tech/api/china";
            queryFromServer(address,"province");
        }
    }

    private void queryFromServer(String address, final String type) {
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(address, new Callback(){
            @Override
            public void onResponse(Call call, Response response)throws IOException {
                String responseText=response.body().string();
                boolean result=false;
                if("province".equals(type))
                {
                    result= Utility.handleProvinceResponse(responseText);
                }
                if("city".equals(type))
                {
                    result= Utility.handleCityResponse(responseText,selectedProvince.getId());
                }
                if(result) {
                    getActivity().runOnUiThread(new Runnable(){
                        @Override
                        public void run() {
                            closeProgressDialog();
                            if ("province".equals(type)){
                                queryProvinces();
                            }else if("city".equals(type))
                            {
                                queryCities();
                            }
                        }
                    });
                }
            }
            public void onFailure(Call call, IOException e){
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        closeProgressDialog();
                        Toast.makeText(getActivity(),"加载失败", Toast.LENGTH_SHORT).show();
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
            progressDialog=new ProgressDialog(getActivity());
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
