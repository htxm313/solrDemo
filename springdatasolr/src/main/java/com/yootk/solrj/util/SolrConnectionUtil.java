package com.yootk.solrj.util;

import com.yootk.solrj.util.interceptor.AuthRequestInterceptor;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.solr.client.solrj.impl.HttpClientUtil;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.params.ModifiableSolrParams;

public class SolrConnectionUtil {
    //定义要使用的Solr连接地址，连接的时候明确的连接到对应的Core上，如果有认证则使用认证信息
    public static final String SOLR_HOST_URL = "http://solr-server/solr/happy-core" ;
    public static final String USERNAME = "lee" ;   //用户名
    public static final String PASSWORD = "happy" ; //用户密码
    private SolrConnectionUtil() {}

    public static HttpSolrClient getHttpSolrClient() {
        //定义一个可以保存所有Solr基础配置信息的对象
        ModifiableSolrParams solrParams = new ModifiableSolrParams() ;
        solrParams.set(HttpClientUtil.PROP_BASIC_AUTH_USER,USERNAME) ;
        solrParams.set(HttpClientUtil.PROP_BASIC_AUTH_PASS,PASSWORD) ;
        //将拦截器整合在当前的HttpClient创建的工具类之中
        HttpClientUtil.addRequestInterceptor(new AuthRequestInterceptor()) ;
        CloseableHttpClient httpClient = HttpClientUtil.createClient(solrParams) ;//设置相关的Solr处理参数
        HttpSolrClient solrClient = new HttpSolrClient.Builder(SOLR_HOST_URL).withHttpClient(httpClient).build() ;
        return solrClient ;
    }
}
