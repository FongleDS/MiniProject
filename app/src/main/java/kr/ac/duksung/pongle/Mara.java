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

public class Mara extends AppCompatActivity {
    ImageView maratang;
    ImageView gguo_s;
    ImageView gguo_l;
    ImageView shang;
    Button goback;

    String menuID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mara);

        maratang = findViewById(R.id.maratang);
        gguo_s = findViewById(R.id.gguo_s);
        gguo_l = findViewById(R.id.gguo_l);
        shang = findViewById(R.id.shang);
        goback = findViewById(R.id.goback);

        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdID = bundle.getString("stdNum");
        String seatID = bundle.getString("seatNum");

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Toast.class);
                startActivity(intent);
            }
        });

        maratang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "31";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        gguo_s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "32";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        gguo_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "33";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        shang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "34";
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