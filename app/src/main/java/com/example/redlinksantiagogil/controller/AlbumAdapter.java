package com.example.redlinksantiagogil.controller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.redlinksantiagogil.R;
import com.example.redlinksantiagogil.model.Album;

import java.util.ArrayList;
import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter {

    private List<Album> currentAlbumList;
    private List<Album> fullAlbumsList;
    private Context context;
    private ItemClickListener itemClickListener;

    public AlbumAdapter(List<Album> currentAlbumList, Context context) {
        this.currentAlbumList = currentAlbumList;
        this.fullAlbumsList = currentAlbumList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.album_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.albumID.setText(currentAlbumList.get(position).getId().toString());
        viewHolder.albumTitle.setText(currentAlbumList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return currentAlbumList.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView albumID;
        private TextView albumTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            albumID = itemView.findViewById(R.id.text_view_album_id);
            albumTitle = itemView.findViewById(R.id.text_view_album_title);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
                itemClickListener.onItemClick(currentAlbumList.get(getAdapterPosition()).getId());
        }
    }

    public void filterAlbumList(String filter) {
        if (filter.equals("")) {
            currentAlbumList = fullAlbumsList;
        } else {
            List<Album> filteredList = new ArrayList<>();
            for (Album album : fullAlbumsList) {
                if (album.getTitle().contains(filter)) {
                    filteredList.add(album);
                }
            }
            currentAlbumList = filteredList;
        }
        notifyDataSetChanged();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(int itemId);
    }
}
