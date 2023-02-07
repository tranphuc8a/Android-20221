package com.example.asynctaskloader.worker;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;


import com.example.asynctaskloader.model.Book;
import com.example.asynctaskloader.view.MainActivity;

import java.lang.ref.WeakReference;


public class ImageDownloadCallback implements LoaderManager.LoaderCallbacks<Bitmap> {
    WeakReference<Book> book;
    WeakReference<MainActivity> activity;

    public ImageDownloadCallback(MainActivity activity){
        this.activity = new WeakReference<>(activity);
    }

    public void setParams(Book book){
        this.book = new WeakReference<>(book);
    }

    @NonNull
    @Override
    public Loader<Bitmap> onCreateLoader(int id, @Nullable Bundle args) {
        return new ImageAsyncTaskLoader(activity.get(), book.get().thumbnail);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onLoadFinished(@NonNull Loader<Bitmap> loader, Bitmap data) {
        if (data == null) return;
        book.get().thumbnailBitmap = data;
        activity.get().bookAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Bitmap> loader) {
        // todo
    }
}
