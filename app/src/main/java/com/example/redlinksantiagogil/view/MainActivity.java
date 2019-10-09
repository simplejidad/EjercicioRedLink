package com.example.redlinksantiagogil.view;

import android.app.FragmentManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.redlinksantiagogil.R;

public class MainActivity extends AppCompatActivity implements AlbumsFragment.FragmentCommunicator {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        openAlbumsFragment();
    }

    private void openAlbumsFragment() {
        AlbumsFragment albumsFragment = new AlbumsFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentHolder, albumsFragment)
                .commit();
    }

    @Override
    public void openAlbumImagesFragment(int albumId) {
        ImagesFragment imagesFragment = new ImagesFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ImagesFragment.ALBUM_ID, albumId);
        imagesFragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentHolder, imagesFragment)
                .addToBackStack("")
                .commit();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
