package com.sai.version4.client;

import com.sai.version4.common.RPCRequest;
import com.sai.version4.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
    @Override
    public RPCResponse sendRequest(RPCRequest request) {

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
