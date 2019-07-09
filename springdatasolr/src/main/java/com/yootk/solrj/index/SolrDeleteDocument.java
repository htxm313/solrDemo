package com.yootk.solrj.index;

import com.yootk.solrj.util.SolrConnectionUtil;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.UpdateResponse;

import java.io.IOException;

public class SolrDeleteDocument {
    public static void main(String[] args) throws IOException, SolrServerException {
        HttpSolrClient solrClient = SolrConnectionUtil.getHttpSolrClient() ;
        UpdateResponse response = solrClient.deleteById("989") ;
        System.out.println("【花费时间】" + response.getElapsedTime());
        solrClient.commit() ;   //提交修改
        solrClient.close();
    }
}
