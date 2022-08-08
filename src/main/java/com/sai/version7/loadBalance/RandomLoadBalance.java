package com.sai.version7.loadBalance;

import java.util.List;
import java.util.Random;

/**
 * @Description: 随机负载均衡
 * @author: sai
 * @date: 2022年08月08日 22:46
 */
public class RandomLoadBalance implements LoadBalance{
    @Override
    public String balance(List<String> addressList) {
        Random random = new Random();
        int choose = random.nextInt(addressList.size());
        System.out.println("随机负载均衡选中了"+choose+"服务器");
        return addressList.get(choose);
    }
}
