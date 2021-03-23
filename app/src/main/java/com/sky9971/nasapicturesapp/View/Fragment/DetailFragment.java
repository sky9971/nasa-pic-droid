package com.sky9971.nasapicturesapp.View.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.sky9971.nasapicturesapp.Model.PictureModel;
import com.sky9971.nasapicturesapp.R;
import com.sky9971.nasapicturesapp.Util.SwipeTouchListener;
import com.sky9971.nasapicturesapp.Util.onSwipeCallback;
import com.sky9971.nasapicturesapp.ViewModel.PictureViewModel;
import com.sky9971.nasapicturesapp.databinding.FragmentDetailBinding;


public class DetailFragment extends Fragment implements onSwipeCallback {

    private FragmentDetailBinding binding;
    private PictureViewModel viewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(PictureViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater,container,false);
        viewModel.getPictureData().observe(getViewLifecycleOwner(), new Observer<PictureModel>() {
            @Override
            public void onChanged(PictureModel pictureModel) {
                binding.title.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setData(pictureModel);
                        binding.invalidateAll();
                    }
                },500);
            }
        });
        setData(viewModel.getPictureData().getValue());
        binding.getRoot().setOnTouchListener(new SwipeTouchListener(getContext(),this));
        return binding.getRoot();
    }

    private void setData(PictureModel model){
        String copyright = model.getCopyright()+" ©";
        if(model.getCopyright().contains("Not Available")){
            copyright = "Not Available";
        }
        String media = "Type: "+model.getMediaType();
        String date = "Date: "+model.getDate();
        String service = "Service Version: "+model.getServiceVersion();
        binding.title.setText(model.getTitle());
        binding.image.setImageURI(model.getImage());
        binding.image.getHierarchy().setFailureImage(getContext().getDrawable(R.drawable.ic_baseline_error_outline_24));
        binding.image.getHierarchy().setRetryImage(getContext().getDrawable(R.drawable.ic_baseline_refresh_24));
        binding.image.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
        binding.description.setText(model.getExplanation());
        binding.copyright.setText(copyright);
        binding.date.setText(date);
        binding.mediaType.setText(media);
        binding.serviceVersion.setText(service);
    }

    @Override
    public void onRightSwipe() {
        viewModel.swipeRight();
    }

    @Override
    public void onLeftSwipe() {
        viewModel.swipeLeft();
    }
}