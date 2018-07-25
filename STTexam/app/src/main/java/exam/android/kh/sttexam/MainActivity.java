package exam.android.kh.sttexam;

import android.content.Intent;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final  int  REQ_SPEECHI_RECOQ = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
                // 내장되어있는  스피치
                startActivityForResult(intent, REQ_SPEECHI_RECOQ);
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        // 액티비티 전환
        switch (requestCode){ //1000번지
            case REQ_SPEECHI_RECOQ:
                    if(data != null && resultCode == RESULT_OK){
                        List<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        TextView Text = findViewById(R.id.text01);
                        Text.setText(result.get(0));
                    }
                break;
        }
    }

}
