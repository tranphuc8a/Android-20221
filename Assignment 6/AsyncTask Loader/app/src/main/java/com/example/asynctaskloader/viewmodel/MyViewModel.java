package com.example.asynctaskloader.viewmodel;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.loader.app.LoaderManager;

import com.example.asynctaskloader.model.Book;
import com.example.asynctaskloader.view.MainActivity;
import com.example.asynctaskloader.worker.BookLoaderCallback;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MyViewModel extends ViewModel {
    // attr and constructors
    public MutableLiveData<ArrayList<Book>> listBook;
    public String keyword = "";
    WeakReference<MainActivity> activity;
    public Key loadKey = null;
    public MyViewModel(){
        loadKey = new Key();
        listBook = new MutableLiveData<>(new ArrayList<>());
    }

    // getter and setter
    public Key getLoadKey(){
        return loadKey;
    }
    public void setActivity(MainActivity activity){
        this.activity = new WeakReference<>(activity);
    }
    public ArrayList<Book> getListBook(){
        return listBook.getValue();
    }
    public void append(ArrayList<Book> data){
        ArrayList<Book> list = getListBook();
        list.addAll(data);
    }

    // call get book asyntask
    public void loadBook(String keyword){
        activity.get().binding.progressBar.setVisibility(View.VISIBLE);
        this.keyword = keyword;
        getListBook().clear();
        loadMore(20);
    }
    public void loadMore(){
        loadMore(10);
    }
    public void loadMore(int num_element){
        if (loadKey.isLock()) return;
        loadKey.lock();

        LoaderManager provider = LoaderManager.getInstance(this.activity.get());
        BookLoaderCallback bookLoaderCallback = new BookLoaderCallback(this.activity.get());
        bookLoaderCallback.setParams(num_element, this.keyword, getListBook().size());
        if (provider.getLoader(0) == null){
            provider.initLoader(0, null, bookLoaderCallback);
        } else {
            provider.restartLoader(0, null, bookLoaderCallback);
        }
    }

}
