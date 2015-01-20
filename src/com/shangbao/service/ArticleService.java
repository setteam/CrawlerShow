package com.shangbao.service;

import java.util.List;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangbao.dao.ArticleDao;
import com.shangbao.model.Article;


@Service(value="articleService")
public class ArticleService {
	
	@Resource
	private ArticleDao articleDao;	
	
	public ArticleDao getArticleDao() {
		return articleDao;
	}
	
	public void setArticleDao(ArticleDao articleDao) {
		this.articleDao = articleDao;
	}

	//查找所有数据
	public List<Article> findAll() {
//		Article article = new Article();
//		article.setTitle("title");
		return articleDao.findAllArticle();
	}

	public Article findOneDetail(String id) {
		return articleDao.findOneArticle(id);
	}

	public List<Article> findSimilar(String title) {
		return articleDao.findSimilarArticle(title);
	}

//	public Article findTest() {
//		// TODO Auto-generated method stub
//		return articleDao.findArticleByOneUrl("http://war.163.com/15/0114/11/AFTQUUDU00014OVF.html");
//	}

	public List<Article> findRepeat(String title) {
		return articleDao.findRepeatArticle(title);
	}

	public long postArticle(String title) {
		long result =articleDao.postArticleToDatabase(title);	
		return result;
	}
}
