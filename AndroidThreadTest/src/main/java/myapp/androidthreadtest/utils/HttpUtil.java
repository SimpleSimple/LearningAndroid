package myapp.androidthreadtest.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    // public final static String BASE_URL="http://www.99test.com/index.php/";  //使用域名的地址
    public final static String BASE_URL="http://192.168.0.101:8080/index.php/";

    public HttpUtil()
    {}

    public static String getRequest(final String url) throws Exception
    {
        StringBuilder sb=new StringBuilder();
        try {
            URL url1 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(8000);
            conn.setReadTimeout(8000);
            conn.setDoInput(true);
            conn.setDoInput(true);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));
            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append( line );
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static void getRequest(final String url,
        final HttpCallbackListener listener) throws Exception
    {
        //  开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url1 = new URL(url);
                    HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(8000);
                    conn.setReadTimeout(8000);
                    conn.setDoInput(true);
                    conn.setDoInput(true);

                    BufferedReader reader = new BufferedReader(
                            new InputStreamReader(conn.getInputStream(), "utf-8"));
                    StringBuilder sb=new StringBuilder();
                    String line = "";
                    while ((line = reader.readLine()) != null) {
                        sb.append( line );
                    }
                    if(listener != null){
                        listener.onResponse(sb.toString());
                    }
                }catch(Exception ex){
                    if(listener != null){
                        listener.onError(ex);
                    }
                    ex.printStackTrace();
                }
            }
        }).start();
    }
}
