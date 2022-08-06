package com.sai.version4.client;

import com.sai.version4.common.RPCRequest;
import com.sai.version4.common.RPCResponse;

public interface RPCClient {
    RPCResponse sendRequest(RPCRequest request);
}
