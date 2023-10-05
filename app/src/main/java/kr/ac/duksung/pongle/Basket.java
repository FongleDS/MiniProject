package kr.ac.duksung.pongle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;

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

public class Basket extends AppCompatActivity {
    Button button_check;
    Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        button_check = findViewById(R.id.button_check);

        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String Realtime = bundle.getString("orderTime");
        MyApplication app = (MyApplication) getApplication();

        String stdID = app.getStdID();
        String seatID = app.getSeatID();
        String menuID = bundle.getString("menuID");

        Intent intent = new Intent(getApplicationContext(), CheckInfo.class);
        intent.putExtra("stdNum", stdID);
        orderUpdate(stdID, menuID, Realtime, seatID, intent);
        BasketInit();

        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });

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

    }

    OkHttpClient client = new OkHttpClient();
    public void orderUpdate(String stdID, String menuID, String orderDate, String seatID, Intent intent) {
        RequestBody formBody = new FormBody.Builder()
                .add("stdID", stdID)
                .add("menuID", menuID)
                .add("orderDate", orderDate)
                .add("seatID", seatID)
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/orderUpdate")
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String responseBody = response.body().string();
                        JSONObject jsonResponse = new JSONObject(responseBody);
                        String orderID = jsonResponse.getString("orderID");
                        runOnUiThread(() -> {
                            System.out.println("===========");
                            System.out.println(orderID);
                            MyApplication app = (MyApplication) getApplicationContext();
                            app.setOrderID(orderID);
                            intent.putExtra("orderID", orderID);
                        });
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public void BasketInit() {
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/basketInit")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println("basket INit");
                }
            }
        });
    }
}