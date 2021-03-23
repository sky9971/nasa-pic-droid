package com.sky9971.nasapicturesapp.View.Adapter;

import android.content.Context;
import android.graphics.ColorFilter;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.drawable.ProgressBarDrawable;
import com.sky9971.nasapicturesapp.Model.PictureModel;
import com.sky9971.nasapicturesapp.R;
import com.sky9971.nasapicturesapp.Util.PictureTap;

import java.util.ArrayList;

public class PictureItemAdapter extends RecyclerView.Adapter<PictureItemViewHolder>{
    private ArrayList<PictureModel> list;
    private Context context;
    private PictureTap tap;

    public PictureItemAdapter(Context ctx,ArrayList<PictureModel> List,PictureTap Tap){
        context = ctx;
        list = List;
        tap = Tap;
    }

    @NonNull
    @Override
    public PictureItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.picture_item,parent,false);
        return new PictureItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PictureItemViewHolder holder, int position) {
        PictureModel model = list.get(position);
        holder.binding.pictureTitle.setText(model.getTitle());
        holder.binding.imageView.setImageURI(Uri.parse(model.getUrl()));
        holder.binding.imageView.getHierarchy().setFailureImage(context.getDrawable(R.drawable.ic_baseline_error_outline_24));
        holder.binding.imageView.getHierarchy().setRetryImage(context.getDrawable(R.drawable.ic_baseline_refresh_24));
        holder.binding.imageView.getHierarchy().setProgressBarImage(new ProgressBarDrawable());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tap.PictureClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
