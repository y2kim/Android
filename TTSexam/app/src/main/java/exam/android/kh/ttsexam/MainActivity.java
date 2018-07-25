package exam.android.kh.ttsexam;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextToSpeech tts = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // i 초기화 성공했냐 ? 실패했냐 ?
                if(i != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.KOREA);
                }
            }
        });

        Button button  = findViewById(R.id.btn01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText str = findViewById(R.id.edt01);
                String line = str.getText().toString();
                tts.setPitch(2.0F);// 톤 높이
                tts.setSpeechRate(2.0F);// 빠르기 f
                tts.speak(line,TextToSpeech.QUEUE_FLUSH,null);
            }
        });



    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        tts.shutdown();
        // 지원이 계속 돌기 때문에 종료해야 된다
    }

}
