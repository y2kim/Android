package exam.android.kh.gui_01;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private int pressedCount = 1;
    //public static TextToSpeech tts = null;
    private WebView webView = null; // 간단하게 html 넣을수 있는  패널
    private final int REQ_SPEECHI_RECOQ = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(
                    new String[]{Manifest.permission.SEND_SMS}, 1000  // 숫자는 상관없음
            );
            // 이거 안하면 실행이 안됨 마쉬멜로 부터
        }

//         tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int i) {
//
//                if(i != TextToSpeech.ERROR){
//                    tts.setLanguage(Locale.KOREA);
//                }
//            }
//        });


       // TTSclass ttSclass = new TTSclass();
        this.webView = findViewById(R.id.webview);
        this.webView.loadUrl("file:///android_asset/index.html");
        //file 로 연결
        this.webView.getSettings().setJavaScriptEnabled(true);
        // 자바 스크립트 실행 가능   // 안드로이드 자바를 연동시키는 코드
        this.webView.setWebChromeClient(new WebChromeClient());
        // 2r개의 코드가 있어야  제대로 모든 기능이 가능함
        this.webView.addJavascriptInterface(new WebAppInterface(this, webView), "Android");
        // 자바스크립트롸의 연동  WebAppInterface  지정  (자유) // String : 자바스크립트에서 뭘로 부를지 정해야 한다


    }

    //
//    @Override
//    protected void onDestroy(){
//        super.onDestroy();
//        tts.shutdown();
//        // 지원이 계속 돌기 때문에 종료해야 된다
//    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SpeechUtils.REQ_SPEECH_RECOGNIZE:
                if (resultCode == RESULT_OK && null != data) {
                    List<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                        System.out.print(result.get(0));
                        Log.e("!!!",result.get(0));
                    if(result.get(0).contains("문자 보내 줘")){
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage("01032023529",null,"문자",null,null);
                        Toast.makeText(this,"01032023529" + " : " + "문자",Toast.LENGTH_SHORT).show();
                    }

                }
        }
    }

}
