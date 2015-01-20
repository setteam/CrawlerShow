package com.shangbao.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.shangbao.common.DataToJson;
import com.shangbao.common.PostData;
import com.shangbao.model.Article;

@Repository(value = "articleDao")
public class ArticleDao {

	@Resource
	private MongoTemplate mongoTemplate;

	
	@Resource
	private PostData postData;
	
	@Resource 
	private DataToJson dataToJson;

	public MongoTemplate getMongoTemplate() {
		return mongoTemplate;
	}

	public void setMongoTemplate(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}

	// ����������������
	public List<Article> findAllArticle() {
		return mongoTemplate.findAll(Article.class);
	}

	// ����id����һ�����ŵ���ϸ����
	public Article findOneArticle(String id) {
		return mongoTemplate.findById(id, Article.class);
	}

	// ��������title�������������Ƶ����������б�
	public List<Article> findSimilarArticle(String title) {
		Query query = new Query();
		Criteria criteria = Criteria.where("title").is(title);
		query.addCriteria(criteria);
		Article myArticle = mongoTemplate.findOne(query, Article.class);
		String[] similarUrls = myArticle.getSimilarNews();
		return findArticlesByUrls(similarUrls);
	}

	// ��������title�����������ظ������������б�
	public List<Article> findRepeatArticle(String title) {
		Query query = new Query();
		Criteria criteria = Criteria.where("title").is(title);
		query.addCriteria(criteria);
		Article myArticle = mongoTemplate.findOne(query, Article.class);
		String[] similarUrls = myArticle.getRepeatedNews();
		return findArticlesByUrls(similarUrls);
	}

	public long postArticleToDatabase(String title) {
//		Article article = mongoTemplate.findById(articleId, Article.class);
		Query query = new Query();
		Criteria criteria = Criteria.where("title").is(title);
		query.addCriteria(criteria);
		Article myArticle = mongoTemplate.findOne(query, Article.class);
		long newId =postData.sendArticle(dataToJson.toJson(myArticle));
		return newId;
	}

//	public String postCommentToDatabase(Json comment, String articleId) {
//		HttpClient client = new DefaultHttpClient();
//		Properties props = new Properties();
//		String url="";
//		try {
//			props = PropertiesLoaderUtils
//					.loadAllProperties("config.properties");
//			url = props.getProperty("HttpPost_comment");// �ϴ����۵�url��ַ
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		HttpPost post = new HttpPost(url+ articleId);
//		String entity = "";
//		try {
//			StringEntity s = new StringEntity(comment.toString(), HTTP.UTF_8);
//			s.setContentEncoding("utf-8");
//			s.setContentType("application/json");
//			post.setEntity(s);
//
//			HttpResponse res = client.execute(post);
//			int statusCode = res.getStatusLine().getStatusCode();
//
//			entity = EntityUtils.toString(res.getEntity());
//			// System.out.println(entity);
//
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//		return entity;
//	}

	// ���ݴ����url���飬��ԭʼ���ݿ����ҳ����ж�Ӧ������
	public List<Article> findArticlesByUrls(String[] urls) {
		int length = urls.length;
		if (length == 0) {
			System.out.println("----findArticleByUrl:Urls.length = 0------");
			return null;
		}

		List<Article> result = new ArrayList<Article>();
		for (int i = 0; i < length; i++) {
			String url = urls[i];
			Article article = findArticleByOneUrl(url);
			result.add(article);
		}
		return result;
	}

	// ����url��ԭʼ�����������ҳ�������ŵ���Ϣ
	public Article findArticleByOneUrl(String url) {
//		System.out.println(url);
		Query query = new Query();
		Criteria criteria = Criteria.where("url").is(url);
//		Criteria criteria = Criteria.where("Title").is("��ý��������������й¶�й��볯�ʾ��»���");
		query.addCriteria(criteria);
		Article article = mongoTemplate.findOne(query, Article.class);
		return article;
	}

}
