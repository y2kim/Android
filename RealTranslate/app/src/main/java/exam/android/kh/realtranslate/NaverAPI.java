package exam.android.kh.realtranslate;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class NaverAPI {

    public String translate(String soricreMessage) throws  Exception {

        String clientId = "I6X2gWWqioV4AJCwEwJ_";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "7BggKC5Yom";//애플리케이션 클라이언트 시크릿값";

        String text = URLEncoder.encode(soricreMessage, "UTF-8");
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("X-Naver-Client-Id", clientId);
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
        // post request
        String postParams = "source=ko&target=en&text=" + text; // 번열될 부분
        /* 우리가 신경쓸것  <-  이부분  */
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(postParams);
        wr.flush();
        wr.close();
        int responseCode = con.getResponseCode();
        BufferedReader br;  // 리퀘스트 쏘고 받고 하는 과정들
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        /*json 타입으로 돌려줬다   상호호환이 안되서 json으로 돌려줬다*/
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();
        JSONObject result = new JSONObject(response.toString());

        String resultMesages =  result.getJSONObject("message").
                getJSONObject("result").getString("translatedText");

        return  resultMesages;


    }

}
