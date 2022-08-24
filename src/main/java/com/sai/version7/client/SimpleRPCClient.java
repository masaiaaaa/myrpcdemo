package com.sai.version7.client;

import com.sai.version7.common.RPCRequest;
import com.sai.version7.common.RPCResponse;
import com.sai.version7.register.ServiceRegister;
import com.sai.version7.register.ZkServiceRegister;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 15:51
 */
@AllArgsConstructor
public class SimpleRPCClient implements RPCClient{
    private String host;
    private int port;

    private ServiceRegister serviceRegister;

    public SimpleRPCClient(){
        this.serviceRegister = new ZkServiceRegister();
    }
    @Override
    public RPCResponse sendRequest(RPCRequest request) {

        //从注册中心获取host、port
        InetSocketAddress address = serviceRegister.serviceDiscovery(request.getInterfaceName());

        host = address.getHostName();
        port = address.getPort();

        Socket socket = null;
        try {
            socket = new Socket(host, port);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());

            System.out.println(request);

            objectOutputStream.writeObject(request);
            objectOutputStream.flush();

            RPCResponse response = (RPCResponse)objectInputStream.readObject();
            return response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
