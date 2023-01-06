package com.example.mvvmexample.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.example.mvvmexample.databinding.ActivityMainBinding;
import com.example.mvvmexample.model.City;
import com.example.mvvmexample.viewmodel.CityViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding = null;
    CityViewModel cityViewModel = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.root);

        cityViewModel = new ViewModelProvider(this).get(CityViewModel.class);

        @SuppressLint("SetTextI18n") Observer<City> observer = city -> {
            binding.avatar.setImageResource(city.avatar);
            binding.name.setText(city.name);
            binding.population.setText("Population: " + city.population);
        };

        cityViewModel.getCity().observe(this, observer);
        cityViewModel.loop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}