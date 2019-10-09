package com.example.redlinksantiagogil.service;

import com.example.redlinksantiagogil.model.Album;
import com.example.redlinksantiagogil.model.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetAlbumsDataService {

    @GET("users/1/albums")
    Call<List<Album>> getAlbums();

    @GET("albums/1/photos")
    Call<List<Image>> getAlbumImages();

}
