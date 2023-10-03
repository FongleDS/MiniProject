package kr.ac.duksung.pongle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;


public class AlarmActivity extends AppCompatActivity {
    Button btn_alarm;
    Socket mSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        btn_alarm = findViewById(R.id.button_alarm);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);



        try {
            mSocket = IO.socket("http://10.0.2.2:5000");
            mSocket.connect();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        mSocket.on("pickup_alarm", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                System.out.println(data);
                try {
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


        btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SelectSeat.class);
                startActivity(intent);
            }
        });
    }
}