package com.example.redlinksantiagogil.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.redlinksantiagogil.R;
import com.example.redlinksantiagogil.model.Image;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter {

    private List<Image> imageList;
    private Context context;

    public ImageAdapter(List<Image> imageList, Context context) {
        this.imageList = imageList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Image image = imageList.get(position);
        viewHolder.id.setText(image.getId().toString());
        viewHolder.title.setText(image.getTitle());
        Glide
                .with(context)
                .load(image.getUrl())
                .into(viewHolder.image);


    }

    @Override
    public int getItemCount() {
        return imageList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;
        private TextView id;
        private TextView title;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            id = itemView.findViewById(R.id.text_view_image_id);
            title = itemView.findViewById(R.id.text_view_image_title);
        }
    }
}
