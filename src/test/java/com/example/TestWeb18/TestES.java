package com.example.TestWeb18;

import com.example.TestWeb18.util.ElasticsearchConnectionUtil;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestES
{
    @Test
    public void testEsSave() throws IOException {
        ElasticsearchConnectionUtil es = ElasticsearchConnectionUtil.getElasticsearchConnectionUtil();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "datou");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch3");

        String index = "twitter";
        String type = "_doc";
        String documentId = "4";
        es.save(index, type, documentId, jsonMap);

    }

}
