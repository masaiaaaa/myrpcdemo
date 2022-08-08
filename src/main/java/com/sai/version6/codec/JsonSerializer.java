package com.sai.version6.codec;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sai.version6.common.RPCRequest;
import com.sai.version6.common.RPCResponse;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月08日 20:28
 */
public class JsonSerializer implements Serializer{
    @Override
    public byte[] serialize(Object obj) {
        return JSONObject.toJSONBytes(obj);
    }

    @Override
    public Object deserialize(byte[] bytes, int messageType) {
        Object obj = null;
        switch (messageType) {
            case 0:
                RPCRequest request = JSON.parseObject(bytes, RPCRequest.class);
                //如果参数为空，直接返回
                if(request.getParams() == null){
                    return request;
                }
                Object[] objects = new Object[request.getParams().length];

                for (int i = 0; i < objects.length; i++) {
                    Class<?> paramsType = request.getParamsTypes()[i];
                    if(!paramsType.isAssignableFrom(request.getParams()[i].getClass())){
                        objects[i] = JSONObject.toJavaObject((JSONObject)request.getParams()[i], request.getParamsTypes()[i]);
                    }else{
                        objects[i] = request.getParams()[i];
                    }
                }
                request.setParams(objects);
                obj = request;
                break;
            case 1:
                RPCResponse response = JSON.parseObject(bytes, RPCResponse.class);
                Class<?> dataType = response.getDataType();
                if(! dataType.isAssignableFrom(response.getData().getClass())){
                    response.setData(JSONObject.toJavaObject((JSONObject) response.getData(),dataType));
                }
                obj = response;
                break;
            default:
                System.out.println("暂时不支持这种消息");
                throw new RuntimeException();
        }
        return obj;
    }

    @Override
    public int getType() {
        return 1;
    }
}
