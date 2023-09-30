package kr.ac.duksung.pongle;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
public class SelectSeat extends AppCompatActivity {
    Button choice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);
        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdNum = bundle.getString("stdNum");
        String stdID = bundle.getString("stdNum");
        String seatID = bundle.getString("seatNum");

        final ImageView seatButton1 = findViewById(R.id.seat_button_1);
        final ImageView choiceButton1 = findViewById(R.id.choice_button_1);
        final ImageView seatButton2 = findViewById(R.id.seat_button_2);
        final ImageView choiceButton2 = findViewById(R.id.choice_button_2);
        final ImageView seatButton3 = findViewById(R.id.seat_button_3);
        final ImageView choiceButton3 = findViewById(R.id.choice_button_3);
        final ImageView seatButton4 = findViewById(R.id.seat_button_4);
        final ImageView choiceButton4 = findViewById(R.id.choice_button_4);
        final ImageView seatButton5 = findViewById(R.id.seat_button_5);
        final ImageView choiceButton5 = findViewById(R.id.choice_button_5);

        seatButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // seat_button_1이 클릭되었을 때 choice_button_1를 화면에 보이도록 설정
                choiceButton1.setVisibility(View.VISIBLE);
            }
        });
        seatButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // seat_button_2가 클릭되었을 때 choice_button_2를 화면에 보이도록 설정
                choiceButton2.setVisibility(View.VISIBLE);
            }
        });
        seatButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // seat_button_3이 클릭되었을 때 choice_button_3를 화면에 보이도록 설정
                choiceButton3.setVisibility(View.VISIBLE);
            }
        });
        seatButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // seat_button_4가 클릭되었을 때 choice_button_4를 화면에 보이도록 설정
                choiceButton4.setVisibility(View.VISIBLE);
            }
        });
        seatButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // seat_button_5가 클릭되었을 때 choice_button_5를 화면에 보이도록 설정
                choiceButton5.setVisibility(View.VISIBLE);
            }
        });


        choice = findViewById(R.id.select_seat);
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ChooseMenu.class);
                intent.putExtra("seatNum", seatID);
                intent.putExtra("stdNum", stdID);
                startActivity(intent);
                intent.putExtra("stdNum", stdNum);
                // intent.putExtra("seatID", seatNum);
            }
        });
    }
}
