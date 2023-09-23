package kr.ac.duksung.pongle;

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

import java.text.SimpleDateFormat;
import java.util.Date;


public class CheckInfo extends AppCompatActivity {
    TextView stdName;
    TextView orderMenu;
    TextView orderID;
    TextView orderTime;
    ImageView QRCode;

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    TextView mTextView;


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

        //mTextView = (TextView) findViewById(R.id.textView);
        //mRefreshBtn = (Button) findViewById(R.id.refreshBtn);

        //bind listener
        //mRefreshBtn.setOnClickListener(this);


        //mTextView.setText(getTime());
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


    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}