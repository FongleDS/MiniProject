package kr.ac.duksung.pongle;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MenuPan extends Activity {
    Button btn_han;
    Button btn_joong;
    Button btn_il;
    Button btn_yang;
    Button btn_bun;

    TextView han_waiting;
    TextView joong_waiting;
    TextView il_waiting;
    TextView yang_waiting;
    TextView bun_waiting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pan);
        btn_han = findViewById(R.id.button_korean);
        btn_joong = findViewById(R.id.button_china);
        btn_il = findViewById(R.id.button_japan);
        btn_yang = findViewById(R.id.button_western);
        btn_bun = findViewById(R.id.button_snack);

        han_waiting = findViewById(R.id.text_han);
        joong_waiting = findViewById(R.id.text_joong);
        il_waiting = findViewById(R.id.text_il);
        yang_waiting = findViewById(R.id.text_yang);
        bun_waiting = findViewById(R.id.text_bun);

        btn_han.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KFood.class);
                startActivity(intent);
            }
        });

        btn_joong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CFood.class);
                startActivity(intent);
            }
        });

        btn_il.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JFood.class);
                startActivity(intent);
            }
        });

        btn_yang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WFood.class);
                startActivity(intent);
            }
        });

        btn_bun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Bunsik.class);
                startActivity(intent);
            }
        });

    }
}