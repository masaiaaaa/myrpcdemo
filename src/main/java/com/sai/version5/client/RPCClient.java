package com.sai.version5.client;


import com.sai.version5.common.RPCRequest;
import com.sai.version5.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
