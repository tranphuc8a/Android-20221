package com.example.asynctaskloader.model;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.loader.app.LoaderManager;

import com.example.asynctaskloader.view.MainActivity;
import com.example.asynctaskloader.worker.ImageDownloadCallback;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Book {
    public String title;
    public String publishedDate;
    public String pageCount;
    public String printType;
    public ArrayList <String> authors;
    public ArrayList <String> categories;
    public String contentVersion;
    public String smallThumbnail;
    public String thumbnail;
    public String language;
    public String previewLink;
    public String infoLink;
    public String canonicalVolumeLink;
    public Bitmap thumbnailBitmap;

    public Book(JSONObject json){
        this.from_json(json);
    }
    public void from_json(JSONObject json){
        if (json == null) return;
        try {
            if (json.has("title"))
                this.title = json.getString("title");
            if (json.has("publishedDate"))
                this.publishedDate = json.getString("publishedDate");
            if (json.has("pageCount"))
                this.pageCount = json.getString("pageCount");
            if (json.has("printType"))
                this.printType = json.getString("printType");
            if (json.has("contentVersion"))
                this.contentVersion = json.getString("contentVersion");
            if (json.has("imageLinks")){
                JSONObject imageLinks = json.getJSONObject("imageLinks");
                if (imageLinks.has("smallThumbnail"))
                    this.smallThumbnail = imageLinks.getString("smallThumbnail");
                if (imageLinks.has("thumbnail"))
                    this.thumbnail = imageLinks.getString("thumbnail");
            }
            if (json.has("language"))
                this.language = json.getString("language");
            if (json.has("previewLink"))
                this.previewLink = json.getString("previewLink");
            if (json.has("infoLink"))
                this.infoLink = json.getString("infoLink");
            if (json.has("canonicalVolumeLink"))
                this.canonicalVolumeLink = json.getString("canonicalVolumeLink");
            if (authors == null) authors = new ArrayList<>();
            if (categories == null) categories = new ArrayList<>();
            authors.clear();
            categories.clear();
            if (json.has("authors")) {
                JSONArray author_array = json.getJSONArray("authors");
                for (int i = 0; i < author_array.length(); i++) {
                    authors.add(author_array.getString(i));
                }
            }
            if (json.has("categories")){
                JSONArray categories_array = json.getJSONArray("categories");
                for (int i = 0; i < categories_array.length(); i++){
                    categories.add(categories_array.getString(i));
                }
            }
            checkNull();
        } catch (Exception e){
            Log.d("from_json_exception", e.getMessage());
        }
    }

    public void checkNull(){
        if (title == null) title = "null";
        if (publishedDate == null) publishedDate = "null";
        if (printType == null) printType = "null";
        if (contentVersion == null) contentVersion = "null";
        if (smallThumbnail == null) smallThumbnail = "null";
        if (language == null) language = "null";
    }
}
