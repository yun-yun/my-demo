package top.lyunk.util;

import org.apache.log4j.Logger;

/**
 * 创建人:lyunk
 * 时间：2018-09-24 14:08
 * 说明：
 */
public class Global {
    public static Logger logger = Logger.getLogger(Global.class);

    private static PropertiesLoader loader = new PropertiesLoader("system.properties");


    /**
     * 取出String类型的Property，但以System的Property优先,如果都为Null则抛出异常.
     */
    public static String getProperty(String key) {
        return loader.getProperty(key);
    }

    /**
     * 取出String类型的Property，但以System的Property优先.如果都为Null则返回Default值.
     */
    public static String getProperty(String key, String defaultValue) {
        return loader.getProperty(key,defaultValue);
    }

    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public static Integer getInteger(String key) {
        return loader.getInteger(key);
    }

    /**
     * 取出Integer类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public static Integer getInteger(String key, Integer defaultValue) {
        return loader.getInteger(key,defaultValue);
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null或内容错误则抛出异常.
     */
    public static Double getDouble(String key) {
        return loader.getDouble(key);
    }

    /**
     * 取出Double类型的Property，但以System的Property优先.如果都为Null则返回Default值，如果内容错误则抛出异常
     */
    public static Double getDouble(String key, Integer defaultValue) {
        return loader.getDouble(key,defaultValue);
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null抛出异常,如果内容不是true/false则返回false.
     */
    public static Boolean getBoolean(String key) {
        return loader.getBoolean(key);
    }

    /**
     * 取出Boolean类型的Property，但以System的Property优先.如果都为Null则返回Default值,如果内容不为true/false则返回false.
     */
    public static Boolean getBoolean(String key, boolean defaultValue) {
        return loader.getBoolean(key,defaultValue);
    }
}
