package com.sky9971.nasapicturesapp.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky9971.nasapicturesapp.R;
import com.sky9971.nasapicturesapp.databinding.FragmentGridBinding;


public class GridFragment extends Fragment {

    private FragmentGridBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGridBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }
}