package kr.ac.duksung.pongle;
import android.content.Intent;
import android.content.SharedPreferences;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
    TextView orderedMenu;
    TextView orderedID;
    TextView orderedTime;
    TextView selectedSeat;
    ImageView QRCode;
    ArrayList infoList = new ArrayList();

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

        // 전 액티비티에서 데이터 받아오기
        Intent getintent = getIntent();
        Bundle bundle = getintent.getExtras();
        String orderID = (String) bundle.get("orderID");
        System.out.println(orderID);
        // String orderID = "9";
        // String stdID = bundle.getString("stdNum");
        // String seatID = bundle.getString("seatNum");
        // String menuID = bundle.getString("menuNum");
        String orderTime = (String) bundle.get("orderTime");

        // 주문 정보 불러오기
        System.out.println(orderID);
        getOrderInfo(orderID);
        System.out.println(infoList);

        // QRCODE 생성
        QRCode = findViewById(R.id.qrcodeImage);
        QRCode.setImageBitmap(generateQRCode(orderID));

        //주문 번호, 주문 시간, 학생 이름 설정
        orderedID.setText(orderID);
        orderedTime.setText(orderTime);
        selectedSeat.setText((CharSequence) infoList.get(1));
        stdName.setText((CharSequence) infoList.get(0));
        orderedMenu.setText((CharSequence) infoList.get(3));
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
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}