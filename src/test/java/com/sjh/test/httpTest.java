package com.sjh.test;

import org.apache.http.HttpResponse;
import org.apache.http.RequestLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.assertj.core.data.MapEntry;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * ClassName: httpTest <br/>
 * Description: <br/>
 * date: 2020/8/3 16:04<br/>
 *
 * @author ex-sujh<br/>
 * @since JDK 12
 */
public class httpTest {

    @Test
    public void test() throws Exception {
        OutputStreamWriter out = null;
        URL url;
        BufferedReader in = null;
        StringBuffer result = new StringBuffer();

        url = new URL("http://192.168.8.16:18808/reportcenter/batch/bathDownload.do?parameter=");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setUseCaches(false);
        conn.setInstanceFollowRedirects(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("user-agent", "Mozilla/4.0(compatible;MSIE 6.0;Windows NT 5.1;SV1)");
        conn.connect();
        out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
        out.append("{\"json\":\" {\\\"PI_OPNAME\\\":\\\"ss\\\",\\\"PI_DATE\\\":\\\"20190509\\\",\\\"PI_FUNDID\\\":\\\"000644\\\",\\\"PI_TRADEDATE\\\":\\\"20190509\\\"}\", \"parame\":\"{\\\"reportid\\\":\\\"27560\\\",\\\"emailFiletype\\\":\\\".pdf\\\"}\"}");
        out.flush();
        out.close();
        System.out.println(conn);
        if (conn.getResponseCode() == 200) {
            in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        }
        String line;
        while((line = in.readLine()) != null) {
            result.append(line);
        }
        System.out.println(result.toString());

        int a = 5/3;
        System.out.println(a);
    }

    @Test
    public void testPost() throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost post = new HttpPost();
        StringEntity entity = new StringEntity("\"parameter\":{\"json\":\" {\"PI_OPNAME\":\"ss\",\"PI_DATE\":\"20190509\",\"PI_FUNDID\":\"000644\",\"PI_TRADEDATE\":\"20190509\"}\", \"parame\":\"{\"reportid\":\"27560\",\"emailFiletype\":\".pdf\"}\"}");
        post.setEntity(entity);
        URI uri = new URI("http://192.168.8.16:18808/reportcenter/batch/bathDownload.do");
        post.setURI(uri);

        HttpResponse execute = httpClient.execute(post);

        String s = EntityUtils.toString(execute.getEntity());

        System.out.println(s);
    }

}
