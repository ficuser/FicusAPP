package com.example.ficus.util;

import android.text.TextUtils;

import com.example.ficus.db.City;
import com.example.ficus.db.Hotel;
import com.example.ficus.db.Image;
import com.example.ficus.db.Province;
import com.example.ficus.db.Tourist;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class Utility {
    public static boolean handeTouristReponse(String response)
    {
        if(!TextUtils.isEmpty(response))
        {
            try{
                Gson gson=new Gson();
                List<Tourist> TouristList=gson.fromJson(response,new TypeToken<List<Tourist>>(){}.getType());
                for(Tourist tourist :TouristList)
                {
                    try {
                        tourist.save();
                    }catch (Exception e)
                    {
                        continue;
                    }
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleHotelResponse(String response)
    {
        if(!TextUtils.isEmpty(response))
        {
            try{
                Gson gson=new Gson();
                List<Hotel> HotelList=gson.fromJson(response,new TypeToken<List<Hotel>>(){}.getType());
                for(Hotel hotel :HotelList)
                {
                    try {
                        hotel.save();
                    }catch (Exception e)
                    {
                        continue;
                    }
                }
                return true;
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCityResponse(String response,int provinceId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try{
                JSONArray allCities= new JSONArray(response);
                for(int i=0;i<allCities.length();i++)
                {
                    JSONObject cityObeject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(cityObeject.getString("name"));
                    city.setCityCode(cityObeject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleProvinceResponse(String response)
    {
        if(!TextUtils.isEmpty(response))
        {
            try{
                JSONArray allProvincs= new JSONArray(response);
                for(int i=0;i<allProvincs.length();i++)
                {
                    JSONObject provinceObeject=allProvincs.getJSONObject(i);
                    Province province=new Province();
                    province.setProvinceName(provinceObeject.getString("name"));
                    province.setProvinceCode(provinceObeject.getInt("id"));
                    province.save();
                }
                return true;
            }catch(JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }
    public static Image handleResponse(String response)
    {
        if(!TextUtils.isEmpty(response))
        {
            try{
                Gson gson=new Gson();
                Image image=gson.fromJson(response,Image.class);
                image.save();
                return image;
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}