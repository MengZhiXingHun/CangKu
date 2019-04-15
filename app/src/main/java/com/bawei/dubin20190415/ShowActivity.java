package com.bawei.dubin20190415;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ShowActivity extends AppCompatActivity {

    private ImageView imagePic;
    private TextView tvnick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        imagePic = (ImageView)findViewById(R.id.imagePic);
        tvnick = (TextView)findViewById(R.id.tvnick);

        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
        String headPic = sp.getString("headPic", null);
        String nickName = sp.getString("nickName", "我是昵称");

        tvnick.setText(nickName);
        Glide.with(this).load(headPic).into(imagePic);

    }
}
