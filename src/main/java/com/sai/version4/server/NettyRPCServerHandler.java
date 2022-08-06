package com.sai.version4.server;

import com.sai.version4.common.RPCRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.AllArgsConstructor;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 16:17
 */
@AllArgsConstructor
public class NettyRPCServerHandler extends SimpleChannelInboundHandler<RPCRequest> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCRequest rpcRequest) throws Exception {

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
