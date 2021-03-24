package com.sky9971.nasapicturesapp.Model;

import android.net.Uri;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class PictureModelTest extends TestCase {

    @Test
    public void testNotNull(){
        PictureModel model = new PictureModel("Starburst Galaxy M94 from Hubble",
                "v1",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                "2019-12-01",
                "Why does this galaxy have a ring of bright blue stars?",
                "image",
                "ESA/HubbleNASA");
        assertNotNull(model);
    }

    @Test
    public void testGetImage() {
        PictureModel model = new PictureModel("Starburst Galaxy M94 from Hubble",
                "v1",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                "2019-12-01",
                "Why does this galaxy have a ring of bright blue stars?",
                "image",
                "ESA/HubbleNASA");
        Uri t = Uri.parse(model.hdUrl);
        assertNotNull(model.getImage());
        assertEquals(t.getPath(),model.getImage().getPath());
    }
}