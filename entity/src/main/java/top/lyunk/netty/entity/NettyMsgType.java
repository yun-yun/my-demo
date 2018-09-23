package top.lyunk.netty.entity;


/**
 * 创建人:lyunk
 * 时间：2018-09-24 0:02
 * 说明：Netty消息类型
 */
public enum NettyMsgType {
    /**
     * 服务端发送心跳包
     */
    HEART_BEAT_SERVER(10001,"HEART_BEAT_SERVER","服务端发送心跳包"),

    /**
     * 客户端发送心跳包
     */
    HEART_BEAT_CLIENT(10002,"HEART_BEAT_CLIENT","客户端发送心跳包"),

    /**
     * 客户端发送给服务器的业务消息
     */
    BUSINESS2SERVER(10003,"BUSINESS2SERVER","客户端发送给服务器的业务消息"),

    /**
     * 服务端发送给客户端的业务消息
     */
    BUSINESS2CLIENT(10004,"BUSINESS2CLIENT","服务端发送给客户端的业务消息"),

    /**
     * 服务器上线
     */
    SERVER_ONLINE(10005,"SERVER_ONLINE","服务器上线"),

    /**
     * 服务器离线
     */
    SERVER_OFFLINE(10006,"SERVER_OFFLINE","服务器离线"),

    /**
     * 客户端上线
     */
    CLIENT_ONLINE(10007,"CLIENT_ONLINE","客户端上线"),

    /**
     * 客户端离线
     */
    CLIENT_OFFLINE(10008,"CLIENT_OFFLINE","客户端离线"),

    /**
     * 服务器接受客户端连接请求
     */
    SERVER_ACCEPT(10009,"SERVER_ACCEPT","服务器接受客户端连接请求"),

    /**
     * 服务器拒绝客户端连接请求
     */
    SERVER_REJECT(10010,"SERVER_REJECT","服务器拒绝客户端连接请求"),

    /**
     * 服务端异常，客户端应持有最后的异常缓存区消息，重连后继续缓冲区消息业务处理
     */
    SERVER_EXCEPTION(10011,"HEART_BEAT_SERVER","服务端异常，客户端应持有最后的异常缓存区消息，重连后继续缓冲区消息业务处理"),

    /**
     * 客户端异常，服务器应持有最后的异常缓存区消息，重连后继续缓冲区信息业务处理
     */
    CLIENT_EXCEPTION(10012,"CLIENT_EXCEPTION","客户端异常，服务器应持有最后的异常缓存区消息，重连后继续缓冲区信息业务处理"),
    /**
     * 个人信息参数
     */
    SYNC_PERSON(10013,"HEART_BEAT_SERVER","个人信息参数"),
    /**
     * 个人信息只有图片
     */
    SYNC_PERSON_IMG(10014,"HEART_BEAT_SERVER","个人信息只有图片");

    /**
     * 事件码
     */
    private int code;
    /**
     * 事件信息
     */
    private String msg;
    /**
     * 事件描述
     */
    private String explain;

    NettyMsgType() {
    }

    NettyMsgType(int code, String msg, String explain) {
        this.code = code;
        this.msg = msg;
        this.explain = explain;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getExplain() {
        return explain;
    }
}
