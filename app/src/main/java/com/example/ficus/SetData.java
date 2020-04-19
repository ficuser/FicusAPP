package com.example.ficus;
public class SetData{
    private String text1;
    private String text2;
    private int imageId;
    public SetData(String name,String price,int imageId) {
        this.text1=name;
        this.text2=price;
        this.imageId=imageId;}
    public SetData(int imageId)
    {
        this.imageId=imageId;
    }
    public SetData(String name,int imageId) {
        this.text1=name;
        this.imageId=imageId; }
    public int getImageId() {
        return imageId;
    }
    public String gerPrice() {
        return text2;
    }
    public String getName() {
        return text1;
    }
}
