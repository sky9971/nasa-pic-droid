package com.sky9971.nasapicturesapp.ViewModel;

import android.os.Parcelable;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.sky9971.nasapicturesapp.Core.ApplicationController;
import com.sky9971.nasapicturesapp.Model.PictureModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class PictureViewModel extends ViewModel {
    MutableLiveData<ArrayList<PictureModel>> livedata;
    MutableLiveData<PictureModel> pictureData;
    MutableLiveData<Integer> pictureModelIndex;

    public PictureViewModel() {
        livedata = new MutableLiveData<>();
        pictureData = new MutableLiveData<>();
        pictureModelIndex = new MutableLiveData<>();
        readJSON();
    }

    public MutableLiveData<ArrayList<PictureModel>> getLivedata() {
        return livedata;
    }

    public MutableLiveData<PictureModel> getPictureData() {
        return pictureData;
    }

    public void updateModel(int index){
        PictureModel model = livedata.getValue().get(index);
        pictureData.setValue(model);
        pictureModelIndex.setValue(index);
    }

    public void swipeRight(){
        pictureModelIndex.postValue(getLeftIndex(pictureModelIndex.getValue(),livedata.getValue().size()));
        pictureData.postValue(livedata.getValue().get(pictureModelIndex.getValue()));
    }

    public void swipeLeft(){
        pictureModelIndex.postValue(getRightIndex(pictureModelIndex.getValue(),livedata.getValue().size()));
        pictureData.postValue(livedata.getValue().get(pictureModelIndex.getValue()));
    }

    public static int getLeftIndex(int cur,int list_size){//move backward
        int prev = cur-1;
        if(prev < 0){
            return list_size-1;
        }else{
            return prev;
        }
    }

    public static int getRightIndex(int cur,int list_size){//move forward
        int next = cur+1;
        if(next < list_size){
            return next;
        }else{
            return 0;
        }
    }

    private void readJSON(){
        ArrayList<PictureModel> models = new ArrayList<>();
        try {
            JSONArray pictures = new JSONArray(readAssetFile());
            for (int i = 0; i < pictures.length(); i++) {
                try {
                    JSONObject pit = pictures.getJSONObject(i);
                    String copyright = "Not Available";
                    if(pit.has("copyright")){
                        copyright = pit.getString("copyright");
                    }
                    models.add(new PictureModel(pit.getString("title"),
                            pit.getString("service_version"),
                            pit.getString("url"),
                            pit.getString("hdurl"),
                            pit.getString("date"),
                            pit.getString("explanation"),
                            pit.getString("media_type"),
                            copyright));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        final DateFormat f = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Collections.sort(models, new Comparator<PictureModel>() {
            @Override
            public int compare(PictureModel pictureModel, PictureModel t1) {
                try {
                    Date d1 = f.parse(pictureModel.getDate());
                    Date d2 = f.parse(t1.getDate());
                    return (d1.getTime() > d2.getTime() ? -1 : 1);
//                return pictureModel.getDate().compareTo(t1.getDate());
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });
        livedata.postValue(models);
    }

    private String readAssetFile(){
        String json = null;
        try {
            InputStream is = ApplicationController.getInstance().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

}
