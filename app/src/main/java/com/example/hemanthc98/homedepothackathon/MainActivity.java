package com.example.hemanthc98.homedepothackathon;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public View onLend(View view) {
        // Redirect to lend page
        return view;
    }

    public View onBorrow(View view) {
        // Redirect to borrow page
        return view;
    }
}
