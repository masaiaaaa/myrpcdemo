package com.sai.version3.server;



/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 11:35
 */
public interface RPCServer {
    void start(int port);
    void stop();
}
