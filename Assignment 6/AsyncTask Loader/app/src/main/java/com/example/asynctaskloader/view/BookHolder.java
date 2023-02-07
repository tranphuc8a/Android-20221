package com.example.asynctaskloader.view;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.loader.app.LoaderManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asynctaskloader.R;
import com.example.asynctaskloader.model.Book;
import com.example.asynctaskloader.worker.BookLoaderCallback;
import com.example.asynctaskloader.worker.ImageDownloadCallback;
import com.squareup.picasso.Picasso;

import java.util.Objects;


public class BookHolder extends RecyclerView.ViewHolder {
    public ImageView thumbnail;
    public TextView title, publishDate, authors, language, categories, pageCount;
    public BookHolder(@NonNull View itemView) {
        super(itemView);
        thumbnail = itemView.findViewById(R.id.thumbnail);
        title = itemView.findViewById(R.id.title);
        publishDate = itemView.findViewById(R.id.publishedDate);
        authors = itemView.findViewById(R.id.authors);
        language = itemView.findViewById(R.id.language);
        categories = itemView.findViewById(R.id.categories);
        pageCount = itemView.findViewById(R.id.pageCount);
    }

    @SuppressLint("SetTextI18n")
    public void updateUI(Book book){
        if (book.thumbnail != null)
//            thumbnail.setImageBitmap(book.thumbnailBitmap);
            Picasso.get().load(book.thumbnail).into(thumbnail);
        title.setText(book.title);
        publishDate.setText(book.publishedDate);
        language.setText(book.language);
        pageCount.setText(book.pageCount + " trang");
        StringBuilder authors_string = new StringBuilder();
        if (!book.authors.isEmpty()){
            for (int i = 0; i < book.authors.size(); i++) {
                authors_string.append(book.authors.get(i));
                if (i + 1 < book.authors.size()) authors_string.append(", ");
            }
        } else {
            authors_string.append("Không có tác giả");
        }
        authors.setText(authors_string.toString());

        StringBuilder categories_string = new StringBuilder();
        if (!book.categories.isEmpty()){
            for (int i = 0; i < book.categories.size(); i++) {
                categories_string.append(book.categories.get(i));
                if (i + 1 < book.categories.size()) categories_string.append(", ");
            }
        } else {
            categories_string.append("Không có thể loại");
        }
        categories.setText(categories_string.toString());
    }

}
