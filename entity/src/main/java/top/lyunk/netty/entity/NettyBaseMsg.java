package top.lyunk.netty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.CharSetUtils;
import top.lyunk.util.CharsetUtil;

import java.nio.charset.Charset;
import java.util.Date;

import static top.lyunk.netty.entity.NettyMsgType.*;

/**
 * 创建人:lyunk
 * 时间：2018-09-24 0:02
 * 说明：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NettyBaseMsg extends EchoPojo {

    private static final long serialVersionUID = 1L;

    private NettyMsgType msgType;

    public static NettyBaseMsg buildMsg(String msg){
        NettyBaseMsg message = new NettyBaseMsg();
        byte[] bytes = msg.getBytes(
                Charset.forName(
                        CharsetUtil.UTF_8));
        message.setBytes(bytes);
        message.setSumCountPackage(bytes.length);
        message.setCountPackage(1);
        message.setSendTime(System.currentTimeMillis());
        message.setReceiveUid("0");
        return message;
    }

    public static NettyBaseMsg buildMsg(String msg, NettyMsgType type){
        NettyBaseMsg message = null;
        switch (type) {
            case
                    /**
                     * 服务端发送心跳包
                     */
                    HEART_BEAT_SERVER:
                message = buildMsg("");
                message.setMsgType(type);
                break;

            /**
             * 客户端发送心跳包
             */
            case HEART_BEAT_CLIENT:
                message = buildMsg("");
                message.setMsgType(type);
                break;

            /**
             * 客户端发送给服务器的业务消息
             */
            case BUSINESS2SERVER:
                message = buildMsg(msg);
                message.setMsgType(type);
                break;

            /**
             * 服务端发送给客户端的业务消息
             */
            case BUSINESS2CLIENT:
                message = buildMsg(msg);
                message.setMsgType(type);
                break;

            /**
             * 服务器上线
             */
            case SERVER_ONLINE:
                message = buildMsg("");
                message.setMsgType(type);
                break;

            /**
             * 服务器离线
             */
            case SERVER_OFFLINE:
                message = buildMsg("");
                message.setMsgType(type);
                break;

            /**
             * 客户端上线
             */
            case CLIENT_ONLINE:
                message = buildMsg("");
                message.setMsgType(type);
                break;

            /**
             * 客户端离线
             */
            case CLIENT_OFFLINE:
                message = buildMsg("");
                message.setMsgType(type);
                break;
            /**
             * 服务器接受客户端连接请求
             */
            case SERVER_ACCEPT:
                message = buildMsg("");
                message.setMsgType(type);
                break;

            /**
             * 服务器拒绝客户端连接请求
             */
            case SERVER_REJECT:
                message = buildMsg(msg);
                message.setMsgType(type);
                break;
            /**
             * 服务端异常，客户端应持有最后的异常缓存区消息，重连后继续缓冲区消息业务处理
             */
            case SERVER_EXCEPTION:
                message = buildMsg("");
                message.setMsgType(type);
                break;

            /**
             * 客户端异常，服务器应持有最后的异常缓存区消息，重连后继续缓冲区信息业务处理
             */
            case CLIENT_EXCEPTION:
                message = buildMsg("");
                message.setMsgType(type);
                break;
            /**
             * 个人信息参数
             */
            case SYNC_PERSON:
                message = buildMsg(msg);
                message.setMsgType(type);
                break;
            /**
             * 个人信息参数只有图片
             */
            case SYNC_PERSON_IMG:
                message = buildMsg(msg);
                message.setMsgType(type);
                break;
        }
        return message;
    }

    public NettyMsgType getMsgType() {
        return msgType;
    }

    public void setMsgType(NettyMsgType msgType) {
        this.msgType = msgType;
    }
}
