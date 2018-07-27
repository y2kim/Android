package exam.android.kh.gui_01;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;

import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.webkit.JavascriptInterface;
import android.media.AudioManager;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;


import static android.app.Activity.RESULT_OK;


//import static exam.android.kh.gui_01.MainActivity.tts;

// 매개체 역활  안드로이와 html 의 접촉
public class WebAppInterface {

    private final  int  REQ_SPEECHI_RECOQ = 1000;
    private Context context;
    private TTSutils contexts;
    // context ; 어차피 시스템
    private WebView webView;


    public WebAppInterface(Context c, WebView w){
        this.context = c;
        this.webView = w;
        contexts  = new TTSutils();
    }

    // 어노테이션 JavascriptInterface 실행됨
    @JavascriptInterface
    public void toastMessage(){
        Toast.makeText(context,"연동 성공  : 여기는 안드로이드",Toast.LENGTH_SHORT).show();
    }
    // index.html
    @JavascriptInterface
    public void toastMessage(String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public  void upvolume(){
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        int volume =  audioManager.getStreamVolume(AudioManager.STREAM_RING);
        if(volume<15){
            audioManager.setStreamVolume(AudioManager.STREAM_RING,volume+1,AudioManager.FLAG_PLAY_SOUND);
        }else{
            Toast.makeText(context, "현재 최고음량입니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @JavascriptInterface
    public  void downvolume(){
        AudioManager audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        int volume =  audioManager.getStreamVolume(AudioManager.STREAM_RING);
        if(volume>0){
            audioManager.setStreamVolume(AudioManager.STREAM_RING,volume-1,AudioManager.FLAG_PLAY_SOUND);
        }else{
            Toast.makeText(context, "현재 최저음량입니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @JavascriptInterface
    public void sendSms(String phone, String msg){
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phone,null,msg,null,null);
        Toast.makeText(context, phone+" : " + msg, Toast.LENGTH_SHORT).show();
    }
    // 문자 보내는건 위험 권한

    @JavascriptInterface
    public void texttospeak(String text){

       // tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    @JavascriptInterface
    public void sendMail(){

        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        ((MainActivity)context).startActivityForResult(intent,REQ_SPEECHI_RECOQ);

    }


}
