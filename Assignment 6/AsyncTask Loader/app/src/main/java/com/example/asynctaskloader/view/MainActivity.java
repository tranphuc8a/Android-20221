package com.example.asynctaskloader.view;

import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.example.asynctaskloader.viewmodel.MyViewModel;
import com.example.asynctaskloader.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding binding = null;
    public MyViewModel viewModel = null;
    public BookAdapter bookAdapter = null;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // create binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // create viewmodel
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.setActivity(this);

        // create adapter
        bookAdapter = new BookAdapter(viewModel);
        binding.recyclerView.setAdapter(bookAdapter);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // set onClick for Button
        binding.button.setOnClickListener(view -> {
            String keyword = String.valueOf(binding.editText.getText());
            if (keyword.isEmpty()) return;
            viewModel.loadBook(keyword);
        });

        // set for hide keyboard when tap outside keyboard
        binding.recyclerView.setOnTouchListener((v, event) -> {
            @SuppressLint("ClickableViewAccessibility") InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return false;
        });
    }

}