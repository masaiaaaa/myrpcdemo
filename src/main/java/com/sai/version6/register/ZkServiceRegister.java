package com.sai.version6.register;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * @Description: TODO
 * @author: sai
 * @date: 2022年08月08日 21:37
 */
public class ZkServiceRegister implements ServiceRegister{
    //curator提供的zk客户端
    private CuratorFramework client;
    //zk根路径节点
    private static final String ROOT_PATH = "MyRPC";

    public ZkServiceRegister(){
        RetryPolicy policy = new ExponentialBackoffRetry(1000, 3);

        this.client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181")
                .sessionTimeoutMs(40000)
                .retryPolicy(policy)
                .namespace(ROOT_PATH)
                .build();
        this.client.start();
        System.out.println("zk 连接成功");
    }
    @Override
    public void register(String serviceName, InetSocketAddress serverAddress) {
        try {
            if(client.checkExists().forPath("/"+serviceName) == null){
                // serviceName创建成永久节点，服务提供者下线时，不删服务名，只删地址
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath("/" + serviceName);
                //路径地址，一个/代表一个节点
                String path = "/" + serviceName + "/" + getServiceAddress(serverAddress);
                // 临时节点，服务器下线就删除节点
                client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.EPHEMERAL)
                        .forPath(path);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("此服务已存在");
        }
    }

    // 根据服务名返回地址
    @Override
    public InetSocketAddress serviceDiscovery(String serviceName) {

        try {
            List<String> strings = client.getChildren()
                    .forPath("/" + serviceName);
            //默认用第一个
            String string = strings.get(0);
            return parseAddress(string);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getServiceAddress(InetSocketAddress serverAddress){
        return serverAddress.getHostName()
                + ":" +
                serverAddress.getPort();
    }
    private InetSocketAddress parseAddress(String address){
        String[] result = address.split(":");
        return new InetSocketAddress(result[0],Integer.parseInt(result[1]));
    }
}
