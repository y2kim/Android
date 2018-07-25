package exam.android.kh.openinternet;

import android.content.Intent;
import android.net.Uri;
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

    private TextToSpeech tts = null;
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
                    if(result.get(0).contains("네이버 연결")){
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.naver.com"));
                        startActivity(browserIntent);
                    }else if(result.get(0).contains("구글 연결")){
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                        startActivity(browserIntent);
                    }
                }
                break;
        }
    }


}
