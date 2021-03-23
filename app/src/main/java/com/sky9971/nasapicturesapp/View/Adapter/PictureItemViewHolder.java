package com.sky9971.nasapicturesapp.View.Adapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.sky9971.nasapicturesapp.databinding.PictureItemBinding;

class PictureItemViewHolder extends RecyclerView.ViewHolder {
    public PictureItemBinding binding;

    public PictureItemViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = PictureItemBinding.bind(itemView);
    }
}
