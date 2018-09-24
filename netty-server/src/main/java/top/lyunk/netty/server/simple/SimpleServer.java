package top.lyunk.netty.server.simple;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import top.lyunk.util.Global;


/**
 * 创建人:lyunk
 * 时间：2018-09-24 12:54
 * 说明：
 */
@Slf4j
public class SimpleServer {
    private int port = Global.getInteger("netty.simple.port");

    public SimpleServer() {
    }

    public SimpleServer(int port) {
        this.port = port;
    }

    public void run(){
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {
            log.info("初始化Netty");
            //服务器引擎
            ServerBootstrap b = new ServerBootstrap();
            b.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new SimpleHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);
            //绑定端口
            log.info("绑定Netty端口：" + port);
            ChannelFuture channelFuture = b.bind(port).sync();

            //等待socket关闭
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("端口绑定失败：" + port);
            log.error(e.getMessage());
        }finally {
            //释放资源
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
//        SimpleServer simpleServer = new SimpleServer(30001);
        SimpleServer simpleServer = new SimpleServer();
        simpleServer.run();
    }
}
