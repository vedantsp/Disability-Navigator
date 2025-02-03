package com.example.android.myapplication;
/**
 * Created by DELL on 23/03/2018.
 */

public class ImageUpload {
    public String name;
    public String addr;
    public String url;
    public String lat;
    public String longg;
//    public String url1;


    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongg() {
        return longg;
    }

    public void setLongg(String longg) {
        this.longg = longg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ImageUpload(String name, String url, String addr, String lat,String longg) {
        this.name = name;
        this.addr=addr;
        this.url = url;
        this.lat=lat;
        this.longg=longg;
    }

    public ImageUpload()
    {

    }
}
