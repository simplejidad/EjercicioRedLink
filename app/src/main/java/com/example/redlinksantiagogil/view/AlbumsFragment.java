package com.example.redlinksantiagogil.view;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.redlinksantiagogil.R;
import com.example.redlinksantiagogil.model.Album;
import com.example.redlinksantiagogil.controller.AlbumAdapter;
import com.example.redlinksantiagogil.service.GetAlbumsDataService;
import com.example.redlinksantiagogil.service.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumsFragment extends Fragment implements AlbumAdapter.ItemClickListener {

    private RecyclerView recyclerView;
    private AlbumAdapter albumAdapter;
    private EditText editTextSearch;
    private FragmentCommunicator fragmentCommunicator;


    public AlbumsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_albums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextSearch = view.findViewById(R.id.editTextSearch);
        recyclerView = view.findViewById(R.id.recyclerView);

        getAlbums();

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addListenerToSearchEditText();

    }

    private void getAlbums() {
        GetAlbumsDataService getAlbumsDataService = RetrofitInstance.getService();

        Call<List<Album>> call =  getAlbumsDataService.getAlbums();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                List<Album> albums = response.body();
                if(albums != null) {
                    albumAdapter = new AlbumAdapter(albums, getContext());
                    albumAdapter.setClickListener(AlbumsFragment.this);
                    recyclerView.setAdapter(albumAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void addListenerToSearchEditText() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String filter = s.toString();
                if (albumAdapter != null) albumAdapter.filterAlbumList(filter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onItemClick(int albumiId) {
        fragmentCommunicator.openAlbumImagesFragment(albumiId);
    }

    public interface FragmentCommunicator {
        void openAlbumImagesFragment(int albumId);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragmentCommunicator = (FragmentCommunicator) context;
    }
}
