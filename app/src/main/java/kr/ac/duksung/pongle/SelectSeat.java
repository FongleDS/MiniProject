package kr.ac.duksung.pongle;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class SelectSeat extends Activity {
    Button choice;
    OrderData orderData = new OrderData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);
        choice = findViewById(R.id.select_seat);
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseMenu.class);
                startActivity(intent);

                //자리 정보 넣어주기
                //orderData.setSeatID();
            }
        });
    }
}