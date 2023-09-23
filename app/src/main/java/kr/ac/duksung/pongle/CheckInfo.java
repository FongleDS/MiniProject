package kr.ac.duksung.pongle;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_info);

        stdName = findViewById(R.id.studentName);
        orderMenu = findViewById(R.id.orderMenu);
        orderID = findViewById(R.id.orderID);
        orderTime = findViewById(R.id.orderTime);

        QRCode = findViewById(R.id.qrcodeImage);
        QRCode.setImageBitmap(generateQRCode("20210796"));

        // 주문 정보 불러오기
        OrderData orderData = new OrderData();
        String stdID = orderData.getStdID(); // 학번 불러오기

        // 실시간 현재 시간 받아오기
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String Realtime = sdf.format(calendar.getTime());


        //학번 불러오고나서 학번 관련된 orderData불러와서 실시간 현재 시간이랑 같은 날짜(시간은 비교)하고 pay가 NO인 데이터불러오기
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

/*
    OkHttpClient client = new OkHttpClient();
    public void getOrderInfo(String stdID) {
        RequestBody formBody = new FormBody.Builder()
                .add("stdID", stdID)
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/get_password")
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
                        if (jsonObject.has("password")) {
                            String password = jsonObject.getString("password");
                            runOnUiThread(() -> {
                                System.out.println(password);
                                realPW = password;
                                System.out.println(realPW);
                                System.out.println(PW);

                                // 입력한 비밀번호가 학번의 비밀 번호와 같을 떄
                                if (realPW.equals(PW)) {
                                    Intent intent = new Intent(getApplicationContext(),MainPage.class);
                                    startActivity(intent);
                                }
                                // 입력한 비밀번호가 학번의 비밀 번호와 다를 때
                                else {
                                    stdPW.setText(null);
                                }
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
    */
}