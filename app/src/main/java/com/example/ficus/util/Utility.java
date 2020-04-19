package com.example.ficus.util;

import android.text.TextUtils;

import com.example.ficus.db.Image;
import com.example.ficus.db.Province;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
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
            }catch (JSONException e) {
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
                //image.save();
                return image;
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return null;
    }
}