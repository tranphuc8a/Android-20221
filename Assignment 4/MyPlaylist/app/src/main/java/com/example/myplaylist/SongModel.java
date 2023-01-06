package com.example.myplaylist;

public class SongModel {
    public int songThumnail;
    public String songName;
    public String songSinger;
    public String songLink = "";

    public SongModel(int songThumnail, String songName, String songSinger) {
        this.songThumnail = songThumnail;
        this.songName = songName;
        this.songSinger = songSinger;
    }

    public int getSongThumnail() {
        return songThumnail;
    }

    public void setSongThumnail(int songThumnail) {
        this.songThumnail = songThumnail;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongSinger() {
        return songSinger;
    }

    public void setSongSinger(String songSinger) {
        this.songSinger = songSinger;
    }

    public String getSongLink() {
        return songLink;
    }

    public void setSongLink(String songLink) {
        this.songLink = songLink;
    }
}
