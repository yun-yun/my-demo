package top.lyunk.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Set;

/**
 * 创建人:lyunk
 * 时间：2018-10-11 10:19
 * 说明：
 */
public class HttpClientUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    CloseableHttpClient httpClient = HttpClients.createDefault();

    CloseableHttpResponse response;

    public HttpClientUtil() {
    }

    public Object sendGetHttp(String url, String param){
        return sendHttp(HttpMethods.GET,url,param);
    }
    public Object sendPostHttp(String url, String param){
        return sendHttp(HttpMethods.POST,url,param);
    }
    public Object sendPutHttp(String url, String param){
        return sendHttp(HttpMethods.PUT,url,param);
    }
    public Object sendHeadHttp(String url, String param){
        return sendHttp(HttpMethods.HEAD,url,param);
    }
    public Object sendDeleteHttp(String url, String param){
        return sendHttp(HttpMethods.DELETE,url,param);
    }
    public Object sendTraceHttp(String url, String param){
        return sendHttp(HttpMethods.TRACE,url,param);
    }
    public Object sendOptionsHttp(String url, String param){
        return sendHttp(HttpMethods.OPTIONS,url,param);
    }

    public Object sendHttp(HttpMethods method, String url, String param){
        try {
            switchHttpMethod(method, url, param);
            HttpEntity entity = this.response.getEntity();
            InputStream is = entity.getContent();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(isr);
            String line;
            StringBuilder strBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null){
                strBuilder.append(line);
            }
            System.out.println(strBuilder);
        } catch (IOException e) {
            httpClientClose();
        }finally {
            httpClientClose();
        }
        return new Object();
    }

    private void switchHttpMethod(HttpMethods method, String url, String param) throws IOException {
        logger.info("有一条待发送的 " + method.name + " 请求");
        switch (method){
            case GET:
                this.response = sendGet(this.httpClient,url,param);break;
            default:
                this.response = sendGet(this.httpClient,url,param);break;
        }
    }


//    GET======================
    private CloseableHttpResponse sendGet(CloseableHttpClient httpClient, String url, String param) throws IOException {
        logger.info("正在构造请求URL...");
        HttpGet httpGet = new HttpGet(url + "?" +  param);
        return httpClientExecute(httpClient, httpGet);
    }

    private CloseableHttpResponse sendGet(CloseableHttpClient httpClient, String url, Map<String,String> param) throws IOException {
        logger.info("正在构造请求URL...");
        URIBuilder uriBuilder = new URIBuilder()
                .setHost(url);
        Set<Map.Entry<String, String>> entries = param.entrySet();
        for (Map.Entry<String, String> entry : entries){
            uriBuilder.setParameter(entry.getKey(), entry.getValue());
        }
        try {
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            return httpClientExecute(httpClient, httpGet);
        } catch (URISyntaxException e) {
            logger.error("URL拼接错误");
            throw new RuntimeException("URL拼接错误",e);
        }
    }
//    POST======================
    private CloseableHttpResponse sendPOST(CloseableHttpClient httpClient, String url, String param) throws IOException {
        logger.info("正在构造请求URL...");
        HttpGet httpGet = new HttpGet(url + "?" +  param);
        return httpClientExecute(httpClient, httpGet);
    }




    private CloseableHttpResponse httpClientExecute(CloseableHttpClient httpClient,HttpRequestBase httpRequestBase) throws IOException {
        logger.info("正在发送Http请求：" + httpRequestBase.getURI());
        CloseableHttpResponse response = httpClient.execute(httpRequestBase);
        return response;
    }

    private void httpClientClose(){
        try {
            if (this.response != null){
                this.response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        try {
            if (this.httpClient != null){
                this.httpClient.close();
            }
        }catch (Exception e){
            logger.error("HttpClient关闭失败：" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HttpClientUtil httpClientUtil = new HttpClientUtil();
//        httpClientUtil.sendGetHttp("http://114.115.171.59/img/test.jpg", "");
        httpClientUtil.sendGetHttp("https://stackoverflow.com/questions/4915414/disable-httpclient-logging/49173217#49173217", "");
    }
}
