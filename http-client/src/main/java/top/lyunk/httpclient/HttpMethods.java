package top.lyunk.httpclient;

/**
 * 创建人:lyunk
 * 时间：2018-10-11 10:43
 * 说明：
 */
public enum HttpMethods {
    GET("GET")
    , POST("POST")
    , HEAD("HEAD")
    , PUT("PUT")
    , DELETE("DELETE")
    , TRACE("TRACE")
    , OPTIONS("OPTIONS");

    String name;


    HttpMethods(String name) {
        this.name = name;
    }

}
