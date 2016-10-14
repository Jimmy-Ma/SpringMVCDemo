package com.jimmy.ssm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jimmy.ssm.po.ItemsCustom;
import com.jimmy.ssm.service.ItemsService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemsService itemsService;
	
	// @RequestMapping指定queryItems()方法和url的映射
	// 一般建议url和方法名一致
	@RequestMapping("/items/queryItems")
	public ModelAndView queryItems() throws Exception {
		
		//调用serivce
		List<ItemsCustom> itemsList = itemsService.findItemsList(null);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当于request的setAttribut方法，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		// 指定试图
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}
}
