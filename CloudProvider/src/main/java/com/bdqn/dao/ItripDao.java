package com.bdqn.dao;

import com.alibaba.fastjson.JSON;
import com.bdqn.entity.JsonRootBean;
import com.bdqn.entity.ListObject;
import com.bdqn.util.Client;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;


public class ItripDao {


    public static void main(String[] args) throws Exception {
        search();
    }


    public static void search() throws Exception {
        String str = "{\n" +
                "  \"from\": 0,\n" +
                "  \"size\": 10,\n" +
                "  \"sort\": [\n" +
                "    {\n" +
                "      \"id\": {\n" +
                "        \"order\": \"asc\"\n" +       //备注：asc是表示升序，desc表示降序。
                "      }\n" +
                "    }\n" +
                "  ], \n" +
                "  \"query\": {\n" +
                "    \"match\": {\n" +
                "      \"hotelName\":\"酒店\"\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Search search = new Search.Builder(str).addIndex("itripdb").addType("logs").build();
        JestClient jestClient = Client.getInstance().getJestClient();        //调用集群返回测试结果
        SearchResult result = jestClient.execute(search);
        //把es查询json结果转成java对象
        JsonRootBean rootBean = JSON.parseObject(result.getJsonString(), JsonRootBean.class);
        for (ListObject obj : rootBean.getHits().getHits()) {
            System.out.println(obj._source.getHotelName());
        }
    }
}
