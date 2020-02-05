package com.example.objectsmanipulation;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        TextView textView = new TextView(this);

        textView.setText("Wow!!!");
        textView.setTextSize(56);
        textView.setTextColor(Color.RED);

        setContentView(textView);
    }
}
