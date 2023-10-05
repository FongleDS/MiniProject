package kr.ac.duksung.pongle;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;


public class MainPage extends Activity {

    Button btn_menu, btn_info, btn_seat;
    TextView Date, Name, leftSeat, waiting;
    String stdNum, stdName, orderID;
    Socket mSocket;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        if (bundle != null) {
            orderID = bundle.getString("orderID");
            stdNum = bundle.getString("stdNum");
        }

        btn_seat = findViewById(R.id.button_seat);
        btn_menu = findViewById(R.id.button_menu);
        btn_info = findViewById(R.id.button_info);
        Date = findViewById(R.id.page_date);
        Name = findViewById(R.id.Name);
        leftSeat = findViewById(R.id.leftseat);
        waiting = findViewById(R.id.waitingOrder);
        lineChart = findViewById(R.id.chart);

        MyApplication app = (MyApplication) getApplication();
        stdName = app.getStdName();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String Realtime = sdf.format(calendar.getTime());

        LeftSeat();
        Waiting();

        String[] onlyDate = Realtime.split(" ");
        onlyDate = onlyDate[0].split("-");
        String finalDate = onlyDate[0] + "년 " + onlyDate[1] + "월 " + onlyDate[2] + "일";
        Date.setText(finalDate);
        Name.setText(stdName);



        //그래프


        ArrayList<Entry> entry_chart1 = new ArrayList<>(); // 데이터를 담을 Arraylist
        ArrayList<Entry> entry_chart2 = new ArrayList<>();
        LineData chartData = new LineData(); // 차트에 담길 데이터

        entry_chart1.add(new Entry(1, 1)); //entry_chart1에 좌표 데이터를 담는다.
        entry_chart1.add(new Entry(2, 2));
        entry_chart1.add(new Entry(3, 3));
        entry_chart1.add(new Entry(4, 4));
        entry_chart1.add(new Entry(5, 2));
        entry_chart1.add(new Entry(6, 8));

        entry_chart2.add(new Entry(1, 2)); //entry_chart2에 좌표 데이터를 담는다.
        entry_chart2.add(new Entry(2, 3));
        entry_chart2.add(new Entry(3, 1));
        entry_chart2.add(new Entry(4, 4));
        entry_chart2.add(new Entry(5, 5));
        entry_chart2.add(new Entry(6, 7));


        LineDataSet lineDataSet1 = new LineDataSet(entry_chart1, "LineGraph1"); // 데이터가 담긴 Arraylist 를 LineDataSet 으로 변환한다.
        LineDataSet lineDataSet2 = new LineDataSet(entry_chart2, "LineGraph2");

        lineDataSet1.setColor(Color.RED); // 해당 LineDataSet의 색 설정 :: 각 Line 과 관련된 세팅은 여기서 설정한다.
        lineDataSet2.setColor(Color.BLACK);

        chartData.addDataSet(lineDataSet1); // 해당 LineDataSet 을 적용될 차트에 들어갈 DataSet 에 넣는다.
        chartData.addDataSet(lineDataSet2);

        lineChart.setData(chartData); // 차트에 위의 DataSet을 넣는다.

        lineChart.invalidate(); // 차트 업데이트
        lineChart.setTouchEnabled(false); // 차트 터치 disable

        //그래프


        try {
            mSocket = IO.socket("http://10.0.2.2:5000");
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        mSocket.on("pickup_alarm", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String data = (String) args[0];  // 문자열 바로 처리
                System.out.println(data);

                if (data.equals("ALARM")) {
                    runOnUiThread(() -> {
                        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                        startActivity(intent);
                    });
                }
            }
        });



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
                System.out.println(stdNum);
                intent.putExtra("orderID", orderID);
                System.out.println(orderID);

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


    public void Waiting() {
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/countWaiting")
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
                        String waits = jsonObject.getString("waiting");
                        runOnUiThread(() -> {
                            waiting.setText("주문 대기 인원 : " + waits + "석");
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}


