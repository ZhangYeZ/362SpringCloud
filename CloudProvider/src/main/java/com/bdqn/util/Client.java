package com.bdqn.util;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;

import java.util.Arrays;

/***
 * 双重锁+单例模式中的饿汉模式
 */
public class Client {

    public Client()
    {
        String [] list=new String []{"http://192.168.134.129:9200","http://192.168.134.129:9300"};
        JestClientFactory
                factory=new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(Arrays.asList(list)).build());
        jestClient = factory.getObject();
    }
    //jestClient 是jest 封装的客户对象
    public static JestClient jestClient;
    //当前
    public static volatile Client client;

    //添加JestClient 客户端的方法
    public static JestClient getJestClient()
    {
        return jestClient;
    }

    //单例模式 实例有且只有一个
    public synchronized static Client getInstance()
    {
        if(client==null)
        {
            client=new Client();
        }
        return client;
    }
}
