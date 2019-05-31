package com.xuecheng.search;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: 98050
 * @Time: 2019-05-29 22:33
 * @Feature:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestSearch {

    @Autowired
    RestHighLevelClient client;

    //1.搜索type下的全部记录
    @Test
    public void testSearchAll() throws IOException {

        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());

        //source源字段过滤，第一个参数是要包含的字段，第二个是要忽略的字段
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel"},new String[]{});
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }

    //2.分页搜索
    @Test
    public void testPage() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        //1.分页设置，设置起始下标，从0开始
        searchSourceBuilder.from(0);
        //2.大小
        searchSourceBuilder.size(1);
        //3.源字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel"},new String[]{});
        searchRequest.source(searchSourceBuilder);

        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }

    //3.精确查询
    @Test
    public void testTermQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("name", "java"));
        //source源字段过滤，第一个参数是要包含的字段，第二个是要忽略的字段
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel"},new String[]{});
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }

    //4.根据id查询
    @Test
    public void testFindId() throws IOException {
        List<String> list = Arrays.asList("1","2");
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery("_id", list));
        //source源字段过滤，第一个参数是要包含的字段，第二个是要忽略的字段
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel"},new String[]{});
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }

    //5.match query
    @Test
    public void testMatchQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //source源字段过滤，第一个参数是要包含的字段，第二个是要忽略的字段
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel"},new String[]{});
        //匹配关键字
        searchSourceBuilder.query(QueryBuilders.matchQuery("description", "java基础").operator(Operator.OR));
        //searchSourceBuilder.query(QueryBuilders.matchQuery("description", "java基础").minimumShouldMatch("80%"));
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }

    //6.multi query
    @Test
    public void testMultiQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("java基础", "name","description").minimumShouldMatch("50%");
        //提升boost
        multiMatchQueryBuilder.field("name", 10);
        searchSourceBuilder.query(multiMatchQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }

    //7.bool查询
    @Test
    public void testBool() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //1.字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","description"},new String[]{});
        //2.分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(1);
        //3.multi query
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("java基础", "name","description").minimumShouldMatch("50%");
        //3.1提升boost
        multiMatchQueryBuilder.field("name", 10);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("studymodel", "201001");
        //4.布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        boolQueryBuilder.must(termQueryBuilder);
        //5.设置布尔查询对象
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }

    //8.过滤器
    @Test
    public void testFilter() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //1.字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","description"},new String[]{});
        //2.分页
        searchSourceBuilder.from(0);
        searchSourceBuilder.size(1);
        //3.multi query
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("vue", "name","description").minimumShouldMatch("50%");
        //3.1提升boost
        multiMatchQueryBuilder.field("name", 10);
        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery("studymodel", "201001");
        //4.布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        boolQueryBuilder.must(termQueryBuilder);
        //5.过滤
        boolQueryBuilder.filter(QueryBuilders.termQuery("studymodel", "201001"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(100));
        //6.设置布尔查询对象
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }

    //9.排序
    @Test
    public void testSort() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //1.字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","description"},new String[]{});
        //2.布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.filter(QueryBuilders.termQuery("studymodel", "201001"));
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(100));
        //3.排序
        searchSourceBuilder.sort(new FieldSortBuilder("studymodel").order(SortOrder.DESC));
        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.ASC));
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        printfResponse(searchResponse);
    }
    //10.高亮
    @Test
    public void testHighLight() throws IOException {
        SearchRequest searchRequest = new SearchRequest("xc_course");
        searchRequest.types("doc");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        //1.字段过滤
        searchSourceBuilder.fetchSource(new String[]{"name","studymodel","price","description"},new String[]{});
        //2.multi query
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery("java", "name","description").minimumShouldMatch("50%");
        //2.1提升boost
        multiMatchQueryBuilder.field("name", 10);
        //3.布尔查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        boolQueryBuilder.must(multiMatchQueryBuilder);
        //4.过滤
        boolQueryBuilder.filter(QueryBuilders.rangeQuery("price").gte(0).lte(100));
        //5.排序
        searchSourceBuilder.sort(new FieldSortBuilder("studymodel").order(SortOrder.DESC));
        searchSourceBuilder.sort(new FieldSortBuilder("price").order(SortOrder.ASC));
        //6.高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.preTags("<tag>");
        highlightBuilder.postTags("</tag>");
        highlightBuilder.fields().add(new HighlightBuilder.Field("name"));
        highlightBuilder.fields().add(new HighlightBuilder.Field("description"));
        searchSourceBuilder.highlighter(highlightBuilder);
        //7.设置布尔查询对象
        searchSourceBuilder.query(boolQueryBuilder);
        searchRequest.source(searchSourceBuilder);
        SearchResponse searchResponse = client.search(searchRequest);
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits){
            System.out.println("index:" + hit.getIndex());
            System.out.println("type:" + hit.getType());
            System.out.println("id:" + hit.getId());
            System.out.println("score:" + hit.getScore());
            System.out.println("source:" + hit.getSourceAsString());
            System.out.println("highlight:" + hit.getHighlightFields());
            System.out.println("----------------------------------");
        }
    }

    private void printfResponse(SearchResponse searchResponse) {
        SearchHits hits = searchResponse.getHits();
        SearchHit[] searchHits = hits.getHits();
        for (SearchHit hit : searchHits){
            System.out.println("index:" + hit.getIndex());
            System.out.println("type:" + hit.getType());
            System.out.println("id:" + hit.getId());
            System.out.println("score:" + hit.getScore());
            System.out.println("source:" + hit.getSourceAsString());
            System.out.println("----------------------------------");

        }
    }


}
