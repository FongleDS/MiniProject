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
import com.github.mikephil.charting.formatter.ValueFormatter;


public class MainPage extends Activity {

    Button btn_menu, btn_info, btn_seat;
    TextView Date, Name, leftSeat, waiting;

    String stdNum, stdName, orderID;
    Socket mSocket;
    BarChart barChart;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        if (bundle != null) {
            orderID = bundle.getString("orderNum");
            stdNum = bundle.getString("stdNum");
            stdName = bundle.getString("stdName");
        }

        btn_seat = findViewById(R.id.button_seat);
        btn_menu = findViewById(R.id.button_menu);
        btn_info = findViewById(R.id.button_info);
        Date = findViewById(R.id.page_date);
        Name = findViewById(R.id.Name);
        leftSeat = findViewById(R.id.leftseat);
        waiting = findViewById(R.id.waitingOrder);
        barChart = findViewById(R.id.chart);


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

        ArrayList<BarEntry> entry_chart = new ArrayList<>();

        BarData barData = new BarData();
        String[] labels = new String[]{"09.01", "09.02", "09.03", "09.04", "09.05", "09.06"};

        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                Log.d("ValueFormatter", "Received value: " + value);
                if(value < 0 || value >= labels.length) {
                    return ""; // 또는 다른 적절한 기본값
                }
                return labels[(int) value];
            }
        };

        entry_chart.add(new BarEntry(0, 1));
        entry_chart.add(new BarEntry(1, 2));
        entry_chart.add(new BarEntry(2, 3));
        entry_chart.add(new BarEntry(3, 4));
        entry_chart.add(new BarEntry(4, 2));
        entry_chart.add(new BarEntry(5, 8));

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        //xAxis.setGranularity(1f);

        xAxis.setAxisMinimum(0f);
        xAxis.setAxisMaximum(15f);
        //xAxis.setGridColor(Color.parseColor("#B70050"));
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawLabels(true);
        xAxis.setValueFormatter(formatter);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(4f);

        YAxis axisLeft = barChart.getAxisLeft();
        axisLeft.setDrawGridLines(false);
        axisLeft.setDrawAxisLine(false);
        axisLeft.setAxisMinimum(0f);
        axisLeft.setAxisMaximum(10f);
        axisLeft.setDrawLabels(false);


        axisLeft.setEnabled(false);
        barChart.getAxisRight().setEnabled(false);
        barChart.getDescription().setEnabled(false);
        barChart.getLegend().setEnabled(false);
        barChart.setDrawGridBackground(false);
        barChart.setDrawBorders(false);

        barChart.setEnabled(false);
        barData.setBarWidth(0.25f);

        BarDataSet barDataSet = new BarDataSet(entry_chart, "");
        barDataSet.setColor(Color.parseColor("#B70050"));
        barData.addDataSet(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();



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
                intent.putExtra("orderID", orderID);
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


