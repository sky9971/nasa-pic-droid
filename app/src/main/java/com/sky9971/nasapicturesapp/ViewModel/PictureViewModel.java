package com.sky9971.nasapicturesapp.ViewModel;

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

    public MutableLiveData<Integer> getPictureModelIndex() {
        return pictureModelIndex;
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
        if(list_size<=0){
            return 0;
        }
        int prev = cur-1;
        if(prev < 0){
            return list_size-1;
        }else{
            return prev;
        }
    }

    public static int getRightIndex(int cur,int list_size){//move forward
        if(list_size<=0){
            return 0;
        }
        int next = cur+1;
        if(next < list_size){
            return next;
        }else{
            return 0;
        }
    }

    public void readJSON(){
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
            if(ApplicationController.getInstance()==null){
                return dummy_data;
            }
            InputStream is = ApplicationController.getInstance().getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return dummy_data;
        }
        return json;
    }

    private static String dummy_data = "[\n" +
            "        {\n" +
            "        \"copyright\": \"ESA/HubbleNASA\",\n" +
            "        \"date\": \"2019-12-01\",\n" +
            "        \"explanation\": \"Why does this galaxy have a ring of bright blue stars?  Beautiful island universe Messier 94 lies a mere 15 million light-years distant in the northern constellation of the Hunting Dogs (Canes Venatici). A popular target for Earth-based astronomers, the face-on spiral galaxy is about 30,000 light-years across, with spiral arms sweeping through the outskirts of its broad disk. But this Hubble Space Telescope field of view spans about 7,000 light-years across M94's central region. The featured close-up highlights the galaxy's compact, bright nucleus, prominent inner dust lanes, and the remarkable bluish ring of young massive stars. The ring stars are all likely less than 10 million years old, indicating that M94 is a starburst galaxy that is experiencing an epoch of rapid star formation from inspiraling gas. The circular ripple of blue stars is likely a wave propagating outward, having been triggered by the gravity and rotation of a oval matter distributions. Because M94 is relatively nearby, astronomers can better explore details of its starburst ring.    Astrophysicists: Browse 2,000+ codes in the Astrophysics Source Code Library\",\n" +
            "        \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg\",\n" +
            "        \"media_type\": \"image\",\n" +
            "        \"service_version\": \"v1\",\n" +
            "        \"title\": \"Starburst Galaxy M94 from Hubble\",\n" +
            "        \"url\": \"https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "        \"copyright\": \"Steve Mazlin\",\n" +
            "        \"date\": \"2019-12-03\",\n" +
            "        \"explanation\": \"Is this what will become of our Sun? Quite possibly.  The first hint of our Sun's future was discovered inadvertently in 1764. At that time, Charles Messier was compiling a list of diffuse objects not to be confused with comets. The 27th object on Messier's list, now known as M27 or the Dumbbell Nebula, is a planetary nebula, the type of nebula our Sun will produce when nuclear fusion stops in its core. M27 is one of the brightest planetary nebulae on the sky, and can be seen toward the constellation of the Fox (Vulpecula) with binoculars. It takes light about 1000 years to reach us from M27, featured here in colors emitted by hydrogen and oxygen. Understanding the physics and significance of M27 was well beyond 18th century science. Even today, many things remain mysterious about bipolar planetary nebula like M27, including the physical mechanism that expels a low-mass star's gaseous outer-envelope, leaving an X-ray hot white dwarf.   APOD across world languages: Arabic, Catalan, Chinese (Beijing), Chinese (Taiwan), Croatian, Czech, Dutch, Farsi, French, French, German, Hebrew, Indonesian, Japanese, Korean, Montenegrin, Polish, Russian, Serbian, Slovenian,  Spanish and Ukrainian\",\n" +
            "        \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/M27_Mazlin_2000.jpg\",\n" +
            "        \"media_type\": \"image\",\n" +
            "        \"service_version\": \"v1\",\n" +
            "        \"title\": \"M27: The Dumbbell Nebula\",\n" +
            "        \"url\": \"https://apod.nasa.gov/apod/image/1912/M27_Mazlin_960.jpg\"\n" +
            "        },\n" +
            "        {\n" +
            "        \"copyright\": \"Ivan Pedretti\",\n" +
            "        \"date\": \"2019-12-04\",\n" +
            "        \"explanation\": \"It may appear, at first, like the Galaxy is producing the lightning, but really it's the Earth. The featured nighttime landscape was taken from a southern tip of the Italian Island of Sardinia in early June. The foreground rocks and shrubs are near the famous Capo Spartivento Lighthouse, and the camera is pointed south toward Algeria in Africa.  In the distance, across the Mediterranean Sea, a thunderstorm is threatening, with several electric lightning strokes caught together during this 25-second wide-angle exposure.  Much farther in the distance, strewn about the sky, are hundreds of stars in the neighborhood of our Sun in the Milky Way Galaxy. Farthest away, and slanting down from the upper left, are billions of stars that together compose the central band of our Milky Way.   Free Lecture: APOD editor to speak in NYC on January 3\",\n" +
            "        \"hdurl\": \"https://apod.nasa.gov/apod/image/1912/ElectricMilkyWay_Pedretti_1920.jpg\",\n" +
            "        \"media_type\": \"image\",\n" +
            "        \"service_version\": \"v1\",\n" +
            "        \"title\": \"Electric Night\",\n" +
            "        \"url\": \"https://apod.nasa.gov/apod/image/1912/ElectricMilkyWay_Pedretti_1080.jpg\"\n" +
            "        }\n" +
            "        ]";
}
