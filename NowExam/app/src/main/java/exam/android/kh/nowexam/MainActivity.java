package exam.android.kh.nowexam;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        Button button = findViewById(R.id.btn01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final EditText editText = findViewById(R.id.edt01);
                final String text = editText.getText().toString();
                final TextView textView = findViewById(R.id.text01);
                final NaverAPI naverAPI = new NaverAPI();
                new Thread(){
                    @Override
                    public void run() {
                        // 메인 스레드에서는 네트워크 작업할수 없다는 이슈
                        try {
                            final String result = naverAPI.translate(text);
                            tts.setPitch(2.0F);// 톤 높이
                            tts.setSpeechRate(2.0F);// 빠르기 f
                            tts.speak(result,TextToSpeech.QUEUE_FLUSH,null);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    textView.setText(result);
                                }
                            });
                        }catch (Exception e){
                            e.printStackTrace();
                        }

                    }
                }.start();
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
