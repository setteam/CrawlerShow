package com.shangbao.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="shangbaoData")
public class Article {
	@Id
	String id;
	String title;
	String time;
	String content;
	String newSource;
	String url;
	String[] imagePath;
	String[] keyword;
	String abstruct;
	double evaluation; 	
	String[] repeatedNews;
	String[] similarNews;
	int words;
	int commentNum;
	long articleId;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNewSource() {
		return newSource;
	}
	public void setNewSource(String newSource) {
		this.newSource = newSource;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String[] getImagePath() {
		return imagePath;
	}
	public void setImagePath(String[] imagePath) {
		this.imagePath = imagePath;
	}
	public String[] getKeyword() {
		return keyword;
	}
	public void setKeyword(String[] keyword) {
		this.keyword = keyword;
	}
	public String getAbstruct() {
		return abstruct;
	}
	public void setAbstruct(String abstruct) {
		this.abstruct = abstruct;
	}
	public double getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(double evaluation) {
		this.evaluation = evaluation;
	}
	public String[] getRepeatedNews() {
		return repeatedNews;
	}
	public void setRepeatedNews(String[] repeatedNews) {
		this.repeatedNews = repeatedNews;
	}
	public String[] getSimilarNews() {
		return similarNews;
	}
	public void setSimilarNews(String[] similarNews) {
		this.similarNews = similarNews;
	}
	public int getWords() {
		return words;
	}
	public void setWords(int words) {
		this.words = words;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public long getArticleId() {
		return articleId;
	}
	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}
	
}
