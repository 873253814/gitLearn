package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.CharsetUtil;


public class MyClientServer extends ChannelInboundHandlerAdapter {

    public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelUnregistered(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelHandlerContext.writeAndFlush(Unpooled.copiedBuffer("歪比巴卜~茉莉~Are you good~马来西亚~", CharsetUtil.UTF_8));
    }

    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) {
        //接收服务端发送过来的消息
        ByteBuf byteBuf = (ByteBuf) o;
        System.out.println("收到服务端" + channelHandlerContext.channel().remoteAddress() + "的消息：" + byteBuf.toString(CharsetUtil.UTF_8));
    }

    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

    }

    public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

    }
}
