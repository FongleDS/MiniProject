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
import org.w3c.dom.Text;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
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

public class CheckInfo extends AppCompatActivity {
    TextView stdName;
    TextView orderedMenu;
    TextView orderedID;
    TextView orderedTime;
    TextView selectedSeat;
    ImageView QRCode;
    ArrayList infoList = new ArrayList();
    Button seat_view, exitButton;
    Socket mSocket;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_info);

        // button 요소 연결
        stdName = findViewById(R.id.userName);
        orderedMenu = findViewById(R.id.orderMenu);
        orderedID = findViewById(R.id.orderID);
        orderedTime = findViewById(R.id.orderTime);
        selectedSeat = findViewById(R.id.seatID);
        seat_view = findViewById(R.id.seat_view);
        exitButton = findViewById(R.id.exitButton);

        try {
            mSocket = IO.socket("http://10.0.2.2:5000");
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        mSocket.on("pickup_alarm", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                String dataString = (String) args[0];
                try {
                    JSONObject data = new JSONObject(dataString);
                    String result = data.getString("Result");
                    System.out.println(result);
                    if (result.equals("ALARM")) {
                        // orderManager.addOrder(orderID, menuName, "1");
                        runOnUiThread(() -> {
                            Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
                            startActivity(intent);
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



        // 전 액티비티에서 데이터 받아오기
        MyApplication app = (MyApplication) getApplication();
        String orderID = app.getOrderID();
        String stdID = app.getStdID();

        System.out.println("=============");
        System.out.println(orderID);


        seat_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CheckInfo.this, SelectSeat.class);
                intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent1);
            }
        });


        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(CheckInfo.this, MainPage.class);
                intent2.putExtra("orderID", orderID);
                intent2.putExtra("stdNum", stdID);
                intent2.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
            }
        });

        // 주문 정보 불러오기
        System.out.println(orderID);
        getOrderInfo(orderID);
        System.out.println("==============");
        System.out.println(infoList);

        // QRCODE 생성
        String QRs = orderID + "_" + stdID;
        System.out.println(QRs);
        QRCode = findViewById(R.id.qrcodeImage);
        QRCode.setImageBitmap(generateQRCode(QRs));
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
    public void getOrderInfo(String orderID) {
        RequestBody formBody = new FormBody.Builder()
                .add("orderID", orderID)
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
                        orderedID.setText(orderID);
                        stdName.setText((CharSequence) infoList.get(0));
                        orderedTime.setText((CharSequence) infoList.get(1));
                        selectedSeat.setText((CharSequence) infoList.get(2));
                        orderedMenu.setText((CharSequence) infoList.get(3));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }
}