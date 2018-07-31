package exam.android.kh.connectchat;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.webView = findViewById(R.id.webview);
        this.webView.loadUrl("192.168.20.16/controller/chat.jsp");
        // this.webView.loadUrl("http://192.168.20.7:8080/Chat_Prac2/chat.jsp");
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.setWebChromeClient(new WebChromeClient());

        this.webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return false;
            }
        });

        this.webView.addJavascriptInterface(new WebAppInterface(this,webView),"Android");


    }
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        // TODO Auto-generated method stub
        super.onConfigurationChanged(newConfig);
    }



}
