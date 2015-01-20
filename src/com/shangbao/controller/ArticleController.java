package com.shangbao.controller;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.shangbao.model.Article;
import com.shangbao.service.ArticleService;


@Controller
@RequestMapping("/article")
public class ArticleController {
	
	
	@Resource
	private ArticleService articleService;
	
	/*
	 * 初始加载，返回所有的Article
	 */	
	@RequestMapping(value ="/all",method=RequestMethod.GET)
	@ResponseBody
	public List<Article> findAllArticles(){
		System.out.println("-----");
		List<Article> articlelList = articleService.findAll();
		return articlelList;
	}
	
//	/*
//	 * 根据请求的id,返回一篇新闻的详细数据
//	 */
//	@RequestMapping(value ="/getPicture/{id}",method=RequestMethod.GET)
//	@ResponseBody
//	public Article findOneArticles(@PathVariable("id")String id){
//		System.out.println("-------oneDetail--------"+id);
//		Article articlel = articleService.findOneDetail(id);
//		return articlel;
//	}	
	
	/*
	 * 根据请求title，返回这篇新闻的相似新闻列表
	 */
	@RequestMapping(value ="/similar/{title}",method=RequestMethod.GET)
	@ResponseBody
	public List<Article> findSimilarArticles(@PathVariable("title")String title){
		System.out.println("------similar-----"+title);
		List<Article> articleList = articleService.findSimilar(title);
		return articleList;
	}
	
	
	/*
	 * 根据请求title,返回这篇新闻的所有重复新闻
	 */
	@RequestMapping(value ="/repeat/{title}",method=RequestMethod.GET)
	@ResponseBody
	public List<Article> findRepeatArticles(@PathVariable("title")String title){
		System.out.println("---------repeat------"+title);
		List<Article> articles = articleService.findRepeat(title);
		return articles;
	}
	
	/*
	 * 前台请求将数据发送到最终的数据库
	 */	
	@RequestMapping(value = "/postArticle/{title}", method = RequestMethod.GET)
	@ResponseBody
	public long postData(@PathVariable("title")String title) {
		System.out.println("post Article:"+ title);
		long newAritcleId =articleService.postArticle(title);
		return newAritcleId;
	}
	
}
