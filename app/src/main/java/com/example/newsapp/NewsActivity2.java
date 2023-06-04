package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class NewsActivity2 extends AppCompatActivity {
    private TextView title;
    private TextView author;
    private TextView publised;
    private TextView content;
    private ImageView pics;
    String titlee,authorr,publishedd,contentt,picss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news2);
        title= findViewById(R.id.title);
        author= findViewById(R.id.author);
        publised= findViewById(R.id.published);
        pics= findViewById(R.id.picture);
        content=findViewById(R.id.content);
        titlee=getIntent().getStringExtra("title");
        authorr=getIntent().getStringExtra("author");
        publishedd=getIntent().getStringExtra("published");
        contentt= getIntent().getStringExtra("content");
        picss= getIntent().getStringExtra("url");
        title.setText(titlee);
        author.setText(authorr);
        publised.setText(publishedd);
        content.setText(contentt);
        Picasso.get().load(picss).into(pics);

    }
}