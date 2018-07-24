package com.example.user1.quiz01;

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

        Button btn = findViewById(R.id.btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText edt01 = findViewById(R.id.edt01);
                EditText edt02 = findViewById(R.id.edt02);

                String str1 = edt01.getText().toString();
                String str2 = edt02.getText().toString() ;

                int val1 = Integer.parseInt(str1);
                int val2 = Integer.parseInt(str2);

                int val3 = val1 + val2;

                String finalmsg = val3+"";

                Toast.makeText(MainActivity.this.getApplicationContext(),finalmsg,Toast.LENGTH_SHORT).show();

            }
        });
}
}
