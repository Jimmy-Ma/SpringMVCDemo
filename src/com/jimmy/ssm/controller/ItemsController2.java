package com.jimmy.ssm.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.HttpRequestHandler;

import com.jimmy.ssm.po.Items;

public class ItemsController2 implements HttpRequestHandler {

	@Override
	public void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		
		//设置模型数据
		request.setAttribute("itemsList", itemsList);
		//设置转发视图 
		request.getRequestDispatcher("/WEB-INF/jsp/items/itemsList.jsp").forward(request, response);
		
	}
	
}
