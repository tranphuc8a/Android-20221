package com.example.asynctaskloader.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asynctaskloader.viewmodel.MyViewModel;
import com.example.asynctaskloader.R;
import com.example.asynctaskloader.model.Book;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookHolder>{
    public ArrayList<Book> listBook;
    public MyViewModel viewModel;
    public BookAdapter(MyViewModel viewModel){
        this.listBook = viewModel.getListBook();
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        if (getItemCount() - position < 10) {
            viewModel.loadMore();
        }
        holder.updateUI(listBook.get(position));
    }

    @Override
    public int getItemCount() {
        return listBook.size();
    }
}