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
    String menuID;

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


        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdID = bundle.getString("stdNum");
        String seatID = bundle.getString("seatNum");

        aliolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "11";

                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                startActivity(intent);
            }
        });

        tomato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "12";

                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                startActivity(intent);
            }
        });

        truffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "13";

                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                startActivity(intent);
            }
        });

        daepae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "14";

                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                startActivity(intent);
            }
        });

        rose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "15";

                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                startActivity(intent);
            }
        });

        porkimchi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "17";

                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                startActivity(intent);
            }
        });
    }
}