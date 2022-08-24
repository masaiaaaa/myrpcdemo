package com.sai.version7.client;

import com.sai.version7.common.RPCRequest;
import com.sai.version7.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
