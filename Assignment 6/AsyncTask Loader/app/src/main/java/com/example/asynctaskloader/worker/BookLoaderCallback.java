package com.example.asynctaskloader.worker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import com.example.asynctaskloader.model.Book;
import com.example.asynctaskloader.view.MainActivity;
import com.example.asynctaskloader.viewmodel.Key;

import java.lang.ref.WeakReference;
import java.util.ArrayList;



public class BookLoaderCallback implements LoaderManager.LoaderCallbacks<ArrayList<Book>> {
    WeakReference<MainActivity> activity;
    public int num_element = 0, start_index = 0;
    public String keyword = "";
    private final Key loadKey;

    public BookLoaderCallback(MainActivity _activity){
        activity = new WeakReference<>(_activity);
        loadKey = _activity.viewModel.getLoadKey();
    }

    public void setParams(int num_element, String keyword, int start_index){
        this.num_element = num_element;
        this.keyword = keyword;
        this.start_index = start_index;
    }

    @NonNull
    @Override
    public BookAsyncTaskLoader onCreateLoader(int id, @Nullable Bundle args) {
        BookAsyncTaskLoader myAsyncTaskLoader = new BookAsyncTaskLoader(this.activity.get());
        myAsyncTaskLoader.setParams(num_element, keyword, start_index);
        return myAsyncTaskLoader;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Book>> loader, ArrayList<Book> data) {
        if (data == null) return;
        loadKey.unlock();
        this.activity.get().viewModel.append(data);
        this.activity.get().bookAdapter.notifyDataSetChanged();
        activity.get().binding.progressBar.setVisibility(View.INVISIBLE);

//        ImageDownloadCallback imageDownloadCallback = new ImageDownloadCallback(activity.get());
//        LoaderManager provider = LoaderManager.getInstance(activity.get());
//        for (int i = 0; i < data.size(); i++){
//            Book book = data.get(i);
//            if (book.thumbnail == null) continue;
//            imageDownloadCallback.setParams(book);
//            if (provider.getLoader(0) == null) {
//                provider.initLoader(0, null, imageDownloadCallback);
//            } else {
//                provider.restartLoader(0, null, imageDownloadCallback);
//            }
//        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Book>> loader) {}
}
