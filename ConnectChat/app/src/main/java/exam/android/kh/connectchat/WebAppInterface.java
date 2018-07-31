package exam.android.kh.connectchat;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;

public class WebAppInterface {

    private Context context;
    private WebView webView;

    public WebAppInterface(Context c, WebView w){
        this.context = c;
        this.webView = w;
    }

    @JavascriptInterface
    public  void href(final String page){

        this.webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("http://192.168.20.12:8080/controller/"+page);
                // All WebView method must becall on the sma thread 이에러가 뜨면 웹뷰가 동작하는 쓰레드에 넣어주어야 한다
            }
        });

        //  this.webView.loadUrl("http://192.168.20.12/"+page);
        //내 앱에서 바뀜
    }

}
