package com.example.android.tictactoeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button threebythree;
    Button fivebyfive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fivebyfive = findViewById(R.id.fivebyfive);
        fivebyfive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, fivebyfive.class);
                startActivity(intent);
            }
        });
        threebythree = findViewById(R.id.threebythree);
        threebythree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, threebythree.class);
                startActivity(intent);

            }
        });
    }
}
