package com.example.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btn = null;
    EditText editText = null;
    TextView textView = null;
    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btn);
        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);

        if (savedInstanceState != null && savedInstanceState.containsKey("number")){
            this.number = savedInstanceState.getInt("number");
        }

        textView.setText(String.valueOf(number));

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                number ++;
                textView.setText(String.valueOf(number));
            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("number", number);
    }
}