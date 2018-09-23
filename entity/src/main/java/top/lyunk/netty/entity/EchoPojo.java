package top.lyunk.netty.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 创建人:lyunk
 * 时间：2018-09-24 0:02
 * 说明：消息包
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class EchoPojo implements Serializable {

    private static final long serialVersionUID = 4010249994097151671L;

    /**
     * 总包数
     */
    private int sumCountPackage;

    /**
     * 当前包数
     */
    private int countPackage;

    /**
     * 交换信息数据字节
     */
    private byte[] bytes;

    /**
     * 发送人业务id
     */
    private String sendUid;

    /**
     * 接收人业务id (0 接收目标为系统 其他为业务id)
     */
    private String receiveUid;

    /**
     * 发送包时间
     */
    private long sendTime;

    /**
     * 接收包时间
     */
    private long receiveTime;
}
