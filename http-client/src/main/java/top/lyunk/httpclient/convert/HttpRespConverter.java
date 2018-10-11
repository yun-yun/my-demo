package top.lyunk.httpclient.convert;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

/**
 * 创建人:lyunk
 * 时间：2018-10-11 14:05
 * 说明：
 */
public abstract class HttpRespConverter {

    /**
     * 所有请求头
     */
    private Header[] allHeaders;
    /**
     * 本地化
     * 例如：zh_CN
     */
    private Locale locale;
    /**
     * 返回的状态码
     * 例如：200
     */
    private int statusCode;
    /**
     * 返回的状态码描述
     * 例如：OK
     */
    private String reasonPhrase;
    /**
     * 状态栏
     * 例如：HTTP/1.1 200 OK
     */
    private String statusLine;
    /**
     * 协议版本
     * 例如：HTTP/1.1
     */
    private String protocolVersion;
    /**
     * 协议
     * 例如：HTTP
     */
    private String protocol;
    /**
     * 主要版本
     * 例如：HTTP/1.1中的第一个1
     */
    private int major;
    /**
     * 次要版本
     * 例如：HTTP/1.1中的第二个1
     */
    private int minor;

    public abstract void convert(InputStream is) throws IOException;

    private void convert(HttpEntity entity) throws IOException {
        InputStream is = entity.getContent();
        convert(is);
    }

    public void convert(CloseableHttpResponse response) throws IOException {
        this.allHeaders = response.getAllHeaders();
        this.locale = response.getLocale();
        StatusLine statusLine = response.getStatusLine();
        this.statusLine = statusLine.toString();
        this.statusCode = statusLine.getStatusCode();
        this.reasonPhrase = statusLine.getReasonPhrase();
        ProtocolVersion protocolVersion = statusLine.getProtocolVersion();
        this.protocolVersion = protocolVersion.toString();
        this.protocol = protocolVersion.getProtocol();
        this.major = protocolVersion.getMajor();
        this.minor = protocolVersion.getMinor();
        convert(response.getEntity());
        response.close();
    }

    public Header[] getAllHeaders() {
        return allHeaders;
    }

    public Locale getLocale() {
        return locale;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public String getStatusLine() {
        return statusLine;
    }

    public String getProtocolVersion() {
        return protocolVersion;
    }

    public String getProtocol() {
        return protocol;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }
}
