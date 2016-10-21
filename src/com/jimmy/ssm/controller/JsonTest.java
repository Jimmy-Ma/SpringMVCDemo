package com.jimmy.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jimmy.ssm.po.ItemsCustom;

@Controller
public class JsonTest {

	
	/**
	 * 请求json（商品信息），返回json（商品信息）
	 * @RequestBody将请求的json转成ItemsCustom对象
	 * @ResponseBody将返回的ItemsCustom对象转成json
	 * @param itemsCustom
	 * @return
	 */
	@RequestMapping("/requestJson")
	private @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) {
		return itemsCustom;
	}
	
	
	/**
	 * 请求key/value，返回json
	 * @param itemsCustom
	 * @return
	 */
	@RequestMapping("/responseJson")
	private @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) {
		return itemsCustom;
	}
}
