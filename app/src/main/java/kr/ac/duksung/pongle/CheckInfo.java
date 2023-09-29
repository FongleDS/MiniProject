package kr.ac.duksung.pongle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class CheckInfo extends AppCompatActivity {
    TextView stdName;
    TextView orderMenu;
    TextView orderID;
    TextView orderTime;
    ImageView QRCode;
    Button seat_view;
    ImageView exitButton;
    OrderData orderData = new OrderData();
    String orderStr = new String();

    ArrayList infoList = new ArrayList();
    //SharedPreferences prefs = getSharedPreferences("stdorderInfo", MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_info);

        stdName = findViewById(R.id.studentName);
        orderMenu = findViewById(R.id.orderMenu);
        orderID = findViewById(R.id.orderID);
        orderTime = findViewById(R.id.orderTime);
        seat_view = findViewById(R.id.seat_view);
        exitButton = findViewById(R.id.exitButton);

        seat_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectSeat.class);
                startActivity(intent);
            }
        });

        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(intent);
            }
        });



        //SharedPreferences.Editor editor = prefs.edit();
        //String stdID = prefs.getString("stdID", null);
        //editor.apply();

        String stdID = "20210796";

        // 주문 정보 불러오기
        System.out.println(stdID);
        getOrderInfo(stdID);

        QRCode = findViewById(R.id.qrcodeImage);
        QRCode.setImageBitmap(generateQRCode(stdID));

        orderID.setText((CharSequence) infoList.get(0));
        orderTime.setText((CharSequence) infoList.get(1));
        // 실시간 현재 시간 받아오기
        //Calendar calendar = Calendar.getInstance();
        //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        //String Realtime = sdf.format(calendar.getTime());


        //orderID로만 조회
    }


    private Bitmap generateQRCode(String text) {
        int width = 500;
        int height = 500;
        BitMatrix bitMatrix;
        try {
            bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height);
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                int offset = y * width;
                for (int x = 0; x < width; x++) {
                    pixels[offset + x] = bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE;
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }


    OkHttpClient client = new OkHttpClient();
    public void getOrderInfo(String stdID) {
        RequestBody formBody = new FormBody.Builder()
                .add("stdID", stdID)
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
                        JSONArray jsonArray = new JSONArray(responseBody);
                        System.out.println(jsonArray);

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            Iterator<String> keys = jsonObject.keys();

                            while(keys.hasNext()) {
                                String key = keys.next();
                                infoList.add(jsonObject.getString(key));
                                System.out.println(infoList);
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}