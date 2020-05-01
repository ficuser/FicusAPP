package com.example.ficus;
public class SetData{
    private String text1;
    private String text2;
    private int imageId;
    private String imageUrl;
    public SetData(String name,String price,String imageUrl) {
        this.text1=name;
        this.text2=price;
        this.imageUrl=imageUrl;}

    public SetData(String name,String imageUrl)
    {
         this.text1=name;
         this.imageUrl=imageUrl;
    }

    public SetData(int imageId)
    {
        this.imageId=imageId;
    }
    public SetData(String name,int imageId) {
        this.text1=name;
        this.imageId=imageId; }
    public String getImageUrl(){
        return imageUrl;
    }
    public int getImageId()
    {
        return imageId;
    }
    public String gerPrice() {
        return text2;
    }
    public String getName() {
        return text1;
    }
}
