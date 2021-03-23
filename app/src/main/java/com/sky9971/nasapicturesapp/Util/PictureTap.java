package com.sky9971.nasapicturesapp.Util;

import android.view.View;
import android.widget.TextView;

import androidx.navigation.fragment.FragmentNavigator;

import com.facebook.drawee.view.SimpleDraweeView;

public interface PictureTap {
    void PictureClick(int position, FragmentNavigator.Extras extras);
}
