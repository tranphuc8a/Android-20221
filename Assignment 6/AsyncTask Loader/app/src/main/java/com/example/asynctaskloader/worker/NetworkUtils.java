package com.example.asynctaskloader.worker;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.asynctaskloader.model.Book;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class NetworkUtils {
    private static final String DOMAIN = "https://www.googleapis.com/books/v1/volumes";

    public static ArrayList<Book> getListBook(String keyword, int start_index, int num_element) {
        try {
            String api = DOMAIN + "?q=" + keyword + "&maxResults=" + num_element + "&startIndex=" + start_index;
            InputStream inputStream = getInputStream(api);
            String json_string = readInputStream(inputStream);
            if (json_string == null) throw new Exception("json_string is null");
            JSONObject jsonObject = new JSONObject(json_string);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            ArrayList<Book> bookArrayList = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObjectBook = jsonArray.getJSONObject(i).getJSONObject("volumeInfo");
                bookArrayList.add(new Book(jsonObjectBook));
            }
            return  bookArrayList;
        } catch (Exception e){
            Log.d("Exception", e.getMessage());
        }
        return null;
    }

    public static InputStream getInputStream(String api){
        HttpsURLConnection conn;
        try {
            URL url = new URL(api);
            conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            InputStream inputStream = conn.getInputStream();
            int responseCode = conn.getResponseCode();
            if (responseCode != HttpsURLConnection.HTTP_OK) return null;
            return inputStream;
        } catch (Exception e){
            Log.d("Exception", e.getMessage());
        }
        return null;
    }

    public static String readInputStream(InputStream inputStream) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder res = new StringBuilder();
            String line;
            while (true){
                line = reader.readLine();
                if (line == null) break;
                res.append(line).append("\n");
            }
            reader.close();
            return res.toString();
        } catch (Exception e){
            Log.d("Exception", e.getMessage());
        }
        return null;
    }

    public static Bitmap downloadImage(String link) {
//        link = "https://images.unsplash.com/photo-1503023345310-bd7c1de61c7d?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8aHVtYW58ZW58MHx8MHx8&w=1000&q=80";
        Bitmap mIcon = null;
        try {
            URL url = new URL(link);
            URLConnection conn = url.openConnection();
            conn.setUseCaches(true);
            InputStream inputStream = conn.getInputStream();
            mIcon = BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon;
    }
}
