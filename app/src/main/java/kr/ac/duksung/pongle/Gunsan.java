package kr.ac.duksung.pongle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

public class Gunsan extends AppCompatActivity {

    ImageView don;
    ImageView doncurry;
    ImageView shrimpcurry;
    ImageView doubledon;
    ImageView curry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gunsan);

        don = findViewById(R.id.don);
        doncurry = findViewById(R.id.doncurry);
        shrimpcurry = findViewById(R.id.shrimpcurry);
        doubledon = findViewById(R.id.doubledon);
        curry = findViewById(R.id.curry);

        don.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        doncurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        shrimpcurry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        doubledon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });

        curry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                startActivity(intent);
            }
        });
    }
}