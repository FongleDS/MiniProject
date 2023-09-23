package kr.ac.duksung.pongle;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MenuPan extends Activity {
    Button btn_han;
    Button btn_joong;
    Button btn_il;
    Button btn_yang;
    Button btn_bun;

    TextView han_waiting;
    TextView joong_waiting;
    TextView il_waiting;
    TextView yang_waiting;
    TextView bun_waiting;
    TextView testing;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pan);
        btn_han = findViewById(R.id.button_korean);
        btn_joong = findViewById(R.id.button_china);
        btn_il = findViewById(R.id.button_japan);
        btn_yang = findViewById(R.id.button_western);
        btn_bun = findViewById(R.id.button_snack);

        han_waiting = findViewById(R.id.text_han);
        joong_waiting = findViewById(R.id.text_joong);
        il_waiting = findViewById(R.id.text_il);
        yang_waiting = findViewById(R.id.text_yang);
        bun_waiting = findViewById(R.id.text_boon);



        /*데이터 베이스 연동 해놓은 코드!! 조심해주세욤!!
        OkHttpClient client = new OkHttpClient();

        String url = "http://10.0.2.2:5000/restCount";

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // Handle the error
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String jsonResponse = response.body().string();

                    try {
                        JSONArray jsonArray = new JSONArray(jsonResponse);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONArray innerArray = jsonArray.getJSONArray(i);

                            String firstValue = innerArray.getString(0);
                            String secondValue = innerArray.getString(1);

                            switch (firstValue) {
                                case "1" :
                                    han_waiting.setText(secondValue);
                                case "2" :
                                    il_waiting.setText(secondValue);
                                case "3" :
                                    joong_waiting.setText(secondValue);
                                case "4" :
                                    yang_waiting.setText(secondValue);
                                case "5" :
                                    bun_waiting.setText(secondValue);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
*/

        btn_han.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KFood.class);
                startActivity(intent);
            }
        });

        btn_joong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CFood.class);
                startActivity(intent);
            }
        });

        btn_il.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),JFood.class);
                startActivity(intent);
            }
        });

        btn_yang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),WFood.class);
                startActivity(intent);
            }
        });

        btn_bun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Bunsik.class);
                startActivity(intent);
            }
        });

    }
}