package com.sky9971.nasapicturesapp.View.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sky9971.nasapicturesapp.Model.PictureModel;
import com.sky9971.nasapicturesapp.R;
import com.sky9971.nasapicturesapp.View.Adapter.PictureItemAdapter;
import com.sky9971.nasapicturesapp.databinding.FragmentGridBinding;

import java.util.ArrayList;


public class GridFragment extends Fragment {

    private FragmentGridBinding binding;
    private PictureItemAdapter adapter;
    private ArrayList<PictureModel> models;
    private GridLayoutManager manager;
    private Parcelable state;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGridBinding.inflate(inflater,container,false);
        models = new ArrayList<>();
        if(savedInstanceState!=null){
            state = savedInstanceState.getParcelable("list");
        }
        manager = new GridLayoutManager(getContext(),2);
        if(state != null){
            manager.onRestoreInstanceState(state);
        }
        adapter = new PictureItemAdapter(getContext(),models);
        binding.list.setLayoutManager(manager);
        binding.list.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        if(manager!=null) {
            state = manager.onSaveInstanceState();
            outState.putParcelable("list", state);
        }
    }
}