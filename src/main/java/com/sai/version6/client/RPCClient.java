package com.sai.version6.client;


import com.sai.version6.common.RPCResponse;
import com.sai.version6.common.RPCRequest;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
