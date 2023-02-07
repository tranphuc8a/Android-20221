package com.example.asynctaskloader.worker;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.example.asynctaskloader.model.Book;

import java.util.ArrayList;

public class BookAsyncTaskLoader extends AsyncTaskLoader<ArrayList<Book>> {
    public int num_element, start_index;
    public String keyword;

    public BookAsyncTaskLoader(@NonNull Context context) {
        super(context);
    }

    public void setParams(int num_element, String keyword, int start_index){
        this.num_element = num_element;
        this.keyword = keyword;
        this.start_index = start_index;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<Book> loadInBackground() {
        return NetworkUtils.getListBook(keyword, start_index, num_element);
    }
}
