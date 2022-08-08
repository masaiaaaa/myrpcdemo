package com.sai.version7.loadBalance;

import java.util.List;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月08日 22:46
 */
public class RoundLoadBalance implements LoadBalance{
    private int choose = -1;

    @Override
    public String balance(List<String> addressList) {
        choose++;
        choose = choose % addressList.size();
        return addressList.get(choose);
    }
}
