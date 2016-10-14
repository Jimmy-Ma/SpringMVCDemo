package com.jimmy.ssm.service;

import java.util.List;

import com.jimmy.ssm.po.ItemsCustom;
import com.jimmy.ssm.po.ItemsQueryVo;

public interface ItemsService {
	
	//商品信息查询
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
}
