package com.sai.version2.client;

import com.sai.version2.common.RPCRequest;
import com.sai.version2.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: request动态代理
 * @author: sai
 * @date: 2022年06月20日 22:32
 */
@AllArgsConstructor
public class ClientProxy implements InvocationHandler {
    private String host;
    private int port;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RPCRequest request = RPCRequest.builder().interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args).paramTypes(method.getParameterTypes()).build();
        RPCResponse response = IOClient.sendRequest(host, port, request);
        System.out.println(response);
        return response.getData();
    }
    <T>T getProxy(Class<T> clazz){
        Object object = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)object;
    }
}
