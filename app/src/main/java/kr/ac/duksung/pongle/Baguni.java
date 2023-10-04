package kr.ac.duksung.pongle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Baguni extends AppCompatActivity {
    Button button_order;
    TextView Baguni;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baguni);

        button_order = findViewById(R.id.button_order);
        Baguni = findViewById(R.id.text_baguni);

        Intent intent = new Intent(getApplicationContext(), Basket.class);

        getBasket(intent);

        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Realtime = sdf.format(calendar.getTime());
                intent.putExtra("orderTime", Realtime);
                startActivity(intent);
            }
        });

    }

    OkHttpClient client = new OkHttpClient();

    public void getBasket(Intent intent) {
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/getBasket")
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
                        JSONArray jsonArray = new JSONArray(responseBody);
                        System.out.println(jsonArray);

                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            if (i > 0) {
                                stringBuilder.append(", "); // 값들 사이에 콤마와 공백을 추가
                            }
                            stringBuilder.append(jsonArray.getString(i));
                        }
                        Baguni.setText(stringBuilder.toString());
                        intent.putExtra("menuID", stringBuilder.toString());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    finally {
                        response.close();
                    }
                }
            }
        });
    }
}