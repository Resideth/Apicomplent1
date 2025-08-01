package com.maikol.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    private Button buttonGetPosts;
    private Button buttonCreatePost;
    private Button buttonPutPost;
    private Button buttonDeletePost; // Declara el nuevo botón

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        buttonGetPosts = findViewById(R.id.buttonGetPosts);
        buttonCreatePost = findViewById(R.id.buttonCreatePost);
        buttonPutPost = findViewById(R.id.buttonPutPost);
        buttonDeletePost = findViewById(R.id.buttonDeletePost); // Asocia el nuevo botón

        // Listener para el botón GET
        buttonGetPosts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Listener para el botón POST
        buttonCreatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, PostActivity.class);
                startActivity(intent);
            }
        });

        // Listener para el botón PUT
        buttonPutPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, PutActivity.class);
                startActivity(intent);
            }
        });

        // Listener para el botón DELETE
        buttonDeletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, DeleteActivity.class);
                startActivity(intent);
            }
        });
    }
}