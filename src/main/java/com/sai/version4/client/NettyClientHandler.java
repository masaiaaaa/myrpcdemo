package com.sai.version4.client;

import com.sai.version4.common.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.AttributeKey;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月08日 14:45
 */
public class NettyClientHandler extends SimpleChannelInboundHandler<RPCResponse> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, RPCResponse rpcResponse) throws Exception {
        AttributeKey<RPCResponse> key = AttributeKey.valueOf("RPCResponse");
        ctx.channel().attr(key).set(rpcResponse);
        ctx.channel().close();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
