package kr.ac.duksung.pongle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class MainPage extends Activity {

    Button btn_seat;
    Button btn_menu;
    Button btn_info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdNum = bundle.getString("stdNum");

        btn_seat = findViewById(R.id.button_seat);
        btn_menu = findViewById(R.id.button_menu);
        btn_info = findViewById(R.id.button_info);


        //PackageManager pm = getPackageManager();
        //pm.setComponentEnabledSetting(new ComponentName(getApplicationContext(), MainActivity.class),
                //PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);


        btn_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectSeat.class);
                intent.putExtra("stdNum", stdNum);
                System.out.println(stdNum);
                startActivity(intent);
            }
        });

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuPan.class);
                intent.putExtra("stdNum", stdNum);
                startActivity(intent);
            }
        });

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CheckInfo.class);
                intent.putExtra("stdNum", stdNum);
                startActivity(intent);
            }
        });

    }
}
