package com.jimmy.ssm.service;

import java.util.List;

import com.jimmy.ssm.po.ItemsCustom;
import com.jimmy.ssm.po.ItemsQueryVo;

public interface ItemsService {
	
	//商品信息查询
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
	//根据id查询商品信息
	public ItemsCustom findItemsById(Integer id) throws Exception;
	
	//修改商品信息
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception;

	public void updateItems(List<ItemsCustom> itemsCustomList) throws Exception;
	
	//批量删除山商品
	public void deleteItems(Integer[] items_ids);
	
}
