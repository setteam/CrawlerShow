package com.shangbao.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.shangbao.model.Article;

import net.sf.json.JSONObject;

@Component("dataToJson")
public class DataToJson {
	@Resource
	private PostData postData;
	
	public JSONObject toJson(Article myArticle) {
		JSONObject params = new JSONObject();
		params.element("summary", myArticle.getAbstruct());
		params.element("content", myArticle.getContent());
		params.element("title", myArticle.getTitle());
		params.element("keyWord",myArticle.getKeyword());
		String dateString = myArticle.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat format_2 = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		try {
			if(dateString != ""){
				date = format.parse(dateString);
			}else{
				date = format.parse("1970-01-01 00:00:00");
			}
		} catch (Exception e) {
			try {
				date = format_2.parse(dateString);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		params.element("time",date.getTime());
		params.element("level", myArticle.getEvaluation());
		params.element("words", myArticle.getWords());
		params.element("from", myArticle.getNewSource());
		params.element("crawlerCommends", myArticle.getCommentNum());	
		
		String[] pictureUrl = myArticle.getImagePath();
		ArrayList<String> newPictureUrl = new ArrayList<String>();
		for(int i=0;i<pictureUrl.length;i++){
			String url = pictureUrl[i].trim();
			String newUrl = postData.sendPicture(url);
			newPictureUrl.add(newUrl);
		}
		params.element("picturesUrl",newPictureUrl);		
		return params;
	}
}
