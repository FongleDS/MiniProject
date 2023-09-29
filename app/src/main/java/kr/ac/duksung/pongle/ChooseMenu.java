package kr.ac.duksung.pongle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChooseMenu extends AppCompatActivity {

    OrderData orderData = new OrderData();
    Button btn_choose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_menu);
        btn_choose = findViewById(R.id.btn_choose);

        // 실시간 현재 시간 받아오기
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String Realtime = sdf.format(calendar.getTime());

        //메뉴 정보 넣기
        //orderData.setMenuID();

        btn_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckInfo.class);
                startActivity(intent);
            }
        });
    }
}