package exam.android.kh.clock;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech tts = null;
    private final  int  REQ_SPEECHI_RECOQ = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                if(i != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.KOREA);
                }
            }
        });

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
                if(data != null && resultCode == RESULT_OK) {
                    List<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if (result.contains("현재시간")) {
                        long now = System.currentTimeMillis();
                        Date date = new Date(now);
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                        // /를 이용하는데 정신건강에 좋다
                        final String getTime = sdf.format(date);
                        final TextView textView = findViewById(R.id.myText);

                        new Thread() {
                            @Override
                            public void run() {


                                tts.speak(getTime, TextToSpeech.QUEUE_FLUSH, null);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView.setText(getTime);
                                    }
                                });
                            }
                        }.start();

                    }

                }

        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        tts.shutdown();
        // 지원이 계속 돌기 때문에 종료해야 된다
    }
}
