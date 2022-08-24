package com.sai.version7.server;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 14:41
 */



import com.sai.version7.common.RPCRequest;
import com.sai.version7.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * 这里负责解析得到的request请求，执行服务方法，返回给客户端
 * 1. 从request得到interfaceName 2. 根据interfaceName在serviceProvide Map中获取服务端的实现类
 * 3. 从request中得到方法名，参数， 利用反射执行服务中的方法 4. 得到结果，封装成response，写入socket
 */
@AllArgsConstructor
public class WorkThread implements Runnable{
    private Socket socket;
    private ServiceProvider serviceProvider;
    @Override
    public void run() {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            //读取客户端传来的request
            RPCRequest request = (RPCRequest)objectInputStream.readObject();

            RPCResponse response = getResponse(request);

            //写入到客户端
            objectOutputStream.writeObject(response);
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("从IO中读取数据错误");
        }

    }

    public RPCResponse getResponse(RPCRequest request){
        //获取服务名
        String interfaceName = request.getInterfaceName();
        //获取服务端对应服务实现类
        Object service = serviceProvider.getService(interfaceName);

        try {
            Method method = service.getClass().getMethod(request.getMethodName(), request.getParamsTypes());
            Object invoke = method.invoke(service, request.getParams());
            return RPCResponse.success(invoke);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            System.out.println("方法执行错误");
            return RPCResponse.fail();
        }

    }

}
