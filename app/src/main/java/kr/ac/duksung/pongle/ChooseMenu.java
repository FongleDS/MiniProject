package kr.ac.duksung.pongle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class ChooseMenu extends AppCompatActivity {
    Button ordercomplete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_menu);
        Intent intent = new Intent(getApplicationContext(), CheckInfo.class);
        ordercomplete = findViewById(R.id.orderComplete);


        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String stdID = bundle.getString("stdNum");
        String seatID = bundle.getString("seatNum");
        //String menuID = bundle.getString("menuNum");
        String menuID = "10";
        //String seatID = "10";

        // 실시간 현재 시간 받아오기
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());


        ordercomplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Realtime = sdf.format(calendar.getTime());
                //orderUpdate(stdID, seatID, menuID, Realtime);
                getOrderInfo(stdID, seatID, menuID, Realtime, intent);
            }
        });
    }

    OkHttpClient client = new OkHttpClient();
    public void getOrderInfo(String stdID, String menuID, String orderDate, String seatID, Intent intent) {
        RequestBody formBody = new FormBody.Builder()
                .add("stdID", stdID)
                .add("menuID", menuID)
                .add("orderDate", orderDate)
                .add("seatID", seatID)
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/getOrderInfo")
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
                                intent.putExtra("seatNum", seatID);
                                intent.putExtra("stdNum", stdID);
                                intent.putExtra("menuNum", menuID);
                                intent.putExtra("orderID", orderID);
                                intent.putExtra("Realtime", orderDate);
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