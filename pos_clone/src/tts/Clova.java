//java fx : http://pentode.tistory.com/90
//naver clova(TTS) : https://developers.naver.com/docs/clova/api/#/CSS/API_Guide.md#RequestParameter

package tts;

//네이버 음성합성 Open API 예제
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Date;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;;

public class Clova {
	public static String fileLoc;
	
	public Clova() {
     String clientId = "hoDhI8eoI9Zs6CLFQrgs";//애플리케이션 클라이언트 아이디값";
     String clientSecret = "Jt0qd2TKTT";//애플리케이션 클라이언트 시크릿값";
     String textForSpeech = "데이터베이스스스스스ㅡ스스스스스스ㅡㅅㅎㅅ,ㅎㅅ,ㅎ슿스";
     try {
         String text = URLEncoder.encode(textForSpeech, "UTF-8"); 
         String apiURL = "https://openapi.naver.com/v1/voice/tts.bin";
         URL url = new URL(apiURL);
         HttpURLConnection con = (HttpURLConnection)url.openConnection();
         con.setRequestMethod("POST");
         con.setRequestProperty("X-Naver-Client-Id", clientId);
         con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
         // post request
         String postParams = "speaker=mijin&speed=0&text=" + text;
         con.setDoOutput(true);
         DataOutputStream wr = new DataOutputStream(con.getOutputStream());
         wr.writeBytes(postParams);
         wr.flush();
         wr.close();
         int responseCode = con.getResponseCode();
         BufferedReader br;
         if(responseCode==200) { // 정상 호출
             InputStream is = con.getInputStream();
             int read = 0;
             byte[] bytes = new byte[1024];
             // 랜덤한 이름으로 mp3 파일 생성
             String tempname = Long.valueOf(new Date().getTime()).toString();
             File f = new File(tempname + ".mp3");
             f.createNewFile(); 
             //내용 채워주기
             OutputStream outputStream = new FileOutputStream(f);
             while ((read =is.read(bytes)) != -1) {
                 outputStream.write(bytes, 0, read);
             }
             is.close();
             fileLoc = new File(f.getAbsolutePath()).toURI().toString();
             System.out.println(fileLoc);
          
         } else {  // 에러 발생
             br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
             String inputLine;
             StringBuffer response = new StringBuffer();
             while ((inputLine = br.readLine()) != null) {
                 response.append(inputLine);
             }
             br.close();
             System.out.println(response.toString());
         }
     } catch (Exception e) {
         System.out.println(e);
     }
 }
}