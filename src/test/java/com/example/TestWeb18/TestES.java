package com.example.TestWeb18;

import com.example.TestWeb18.entity.TblOrders;
import com.example.TestWeb18.util.ElasticsearchConnectionUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.*;

public class TestES
{
    @Test
    public void testEsSave() throws IOException {
        ElasticsearchConnectionUtil es = ElasticsearchConnectionUtil.getElasticsearchConnectionUtil();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "datou");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch3delete");

        String index = "twitter";
        String type = "_doc";
        String documentId = "5";
        es.save(index, type, documentId, jsonMap);

    }

    @Test
    public void testEsGet() throws IOException {
        ElasticsearchConnectionUtil es = ElasticsearchConnectionUtil.getElasticsearchConnectionUtil();
        String index = "twitter";
        // es7.0默认为_doc
        String type = "_doc";
        String id = "4";
        Map<String, Object> map = es.get(index, type, id);
        System.out.println(map);
    }

    @Test
    public void testEsExists() throws IOException {
        ElasticsearchConnectionUtil es = ElasticsearchConnectionUtil.getElasticsearchConnectionUtil();
        String index = "twitter";
        // es7.0默认为_doc
        String type = "_doc";
        String id = "4";
        boolean bol = es.isExist(index, type, id);
        System.out.println(bol);
    }

    @Test
    public void tetsEsUpdate() throws IOException {
        ElasticsearchConnectionUtil es = ElasticsearchConnectionUtil.getElasticsearchConnectionUtil();
        String index = "twitter";
        // es7.0默认为_doc
        String type = "_doc";
        String id = "4";

        Map<String, Object> jsonMap = new HashMap<String, Object>();
        jsonMap.put("updated", new Date());
        jsonMap.put("reason", "daily update22");

        es.update(index, type, id, jsonMap);
    }

    @Test
    public void testEsDelete() throws IOException {
        ElasticsearchConnectionUtil es = ElasticsearchConnectionUtil.getElasticsearchConnectionUtil();
        String index = "twitter";
        // es7.0默认为_doc
        String type = "_doc";
        String id = "3ZX3pXEBeqV-LU34P23b";
        es.delete(index, type, id);
    }

    @Test
    public void testBulkSave() throws IOException {
        ElasticsearchConnectionUtil es = ElasticsearchConnectionUtil.getElasticsearchConnectionUtil();

        List<TblOrders> list = new ArrayList<TblOrders>();
        TblOrders tblOrder = new TblOrders();
        tblOrder.setOrderId("111-222-112");
        tblOrder.setStoreId(48);
        tblOrder.setOrderNumber(10003);
        tblOrder.setAddress("US2");
        list.add(tblOrder);

        TblOrders tblOrder2 = new TblOrders();
        tblOrder2.setOrderId("111-222-113");
        tblOrder2.setStoreId(48);
        tblOrder2.setOrderNumber(10004);
        tblOrder2.setAddress("Russia2");
        list.add(tblOrder2);

        es.bulkSave(list);

    }

}
