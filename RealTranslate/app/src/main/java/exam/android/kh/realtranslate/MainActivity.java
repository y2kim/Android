package exam.android.kh.realtranslate;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private final  int  REQ_SPEECHI_RECOQ = 1000;
    private TextToSpeech tts = null;
    TextView textView01;

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
                    textView01 = findViewById(R.id.txt01);
                    textView01.setText(result.get(0));

                    final TextView textView02 = findViewById(R.id.txt02);
                    final String transtext = textView01.getText().toString();
                    final NaverAPI naverAPI = new NaverAPI();
                    new Thread(){
                        @Override
                        public void run() {
                            try {
                                final String resultTransText = naverAPI.translate(transtext);
                                tts.speak(resultTransText,TextToSpeech.QUEUE_FLUSH,null);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        textView02.setText(resultTransText);
                                    }
                                });
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }.start();
                }
                break;
        }
    }
}
