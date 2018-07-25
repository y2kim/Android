package exam.android.kh.papagotranslator;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn01);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //android.view.ViewRootImpl$CalledFromWrongThreadException: Only the original thread that created a view hierarchy can touch its views.
                //android 방식의 thread 방식
                 final EditText editText = findViewById(R.id.edit01);
                 final TextView textView = findViewById(R.id.textresult01);
                 final NaverAPI naverAPI = new NaverAPI();

                 final Handler handler = new Handler()
                {
                    public void handleMessage(Message msg)
                    {
                        textView.setText(str);
                    }
                };

                new Thread(){
                     public void run() {

                         Message msg = handler.obtainMessage();
                         handler.sendMessage(msg);

                         try {
                             str =  naverAPI.translate(editText.getText().toString());

                         } catch (Exception e) {
                             e.printStackTrace();
                         }

                     }
                 }.start();

                        // 메인쓰레드에서  네트워크 작업을 할수가 없다
                        /*  반응자체를 안하니 .... 안드로이드에서  규칙을 가지고 있다 메인쓰레드에서 네트워크를 작업을 할수가 없다는것을   */
            }
        });

    }
}

/*
* 1. 메인 스레드는  네트워크 작업을 할수가 없다
* 2. (component)view  mainthread  uithread  변화를 줄수 있다
*
* tarnslate : 네트워크 작업이 필요하다  그래서 스레드가 필요하게 된다
* 메인 스레드   그래픽 전담 쓰레드에서
*
* */