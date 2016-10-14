package com.jimmy.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.jimmy.ssm.po.Items;

public class ItemsController1 implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//调用service查询数据库，查询商品信息
		
		//此处模拟调用service查询数据库得到商品信息
		List<Items> itemsList = new ArrayList<Items>();
		
		Items items1 = new Items();
		items1.setName("联想笔记本");
		items1.setPrice(6000f);
		items1.setDetail("ThinkPad T420");
		
		Items items2 = new Items();
		items2.setName("苹果手机");
		items2.setPrice(5000f);
		items2.setDetail("Iphone 6");
		
		itemsList.add(items1);
		itemsList.add(items2);
		
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//相当于request的setAttribut方法，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);
		
		//指定试图
		modelAndView.setViewName("/WEB-INF/jsp/items/itemsList.jsp");
		
		return modelAndView;
	}

}
