package com.example.TestWeb18.util;

import org.apache.http.HttpHost;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.VersionType;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

public class ElasticsearchConnectionUtil
{
    private final static String HOSTNAME = "127.0.0.1";

    private final static Integer PORT = 9200;

    private final static String SCHEME = "http";

    private static Logger logger = LoggerFactory.getLogger(ElasticsearchConnectionUtil.class);

    private RestHighLevelClient client = null;

    private static ElasticsearchConnectionUtil elasticsearchConnectionUtil = null;

    static {
        elasticsearchConnectionUtil = new ElasticsearchConnectionUtil();
    }

    private ElasticsearchConnectionUtil() {
        logger.info("---------------------------------执行构造器-----------------------------");
        this.client = getClient();
    }

    private static RestHighLevelClient getClient() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost(HOSTNAME, PORT, SCHEME)



                        ));

        return client;
    }

    public void save(String index, String type, String documentId, Map<String, Object> jsonMap) throws IOException {
        if(this.client == null) {
            this.client = getClient();
        }

        //IndexResponse indexResponse = new IndexResponse();

        /*jsonMap.put("user", "datou");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch2");*/
        IndexRequest indexRequest = new IndexRequest(index).type(type)
                .source(jsonMap);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        String index2 = indexResponse.getIndex();
        String id2 = indexResponse.getId();

        System.out.println("save success: id=" + id2);

    }

    public void get(String index, String type, String id) {
        if(this.client == null) {
            this.client = getClient();
        }

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "jimmy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch2");
        IndexRequest indexRequest = new IndexRequest("posts")
                .id("4").source(jsonMap);


    }

    public static void main(String[] args) throws IOException {
        logger.info("开始连接ES.");
        Settings settings = Settings.builder()
                .put("", "")
                .put("cluster.name", "myClusterName").build();

        // on startup
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                //.addTransportAddress(new TransportAddress(InetAddress.getByName("LUOZHIWEI"), 9300));
                .addTransportAddress(new TransportAddress(new InetSocketAddress(HOSTNAME, PORT)));



        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user","kimchy");
        json.put("postDate",new Date());
        json.put("message","trying out Elasticsearch");


        IndexResponse response3 = client.prepareIndex("twitter", "_doc", "1")
                .setSource(XContentFactory.jsonBuilder()
                        .startObject()
                        .field("user", "kimchy")
                        .field("postDate", new Date())
                        .field("message", "trying out Elasticsearch")
                        .endObject()
                )
                .get();

        GetResponse response = client.prepareGet("twitter", "_doc", "1").get();

        //Aggregation aggregation = new Aggregation();
        SearchResponse response2 = client.prepareSearch("twitter").get();

        // Index name
        String _index = response.getIndex();
        // Type name
        String _type = response.getType();
        // Document ID (generated or not)
        String _id = response.getId();
        // Version (if it's the first time you index this document, you will get: 1)
        long _version = response.getVersion();
        // status has stored current instance statement.
        //RestStatus status = response.status();

        logger.info("连接成功.");
        // on shutdown

        client.close();
        logger.info("关闭ES连接.");

    }

    public static ElasticsearchConnectionUtil getElasticsearchConnectionUtil() {
        return elasticsearchConnectionUtil;
    }
}
