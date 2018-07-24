package com.example.user1.exam02;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* 현재 권한이 없음  */
        /* 권한 중  위험한 권한  : 전화 ,[금전적 피해 같은것] 또한번 권한을 받아야함  // 진동은  일반적 권한  */
        /* 지금은 권한 줌  <uses-permission android:name="android.permission.VIBRATE" />  */

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Vibrator vi = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                 vi.vibrate(1000);

            }
        });
    }
}
