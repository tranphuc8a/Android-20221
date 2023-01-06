package com.example.myplaylist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvPlaylist = null;
    SongAdapter songAdapter = null;
    ArrayList<SongModel> listSongModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvPlaylist = findViewById(R.id.rvPlaylist);

        listSongModel = new ArrayList<SongModel>();
        for(int i=0; i<100; i++){
            listSongModel.add(new SongModel(R.drawable.song_thumnail, "Hãy trao cho anh " + i, "Sơn Tùng MTP"));
        }
        songAdapter = new SongAdapter(listSongModel, this);
        rvPlaylist.setAdapter(songAdapter);
        rvPlaylist.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }
}