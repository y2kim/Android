package exam.android.kh.messagesclient;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private WebView webView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

         this.webView = findViewById(R.id.webview);
         this.webView.loadUrl("http://192.168.20.12:8080/java/index.jsp");
         // 앱을 벗어나지 않는다 .
         this.webView.getSettings().setJavaScriptEnabled(true);
         this.webView.setWebChromeClient(new WebChromeClient());

         this.webView.setWebViewClient(new WebViewClient(){
             public boolean shouldOverrideUrlLoading(WebView webView, String url){
                 // 무효화시킨다  크롬으로 벗어나지 않게
                 webView.loadUrl(url);
                 return  false;
             }
         });

         this.webView.addJavascriptInterface(new WebAppInterface(this,webView),"Android");
         // 같은 스레드를 맞춰야 한단
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //if문 설명 : keyCode 값이 백버튼 코드값 이면서 GoBack 할 수 있는 페이지가 있을 때
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();

            return true;
        }

        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack() == false) {
            new AlertDialog.Builder(this).setTitle("프로그램 종료")
                    .setMessage("프로그램을 종료하시겠습니겠습니까?").setPositiveButton("네", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                      android.os.Process.killProcess(android.os.Process.myPid());
                }
            }).setNegativeButton("아니오",null).show();
                    // 빌더 패턴
        }
        return super.onKeyDown(keyCode,event);
    }

}
