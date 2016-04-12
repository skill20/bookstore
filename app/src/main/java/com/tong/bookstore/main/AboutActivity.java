package com.tong.bookstore.main;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.tong.bookstore.BookStoreApplication;
import com.tong.bookstore.R;
import com.tong.bookstore.util.Constants;

import java.util.HashMap;

public class AboutActivity extends AppCompatActivity {

    private TextView authorText;
    private TextView verNameText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        findViewByIds();
        init();
        
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
        toolBar.setTitleTextColor(Color.WHITE);
        toolBar.setNavigationIcon(R.drawable.ic_arrow_back_white);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void init() {
        HashMap<String, String> hashMap = ((BookStoreApplication) getApplicationContext()).getHashMap();
        if (hashMap != null) {
            String s = hashMap.get(Constants.AUTHOR);
            if (!TextUtils.isEmpty(s)) {
                authorText.setText(s);
            }

            String s1 = hashMap.get(Constants.VERSION);
            if (!TextUtils.isEmpty(s1)) {
                verNameText.setText(s1);
            }
        }
    }

    private void findViewByIds() {
        authorText = (TextView) findViewById(R.id.author_text);
        verNameText = (TextView) findViewById(R.id.version_name_text);
    }
}
