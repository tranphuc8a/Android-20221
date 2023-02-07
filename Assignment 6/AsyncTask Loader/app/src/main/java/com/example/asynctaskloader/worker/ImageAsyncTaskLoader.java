package com.example.asynctaskloader.worker;

import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class ImageAsyncTaskLoader extends AsyncTaskLoader<Bitmap> {
    public String link;
    public ImageAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }
    public ImageAsyncTaskLoader(@NonNull Context context, String link){
        this(context);
        this.link = link;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public Bitmap loadInBackground() {
        return NetworkUtils.downloadImage(link);
    }
}
