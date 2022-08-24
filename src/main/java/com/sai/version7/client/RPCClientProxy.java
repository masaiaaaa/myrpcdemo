package com.sai.version7.client;


import com.sai.version7.common.RPCRequest;
import com.sai.version7.common.RPCResponse;
import lombok.AllArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月06日 15:58
 */
@AllArgsConstructor
public class RPCClientProxy implements InvocationHandler {
   private RPCClient client;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //创建请求
        RPCRequest request = RPCRequest.builder()
                .interfaceName(method.getDeclaringClass().getName())
                .methodName(method.getName())
                .params(args)
                .paramsTypes(method.getParameterTypes())
                .build();
        //数据传输
        RPCResponse response = client.sendRequest(request);
        return response.getData();
    }
    <T>T getProxy(Class<T> clazz){
        Object o = Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, this);
        return (T)o;
    }
}
