package com.example.redlinksantiagogil.view;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.redlinksantiagogil.R;
import com.example.redlinksantiagogil.controller.ImageAdapter;
import com.example.redlinksantiagogil.model.Image;
import com.example.redlinksantiagogil.service.GetAlbumsDataService;
import com.example.redlinksantiagogil.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImagesFragment extends Fragment {

    public static final String ALBUM_ID = "album id";

    private RecyclerView recyclerView;
    private ImageAdapter imageAdapter;

    private List<Image> filteredImages = new ArrayList<>();

    public ImagesFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_images, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int albumId = getArguments().getInt(ALBUM_ID);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        getAlbumImages(albumId);
    }

    private void getAlbumImages(final int albumId) {
        GetAlbumsDataService getAlbumsDataService = RetrofitInstance.getService();

        Call<List<Image>> call =  getAlbumsDataService.getAlbumImages();

        call.enqueue(new Callback<List<Image>>() {
            @Override
            public void onResponse(Call<List<Image>> call, Response<List<Image>> response) {
                List<Image> images = response.body();

                for (Image image : images) {
                    if (image.getAlbumId() == albumId)
                        filteredImages.add(image);
                }

                imageAdapter = new ImageAdapter(filteredImages, getContext());
                recyclerView.setAdapter(imageAdapter);
            }

            @Override
            public void onFailure(Call<List<Image>> call, Throwable t) {

            }
        });
    }
}
