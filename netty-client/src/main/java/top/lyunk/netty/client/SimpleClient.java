package top.lyunk.netty.client;


import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.lyunk.util.Global;

import java.net.Socket;

/**
 * 创建人:lyunk
 * 时间：2018-09-24 15:38
 * 说明：
 */
public class SimpleClient {
    private static final String SERVER_IP = Global.getProperty("netty.server.ip");

    private static final int SERVER_PORT = Global.getInteger("netty.simple.port");

    public void run(){
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(work)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE,true )
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new SimpleClientHandler());
                        }
                    });

//            开启客户端

            ChannelFuture f = b.connect(SERVER_IP, SERVER_PORT).sync();

//            等待连接关闭
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
