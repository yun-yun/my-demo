package top.lyunk.httpclient.convert;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * 创建人:lyunk
 * 时间：2018-10-11 14:10
 * 说明：从HttpClientUtil中返回的InputStream，将其转换为String存放于content中
 *
 */
public class HttpResp2StringConverter extends HttpRespConverter {


    /**
     * 内容
     */
    private String content;

    @Override
    public void convert(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(isr);
        String line;
        StringBuilder strBuilder = new StringBuilder();
        while ((line = bufferedReader.readLine()) != null){
            strBuilder.append(line);
        }
        this.content = strBuilder.toString();
    }

    public String getContent() {
        return content;
    }
}
