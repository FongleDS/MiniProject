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

    // 각각의 seatButton과 choiceButton를 배열로 관리하기 위한 배열 선언
    ImageView[] seatButtons = new ImageView[5];
    ImageView[] choiceButtons = new ImageView[5];
    String seatID;

    // 각 choiceButton의 상태를 나타내는 변수
    boolean[] choiceButtonStates = new boolean[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);

        // Intent로 전달된 데이터 받기
        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdID = bundle.getString("stdNum");

        // XML 레이아웃에서 ImageView들을 배열에 할당
        seatButtons[0] = findViewById(R.id.seat_button_1);
        seatButtons[1] = findViewById(R.id.seat_button_2);
        seatButtons[2] = findViewById(R.id.seat_button_3);
        seatButtons[3] = findViewById(R.id.seat_button_4);
        seatButtons[4] = findViewById(R.id.seat_button_5);

        choiceButtons[0] = findViewById(R.id.choice_button_1);
        choiceButtons[1] = findViewById(R.id.choice_button_2);
        choiceButtons[2] = findViewById(R.id.choice_button_3);
        choiceButtons[3] = findViewById(R.id.choice_button_4);
        choiceButtons[4] = findViewById(R.id.choice_button_5);

        // seatButtons 배열에 대한 클릭 이벤트 리스너 설정
        for (int i = 0; i < seatButtons.length; i++) {
            final int index = i;
            seatButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 해당 seatButton이 클릭되었을 때 해당 choiceButton을 화면에 보이도록 설정
                    choiceButtons[index].setVisibility(View.VISIBLE);

                    // 상태를 토글하고 바로 색상을 변경
                    choiceButtonStates[index] = !choiceButtonStates[index];
                    if (choiceButtonStates[index]) {
                        choiceButtons[index].setBackgroundResource(R.drawable.red_seat_sero); // 빨간색으로 변경
                        seatID = String.valueOf(index);
                        System.out.println(seatID);
                    } else {
                        choiceButtons[index].setBackgroundResource(R.drawable.green_seat_sero); // 초록색으로 변경
                    }
                }
            });
        }

        choice = findViewById(R.id.select_seat);
        choice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ChooseMenu 액티비티로 데이터 전달
                Intent intent = new Intent(getApplicationContext(), ChooseMenu.class);
                intent.putExtra("seatNum", seatID);
                System.out.println(seatID);
                intent.putExtra("stdNum", stdID);
                System.out.println(stdID);
                startActivity(intent);
            }
        });
    }
}