package kr.ac.duksung.pongle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class Pasta extends AppCompatActivity {

    ImageView aliolio;
    ImageView tomato;
    ImageView truffle;
    ImageView daepae;
    ImageView porkimchi;
    ImageView rose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta);

        aliolio = findViewById(R.id.aliolio);
        tomato = findViewById(R.id.tomato);
        truffle = findViewById(R.id.truffle);
        daepae = findViewById(R.id.daepae);
        porkimchi = findViewById(R.id.porkimchi);
        rose = findViewById(R.id.rose);

        aliolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        tomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        truffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        daepae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        porkimchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        rose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });
    }
}