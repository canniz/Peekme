package com.giuseppe.layouts;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.start_login_activity);
        Glide
                .with(this)
                .load(getIntent().getData())
                .placeholder(R.drawable.panorama)
                .centerCrop()
                .into(((ImageView) findViewById(R.id.backgroundLoginImage)));
    }
}
