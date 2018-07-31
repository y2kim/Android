package exam.android.kh.weather40;

import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private final int REQ_SPEECHI_RECOQ = 1000;
    private TextToSpeech tts = null;
    private final List<String> listString = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn01);
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                // i 초기화 성공했냐 ? 실패했냐 ?
                if (i != TextToSpeech.ERROR) {
                    tts.setLanguage(Locale.KOREA);
                }
            }
        });

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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 액티비티 전환
        switch (requestCode) { //1000번지
            case REQ_SPEECHI_RECOQ:
                if (data != null && resultCode == RESULT_OK) {
                    List<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    if (result.get(0).contains("현재 날씨")) {
                        new Thread() {
                            public void run() {
                                try {
                                    URL myurl = new URL("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1153051000");
                                    HttpURLConnection con = (HttpURLConnection) myurl.openConnection();
                                    Scanner sc = new Scanner(con.getInputStream(), "utf8");
                                    String[] strarr1 = new String[1150];
                                    String[] strarr2 = new String[1150];
                                    int i =0;
                                    String line = null;
                                    while (true) {


                                        try {
                                            line = sc.nextLine();
                                            strarr1[i++]=sc.nextLine();

                                        if (line.contains("<data seq=\"0\">")) {
                                            if(strarr1[i-3].contains("<data seq=\"0\">")) {
                                                listString.set(0, (line.split("<temp>")[1].split("</temp>")[0]));
                                            }
                                            if(strarr1[i-8].contains("<data seq=\"0\">")) {
                                                listString.set(1, (line.split("<wfKor>")[1].split("</wfKor>")[0]));
                                            }
                                            if(strarr1[i-10].contains("<data seq=\"0\">")) {
                                                listString.set(2, (line.split("<wdKor>")[1].split("</wdKor>")[0]));
                                            }
//                                            if(strarr1[i-17].contains("<data seq=\"0\">")) {
//                                                listString.set(3, (line.split("<reh>")[1].split("</reh>")[0]));
//                                            }
                                        }

                                        } catch (Exception e2) {
                                            System.out.println("끝남");
                                            break;
                                        }
                                    }
                                    con.disconnect();
                                    tts.speak("오늘날씨는"+listString.get(0)+"도 이고 날씨 상태는"+
                                            listString.get(1)+"바람은 "+listString.get(2)+"풍으로 불겠습니다", TextToSpeech.QUEUE_FLUSH, null);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                    }

                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tts.shutdown();
        // 지원이 계속 돌기 때문에 종료해야 된다
    }

}
