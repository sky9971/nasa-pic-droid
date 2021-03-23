package com.sky9971.nasapicturesapp.View.Adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sky9971.nasapicturesapp.Model.PictureModel;
import com.sky9971.nasapicturesapp.R;

import java.util.ArrayList;

public class PictureItemAdapter extends RecyclerView.Adapter<PictureItemViewHolder>{
    private ArrayList<PictureModel> list;
    private Context context;

    public PictureItemAdapter(Context ctx,ArrayList<PictureModel> List){
        context = ctx;
        list = List;
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
        holder.binding.imageView.setImageURI(Uri.parse(model.getUrl()));
        holder.binding.pictureTitle.setText(model.getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
