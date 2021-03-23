package com.sky9971.nasapicturesapp.Model;

import android.net.Uri;

public class PictureModel {
    String title;
    String serviceVersion;
    String url;
    String hdUrl;
    String date;
    String explanation;
    String mediaType;
    String copyright;
    Uri image;

    public PictureModel(String Title,String ServiceVersion,String URL,String HdUrl,String Date, String Explanation,String MediaType, String CopyRight){
        this.title = Title;
        this.serviceVersion = ServiceVersion;
        this.url = URL;
        this.hdUrl = HdUrl;
        this.date = Date;
        this.explanation = Explanation;
        this.mediaType = MediaType;
        this.copyright = CopyRight;
        this.image = Uri.parse(this.hdUrl);
    }

    public String getCopyright() {
        return copyright;
    }

    public String getDate() {
        return date;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
