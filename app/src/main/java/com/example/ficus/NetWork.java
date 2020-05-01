package com.example.ficus;

/*
网络接口
 */
public class NetWork {
    private static  final String HttpUrl="http://39.102.79.163:8080";

    private static String getOtpCodeURL =HttpUrl+"/Tourist/login.action";
    private static String loginURL = HttpUrl+"/Tourist/login.action";
    private static String registerURL = HttpUrl+"/Tourist/register.action";
    private static String TouristUrl=HttpUrl+"/Tourist/tourist.action?touristCity=wuhan&nums=10";
    private static String HotelUrl=HttpUrl+"/Tourist/hotel2.action?HotelCity=wuhan&nums=50";
    private static String ViewHUrl=HttpUrl+"/Tourist/viewer.action";
    private static String ImageUrl=HttpUrl+"/Tourist/image.action?ImageCity=wuhan";

    public static String getHttpUrl(){
        return HttpUrl;
    }

    public static String getGetOtpCodeURL(){
        return getOtpCodeURL;
    }

    public static String getLoginURL() {
        return loginURL;
    }

    public static String getRegisterURL() {
        return registerURL;
    }

    public static String getTouristUrl() {
        return TouristUrl;
    }

    public static String getHotelUrl() {
        return HotelUrl;
    }

    public static String getViewHUrl() {
        return ViewHUrl;
    }

    public static String getImageUrl() {
        return ImageUrl;
    }
}
