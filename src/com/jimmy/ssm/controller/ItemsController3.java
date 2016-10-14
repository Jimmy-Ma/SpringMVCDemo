package com.jimmy.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jimmy.ssm.po.Items;

//@Controller
public class ItemsController3 {

	// @RequestMapping实现queryItems()方法和url的映射
	// 一般建议url和方法名一致
//	@RequestMapping("/items/queryItems")
	public ModelAndView queryItems() throws Exception {

		// 调用service查询数据库，查询商品信息
		// 此处模拟调用service查询数据库得到商品信息
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

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当于request的setAttribut方法，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		// 指定试图
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}

}
