package com.qingshixun.project.eshop.module.search.service;


import java.util.List;
import java.util.Map;
//import java.util.Iterator;

import org.apache.solr.client.solrj.SolrQuery;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import com.qingshixun.project.eshop.web.BaseService;

@Service
public class SearchService extends BaseService {
	   private static final String  SOLR_URL ="http://localhost:8080/solr/qsx_product";
	   private HttpSolrClient client = new HttpSolrClient.Builder(SOLR_URL).build();
	
	   @Cacheable(value = "searchVo", key = "#root.args[0]", unless = "#result eq null ")
	   public List<SolrDocument> getSearch(String keyword) throws Exception{
		
		
		SolrQuery solrquery=new SolrQuery();
		StringBuilder params=new StringBuilder("name:"+keyword+"~0.5");
		params.append(" OR description:"+keyword+"~0.5");
		//查询条件
		solrquery.set("q",params.toString());
		//默认域
		solrquery.set("df", "keywords");
		//分页
		solrquery.setStart(0);
	    solrquery.setRows(15);
	    //高亮
        solrquery.setHighlight(true);
        
        solrquery.addHighlightField("name");
        
        solrquery.setHighlightSimplePre("<font color=\"#FF0000\">");
        
        solrquery.setHighlightSimplePost("</font>");

        //查询
		QueryResponse queryResponse = client.query(solrquery);
		SolrDocumentList results = queryResponse.getResults();
		
		//获取高亮
		 SolrDocumentList results1 = new SolrDocumentList(); 
	     SolrDocument document = new SolrDocument(); 
		 // 第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段名 
        Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();           
        //System.out.println("map的内容： " + map); //用于测试map中的值 
         
        for (int i = 0; i < results.size(); i++) { 
        	document = results.get(i);                 
            //System.out.println("document的内容： " + document); //用于测试document中的值 
             
            //将document的name字段值设置为从map映射中得到相应id文档的name字段值 
        	document.setField("name", map.get(document.getFieldValue("id")).get("name"));                
            
        	results1.add(document); 
        }
        
        
        
//		//打印
//		Iterator<SolrDocument> iter = results.iterator();
//		while (iter.hasNext()) {
//			 SolrDocument doc = iter.next();
//			 String n=doc.getFieldValue("name").toString();
//			 String d=doc.getFieldValue("description").toString();
//			 String p=doc.getFieldValue("productImage").toString();
//			 System.out.println(n);
//			 System.out.println(d);
//			 System.out.println(p);
//		}
		
		return results1;
	}

}
