package com.shangbao.common;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.shangbao.model.Article;

@Component("postData")
public class PostData {
	public long sendArticle(JSONObject json){
		HttpClient client = new DefaultHttpClient();	
		Properties props = new Properties();
		String url="";
		try {
			props = PropertiesLoaderUtils
					.loadAllProperties("config.properties");
			url = props.getProperty("HttpPost_URL");// 上传评论的url地址
      		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		HttpPost post = new HttpPost(url);
		long entity = 0;
		try {
//			System.out.println(json.toString());
			StringEntity s = new StringEntity(json.toString(),HTTP.UTF_8);
			s.setContentEncoding("utf-8");
			s.setContentType("application/json");
			post.setEntity(s);

			HttpResponse res = client.execute(post);
			int statusCode = res.getStatusLine().getStatusCode();
			
			entity = Long.parseLong(EntityUtils.toString(res.getEntity()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return entity;
	}
	
	public String sendComment(JSONObject jsonComment,long id){
		HttpClient client = new DefaultHttpClient();
		Properties props = new Properties();
		String url="";
		try {
			props = PropertiesLoaderUtils
					.loadAllProperties("config.properties");
			url = props.getProperty("HttpPost_comment");// 上传评论的url地址
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		url = url+id;
		HttpPost post = new HttpPost(url);
		String entity = "";
		try {
			StringEntity s = new StringEntity(jsonComment.toString(),HTTP.UTF_8);
			s.setContentEncoding("utf-8");
			s.setContentType("application/json");
			post.setEntity(s);

			HttpResponse res = client.execute(post);
			int statusCode = res.getStatusLine().getStatusCode();
//			System.out.println("status:" + statusCode);
			
			entity = EntityUtils.toString(res.getEntity());		
			System.out.println();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return entity;
	}

	public String sendPicture(String path){
		path ="D:\\imageGuoNei\\201412050090001.jpg";
		HttpClient client = new DefaultHttpClient();
		Properties props = new Properties();
		String url="";
		try {
			props = PropertiesLoaderUtils
					.loadAllProperties("config.properties");
			url = props.getProperty("HttpPost_picture");// 上传评论的url地址
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		HttpPost post = new HttpPost(url);
		String entity = "";
		try {
			File file = new File(path);
			MultipartEntity s = new MultipartEntity();
			ContentBody contentBody = new FileBody(file);
			s.addPart("file",contentBody);
			post.setEntity(s);
			
			HttpResponse res = client.execute(post);
			int statusCode = res.getStatusLine().getStatusCode();		
			entity = EntityUtils.toString(res.getEntity());		
			System.out.println();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return entity;
	}
}
