package com.example.TestWeb18.util;

import org.apache.http.HttpHost;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class ElasticsearchConnectionUtil
{
    private final static String HOSTNAME = "127.0.0.1";

    private final static Integer PORT = 9200;

    private final static String SCHEME = "http";

    private static Logger logger = LoggerFactory.getLogger(ElasticsearchConnectionUtil.class);

    private RestHighLevelClient client;

    private static ElasticsearchConnectionUtil elasticsearchConnectionUtil;

    static {
        elasticsearchConnectionUtil = new ElasticsearchConnectionUtil();
    }

    private ElasticsearchConnectionUtil() {
        logger.info("---------------------------------执行构造器-----------------------------");
        this.client = getClient();
    }

    private static RestHighLevelClient getClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost(HOSTNAME, PORT, SCHEME)));
    }

    public void save(String index, String type, String documentId, Map<String, Object> jsonMap) throws IOException {
        if (this.client == null) {
            this.client = getClient();
        }

        IndexRequest indexRequest = new IndexRequest(index, type, documentId);
        indexRequest.source(jsonMap);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);

        System.out.println("result: " + indexResponse.getResult());

    }

    public Map<String, Object> get(String index, String type, String id) throws IOException {
        if (this.client == null) {
            this.client = getClient();
        }
        GetRequest getRequest = new GetRequest(index, type, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        return getResponse.getSource();
    }

    public boolean isExist(String index, String type, String id) throws IOException {
        if (this.client == null) {
            this.client = getClient();
        }

        GetRequest getRequest = new GetRequest(index, type, id);

        return client.exists(getRequest, RequestOptions.DEFAULT);
    }

    public void update(String index, String type, String id, Map<String, Object> jsonMap) throws IOException {
        if (this.client == null) {
            this.client = getClient();
        }

        UpdateRequest request = new UpdateRequest(index, type, id);
        request.doc(jsonMap);

        UpdateResponse updateResponse = client.update(request, RequestOptions.DEFAULT);

        System.out.println("result: " + updateResponse.getResult());

    }

    public void delete(String index, String type, String id) throws IOException {
        if (this.client == null) {
            this.client = getClient();
        }

        DeleteRequest request = new DeleteRequest(index, type, id);

        DeleteResponse deleteResponse = client.delete(request, RequestOptions.DEFAULT);

        System.out.println("result: " + deleteResponse.getResult());

    }

    public static void main(String[] args) {
        /*logger.info("开始连接ES.");
        Settings settings = Settings.builder().put("", "").put("cluster.name", "myClusterName").build();

        // on startup
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                //.addTransportAddress(new TransportAddress(InetAddress.getByName("LUOZHIWEI"), 9300));
                .addTransportAddress(new TransportAddress(new InetSocketAddress(HOSTNAME, PORT)));

        Map<String, Object> json = new HashMap<String, Object>();
        json.put("user", "kimchy");
        json.put("postDate", new Date());
        json.put("message", "trying out Elasticsearch");

        IndexResponse response3 = client.prepareIndex("twitter", "_doc", "1").setSource(XContentFactory.jsonBuilder().startObject().field("user", "kimchy").field("postDate", new Date()).field("message", "trying out Elasticsearch").endObject()).get();

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
        logger.info("关闭ES连接.");*/

    }

    public static ElasticsearchConnectionUtil getElasticsearchConnectionUtil() {
        return elasticsearchConnectionUtil;
    }
}
