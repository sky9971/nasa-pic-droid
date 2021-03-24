package com.sky9971.nasapicturesapp.ViewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.core.app.ApplicationProvider;

import com.sky9971.nasapicturesapp.Core.ApplicationController;
import com.sky9971.nasapicturesapp.Model.PictureModel;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PictureViewModelTest extends TestCase {

    @Test
    public void testGetLeftIndex() {
        assertEquals(9,PictureViewModel.getLeftIndex(0,10));
        assertTrue(PictureViewModel.getLeftIndex(0,10)<10);
    }

    @Test
    public void testGetRightIndex() {
        assertEquals(2,PictureViewModel.getRightIndex(1,5));
        assertTrue(PictureViewModel.getRightIndex(1,5)<5);
    }
}