package com.bdqn.util;

import com.alibaba.fastjson.JSON;
import com.bdqn.entity.ItripHotel;
import com.bdqn.entity.JsonRootBean;
import com.bdqn.entity.ListObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.cluster.Health;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;

import java.io.IOException;

public class TestES {

    public static void main(String[] args) throws Exception {
        search();
    }

    //查询
    public static void Heath() throws Exception {
        //通过searchbox包实例化健康值对象
        Health health = new Health.Builder().build();
        //调用jestClinet
        JestClient client = Client.getInstance().getJestClient();
        //调用集群返回测试结果
        JestResult result = client.execute(health);
        System.out.println(result.getJsonString());
    }

    //插入
    public static void insert() throws Exception {

        for (int i = 0; i < 100; i++) {
            ItripHotel user = new ItripHotel();
            user.setId(i);
            user.setHotelName("fanqi" + i);
            //构建一个索引
            Index index = new Index.Builder(user).index("userdb").type("user").id("" + i).build();
            //执行
            JestClient jestClient = Client.getInstance().getJestClient();
            DocumentResult result = jestClient.execute(index);
            System.out.println(result.getJsonString());
        }
    }

    //根据id删除
    public static void delete() throws Exception {
        Delete index = new Delete.Builder("5").index("userdb").type("user").build();
        //执行
        JestClient jestClient = Client.getInstance().getJestClient();
        DocumentResult result = jestClient.execute(index);
        System.out.println(result.getJsonString());
    }

    public static void search() throws IOException {
        Search search = new Search.Builder("").addIndex("itripdb").addType("logs").build();
        JestClient jestClient = Client.getInstance().getJestClient();
        JestResult result = jestClient.execute(search);
        System.out.println(result.getJsonString());
        JsonRootBean rootBean = JSON.parseObject(result.getJsonString(), JsonRootBean.class);
        for (ListObject obj : rootBean.getHits().getHits()) {
            System.out.println(obj._source.getHotelName());
        }
    }
}
