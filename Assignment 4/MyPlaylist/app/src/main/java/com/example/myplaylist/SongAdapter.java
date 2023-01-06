package com.example.myplaylist;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.SongViewHolder> {
    ArrayList<SongModel> listSongModel = null;
    MainActivity mainActivity = null;

    public SongAdapter(@NonNull ArrayList<SongModel> listSongModel,@NonNull MainActivity mainActivity) {
        this.listSongModel = listSongModel;
        this.mainActivity = mainActivity;
    }

    @NonNull
    @Override
    public SongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mainActivity.getLayoutInflater().inflate(R.layout.item_song, null);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongViewHolder holder, int position) {
        SongModel songModel = listSongModel.get(position);
//        if(songModel.getSongThumnail() != null && !songModel.getSongThumnail().isEmpty())
            holder.songThumnail.setImageResource(songModel.getSongThumnail());
        holder.songName.setText(songModel.getSongName());
        holder.songSinger.setText(songModel.getSongSinger());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(android.content.Intent.ACTION_VIEW);
                //todo: cần phải set link bài hát vào đây
                intent.setDataAndType(Uri.parse("https://zingmp3.vn/bai-hat/Hay-Trao-Cho-Anh-Aki-Khoa/ZWADF9BB.html"), "audio/*");
                mainActivity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listSongModel.size();
    }

    class SongViewHolder extends RecyclerView.ViewHolder{
        ImageView songThumnail = null;
        TextView songName = null, songSinger = null;

        public SongViewHolder(@NonNull View itemView) {
            super(itemView);
            songThumnail = itemView.findViewById(R.id.imgSong);
            songName = itemView.findViewById(R.id.tvSongName);
            songSinger = itemView.findViewById(R.id.tvSongSinger);
        }
    }
}


