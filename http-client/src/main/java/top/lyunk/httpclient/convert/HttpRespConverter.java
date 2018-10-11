package top.lyunk.httpclient.convert;

import java.io.InputStream;

/**
 * 创建人:lyunk
 * 时间：2018-10-11 14:05
 * 说明：
 */
public abstract class HttpRespConverter {

    public Object convert(InputStream is){
        return is;
    }

}
