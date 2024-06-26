package xyz.rootlab.common.utils.http;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Set;

@Slf4j
class HttpUtils {
    /**
     * 기본적으로 json 요청
     */
    public static String post(String requestUrl, String jsonObjStr) {
        HttpURLConnection conn = connect(requestUrl);
        HttpConfig config = new HttpConfig();
        setConfig(conn, config);
        send(conn, jsonObjStr);
        return receive(conn);
    }

    public static String post(String requestUrl, String jsonObjStr, HttpConfig config) {
        HttpURLConnection conn = connect(requestUrl);
        setConfig(conn, config);
        send(conn, jsonObjStr);
        return receive(conn);
    }

    public static String postForm(String requestUrl, String jsonObjStr) {
        HttpURLConnection conn = connect(requestUrl);
        HttpConfig config = new HttpConfig();
        config.setContentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE);
        setConfig(conn, config);
        send(conn, jsonObjStr);
        return receive(conn);
    }

    private static void setConfig(HttpURLConnection conn, HttpConfig config) {
        try {
            // 전송모드 설정(일반적인 POST방식)
            conn.setDefaultUseCaches(config.isUseCache());
            conn.setDoInput(config.isDoInput());
            conn.setDoOutput(config.isDoOutput());
            conn.setConnectTimeout(config.getConnectTimeOut());
            conn.setReadTimeout(config.getReadTimeOut());
            conn.setRequestMethod(config.getMethod());
            conn.setRequestProperty("charset", "UTF-8");

            // content-type 설정
            conn.setRequestProperty("Content-type", config.getContentType());
            conn.setRequestProperty("Accept", "application/json");
            HashMap<String, String> headers = config.getHeaders();
            if (headers.size() > 0) {
                Set<String> keySet = headers.keySet();
                for (String key : keySet) {
                    String value = headers.get(key);
                    conn.setRequestProperty(key, value);
                }
            }
        } catch (Exception e) {
            log.error("", e);
        }
    }

    private static HttpURLConnection connect(String requestUrl) {
        HttpURLConnection http = null;
        try {
            URL url = new URL(requestUrl);

            // URL설정, 접속
            if (requestUrl.startsWith("https")) {
                TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }};

                // Install the all-trusting trust manager
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new java.security.SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

                // Create all-trusting host name verifier
                HostnameVerifier allHostsValid = new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                };

                // install the all-trusting host verifier
                HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);
                http = (HttpsURLConnection) url.openConnection();
            } else {
                http = (HttpURLConnection) url.openConnection();
            }
        } catch (Exception e) {
            log.error("", e);
        }
        return http;
    }

    private static void send(HttpURLConnection conn, String dataStr) {

        OutputStream os = null;
        try {
            os = conn.getOutputStream();
            if(StringUtils.isNotEmpty(dataStr)) {
                byte[] data = dataStr.getBytes(StandardCharsets.UTF_8);
                if(data.length > 0) {
                    os.write(data);
                    os.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e1) {
                    log.error(e1.getMessage());
                }
            }
        }
    }

    private static String receive(HttpURLConnection conn) {
        InputStream inputStream = null;
        String result = "";
        try {
            inputStream = conn.getInputStream();
            InputStreamReader in = new InputStreamReader(inputStream);
            BufferedReader bufferReader = new BufferedReader(in);
            StringBuilder builder = new StringBuilder();
            String str;
            while ((str = bufferReader.readLine()) != null) {
                builder.append(str).append("\n");
            }
            bufferReader.close();
            in.close();
            result = builder.toString();
        } catch(Exception e) {
            log.error("", e);
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    log.error("", e);
                }
            }
        }

        return result;
    }
}
