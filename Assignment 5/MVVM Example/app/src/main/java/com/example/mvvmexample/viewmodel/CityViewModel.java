package com.example.mvvmexample.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmexample.R;
import com.example.mvvmexample.model.City;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class CityViewModel extends ViewModel {
    private MutableLiveData<City> city;
    private final ArrayList<City> listCity;
    private final int length;
    private int index = 0;

    public CityViewModel(){
        listCity = new ArrayList<>();
        listCity.add(new City(R.drawable.bangkok,   "Băng Cốc",     "1.000.000"));
        listCity.add(new City(R.drawable.beijing,   "Bắc Kinh",     "2.000.000"));
        listCity.add(new City(R.drawable.london,    "Luân Đôn",     "3.000.000"));
        listCity.add(new City(R.drawable.newyork,   "New York",     "4.000.000"));
        listCity.add(new City(R.drawable.paris,     "Pari",         "5.000.000"));
        listCity.add(new City(R.drawable.rio,       "Rio",          "6.000.000"));
        listCity.add(new City(R.drawable.rome,      "Rome",         "7.000.000"));
        listCity.add(new City(R.drawable.singapore, "Singapore",    "8.000.000"));
        listCity.add(new City(R.drawable.sydney,    "Sydney",       "9.000.000"));
        listCity.add(new City(R.drawable.tokyo,     "Tokyo",        "10.000.000"));

        city = new MutableLiveData<>();
        city.setValue(listCity.get(index));
        length = listCity.size();
    }

    public MutableLiveData<City> getCity(){
        int newIndex;
        do newIndex = (int) Math.floor(Math.random() * length);
        while (newIndex == index);
        index = newIndex;
        if (city == null) {
            city = new MutableLiveData<>();
        }
        City city2 = listCity.get(index);
        city.postValue(city2);
        System.out.println("OKay");
        return city;
    }

    public void loop() {
        int delay = 3;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                getCity();
            }
        }, 0, delay * 1000L);
    }
}
