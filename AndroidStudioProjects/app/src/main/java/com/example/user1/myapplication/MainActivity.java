package com.example.user1.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnExam01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               EditText et = findViewById(R.id.txtExam01);
               String msg = et.getText().toString();
               //R = rewoure

                /* 익명 인스턴스 방식 */
                Toast.makeText(MainActivity.this.getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                /*  MainActivity.this.getApplicationContext() / 자유말 / 숏 롱   */
            }
        });

    }
}
