package com.sky9971.nasapicturesapp.ViewModel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.sky9971.nasapicturesapp.Model.PictureModel;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;

@RunWith(JUnit4.class)
public class PictureViewModelMockTest extends TestCase {

    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    PictureViewModel viewModel;
    @Mock Observer<ArrayList<PictureModel>> observer;
    @Mock Observer<Integer> indexObserver;
    @Mock Observer<PictureModel> modelObserver;
    ArrayList<PictureModel> models;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        viewModel = new PictureViewModel();
        viewModel.getLivedata().observeForever(observer);
        viewModel.getPictureData().observeForever(modelObserver);
        viewModel.getPictureModelIndex().observeForever(indexObserver);
        models = new ArrayList<>();
        models.add(new PictureModel("Starburst Galaxy M94 from Hubble",
                "v1",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                "2019-12-01",
                "Why does this galaxy have a ring of bright blue stars?",
                "image",
                "ESA/HubbleNASA"));
        models.add(new PictureModel("Starburst Galaxy M94 from Hubble",
                "v1",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                "2019-12-01",
                "Why does this galaxy have a ring of bright blue stars?",
                "image",
                "ESA/HubbleNASA"));
    }

    @Test
    public void testNull(){
        assertNotNull(viewModel.getLivedata());
        assertTrue(viewModel.getLivedata().hasObservers());

        assertNotNull(viewModel.getPictureData());
        assertTrue(viewModel.getPictureData().hasObservers());

        assertNotNull(viewModel.getPictureModelIndex());
        assertTrue(viewModel.getPictureModelIndex().hasObservers());
    }

    @Test
    public void testLiveData(){
        viewModel.getLivedata().postValue(models);
        Mockito.verify(observer).onChanged(models);
        assertNotNull(viewModel.getLivedata().getValue());
        assertEquals(2,viewModel.getLivedata().getValue().size());
    }

    @Test
    public void testIndexLiveData(){
        viewModel.getPictureModelIndex().postValue(0);
        Mockito.verify(indexObserver).onChanged(0);
    }

    @Test
    public void testPictureModelData(){
        PictureModel model = new PictureModel("Starburst Galaxy M94 from Hubble",
                "v1",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                "2019-12-01",
                "Why does this galaxy have a ring of bright blue stars?",
                "image",
                "ESA/HubbleNASA");
        viewModel.getPictureData().postValue(model);
        Mockito.verify(modelObserver).onChanged(model);
        assertNotNull(viewModel.getPictureData().getValue());
    }

    @Test
    public void testUpdateModel(){
        viewModel.getLivedata().postValue(models);
        viewModel.updateModel(0);
        Mockito.verify(indexObserver).onChanged(0);
        Mockito.verify(modelObserver).onChanged(models.get(0));
        assertNotNull(viewModel.getPictureData().getValue());
    }

    @Test
    public void testSwipeRight(){
        models.add(new PictureModel("Starburst Galaxy M94 from Hubble",
                "v1",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                "2019-12-01",
                "Why does this galaxy have a ring of bright blue stars?",
                "image",
                "ESA/HubbleNASA"));
        viewModel.getLivedata().postValue(models);
        viewModel.updateModel(0);
        Mockito.after(2000);
        viewModel.swipeRight();
        int val = viewModel.getPictureModelIndex().getValue();
        assertEquals(2,val);
    }

    @Test
    public void testSwipeLeft(){
        models.add(new PictureModel("Starburst Galaxy M94 from Hubble",
                "v1",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                "2019-12-01",
                "Why does this galaxy have a ring of bright blue stars?",
                "image",
                "ESA/HubbleNASA"));
        models.add(new PictureModel("Starburst Galaxy M94 from Hubble",
                "v1",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_960.jpg",
                "https://apod.nasa.gov/apod/image/1912/M94_Hubble_1002.jpg",
                "2019-12-01",
                "Why does this galaxy have a ring of bright blue stars?",
                "image",
                "ESA/HubbleNASA"));
        viewModel.getLivedata().postValue(models);
        viewModel.updateModel(0);
        viewModel.swipeLeft();
        int val = viewModel.getPictureModelIndex().getValue();
        assertEquals(1,val);
    }

    @After
    public void tearDown() throws Exception{
        viewModel = null;
        observer = null;
        indexObserver = null;
        modelObserver = null;
        models = null;
    }
}
