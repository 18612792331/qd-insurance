package com.qding.api.util;

/**
 * 老评论切换到评论平台
 * @author Administrator
 *
 */
public class ScoreTransfer {
	
	
	public static int getRate(int score){
		//评论平台 1 差，3 良好，5优秀
		//切换前  评价等级rate= 0-好，1-中，2-差
		if(score==1 || score==2){
			return 2;
		}else if(score==3){
			return 1;
		}else if(score==5 || score==4){
			return 0;
		}
		return 0;
	}
	
	public static int getScore(int rate){
		//评论平台 1 差，3 良好，5优秀
		//切换前  评价等级rate= 0-好，1-中，2-差
		if(rate==0){
			return 5;
		}else if(rate==1){
			return 3;
		}else if(rate==2){
			return 1;
		}
		return 5;
	}
	
	
	
}
