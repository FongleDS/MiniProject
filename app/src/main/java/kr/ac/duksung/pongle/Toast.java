package kr.ac.duksung.pongle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Toast extends AppCompatActivity {

    ImageView americano;
    ImageView latte;
    ImageView vanilla;
    ImageView yeon;
    ImageView hamcheese;
    ImageView baconegg;
    Button goback;
    Button gofront;

    String menuID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);

        americano = findViewById(R.id.americano);
        latte = findViewById(R.id.latte);
        vanilla = findViewById(R.id.vanilla);
        yeon = findViewById(R.id.yeon);
        hamcheese = findViewById(R.id.hamcheese);
        baconegg = findViewById(R.id.baconegg);
        goback = findViewById(R.id.goback);
        gofront = findViewById(R.id.gofront);

        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdID = bundle.getString("stdNum");
        String seatID = bundle.getString("seatNum");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Mara.class);
                startActivity(intent);
            }
        });

        gofront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Ttok.class);
                startActivity(intent);
            }
        });

        americano.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "35";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        latte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "36";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        vanilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "37";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        yeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "38";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        hamcheese.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "39";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        baconegg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "40";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

    }
}