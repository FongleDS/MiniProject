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

public class Ttok extends AppCompatActivity {

    ImageView masungttok;
    ImageView chalsundae;
    ImageView busan;
    ImageView modum;
    ImageView kimbob;
    ImageView chickenmayo;
    String menuID;
    Button goback;
    Button gofront;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ttok);

        masungttok = findViewById(R.id.masungttok);
        chalsundae = findViewById(R.id.chalsundae);
        busan = findViewById(R.id.busan);
        modum = findViewById(R.id.modum);
        kimbob = findViewById(R.id.kimbob);
        chickenmayo = findViewById(R.id.chickenmayo);
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
                Intent intent = new Intent(getApplicationContext(), Gunsan.class);
                startActivity(intent);
            }
        });

        gofront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Toast.class);
                startActivity(intent);
            }
        });

        masungttok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "1";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        chalsundae.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "21";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        busan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "19";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        modum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "27";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        kimbob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "23";
                String Realtime = sdf.format(calendar.getTime());
                Intent intent = new Intent(getApplicationContext(), Basket.class);
                intent.putExtra("menuNum", menuID);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

        chickenmayo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuID = "22";
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