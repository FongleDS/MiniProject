package kr.ac.duksung.pongle;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
public class SelectSeat extends Activity {
    Button choice;
    Button select_seat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);
        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdNum = bundle.getString("stdNum");
        String stdID = bundle.getString("stdNum");
        String seatID = bundle.getString("seatNum");


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

        select_seat = findViewById(R.id.select_seat);
        select_seat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MenuPan.class);
                startActivity(intent);
            }
        });
    }
}