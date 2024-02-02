package com.mcas2.roomcodelab.picturerecycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mcas2.roomcodelab.R;
import com.mcas2.roomcodelab.entities.Picture;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.PictureViewHolder> {
    private Context context;
    private List<Picture> pictures;

    public PictureAdapter(Context context, List<Picture> pictures) {
        this.context = context;
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public PictureAdapter.PictureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PictureViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pic,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull PictureViewHolder holder, int position) {
        Picture picture=pictures.get(position);
        Picasso.get().load(picture.getUrl()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return pictures.size();
    }

    public void getAllDatas(List<Picture> pictures)
    {
        this.pictures=pictures;
    }

    public static class PictureViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView image;

        public PictureViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.itempic);
        }
    }
}