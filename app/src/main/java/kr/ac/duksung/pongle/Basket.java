package kr.ac.duksung.pongle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Basket extends AppCompatActivity {
    Button button_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        button_check = findViewById(R.id.button_check);

        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdID = bundle.getString("stdNum");
        String seatID = bundle.getString("seatNum");
        String menuID = bundle.getString("menuNum");
        String Realtime = bundle.getString("orderTime");



        button_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CheckInfo.class);
                //orderUpdate(stdID, seatID, menuID, Realtime);
                orderUpdate(stdID, menuID, Realtime, seatID, intent);
                startActivity(intent);
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
                    String responseBody = response.body().string();
                    try {
                        JSONObject jsonObject = new JSONObject(responseBody);
                        if (jsonObject.has("orderID")) {
                            String orderID = jsonObject.getString("orderID");
                            runOnUiThread(() -> {
                                System.out.println("===========");
                                System.out.println(orderID);
                                intent.putExtra("orderID", orderID);
                                startActivity(intent);
                            });
                        } else if (jsonObject.has("error")) {
                            String error = jsonObject.getString("error");
                            runOnUiThread(() -> {
                                System.out.println("error");
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}