package kr.ac.duksung.pongle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainPage extends Activity {

    Button btn_menu, btn_info, btn_seat;
    TextView Date, Name, leftSeat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdNum = bundle.getString("stdNum");
        String stdName = bundle.getString("stdName");

        btn_seat = findViewById(R.id.button_seat);
        btn_menu = findViewById(R.id.button_menu);
        btn_info = findViewById(R.id.button_info);
        Date = findViewById(R.id.page_date);
        Name = findViewById(R.id.Name);
        leftSeat = findViewById(R.id.leftseat);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String Realtime = sdf.format(calendar.getTime());

        LeftSeat();

        String[] onlyDate = Realtime.split(" ");
        onlyDate = onlyDate[0].split("-");
        String finalDate = onlyDate[0] + "년 " + onlyDate[1] + "월 " + onlyDate[2] + "일";
        Date.setText(finalDate);
        Name.setText(stdName);



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

    OkHttpClient client = new OkHttpClient();
    public void LeftSeat() {
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/countSeat")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String responseBody = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        System.out.println(jsonObject);
                        String leftseats = jsonObject.getString("leftseat");
                        runOnUiThread(() -> {
                            leftSeat.setText("현재 남은 좌석 : " + leftseats + "석");
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
