package com.jimmy.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.jimmy.ssm.mapper.ItemsMapperCustom;
import com.jimmy.ssm.po.ItemsCustom;
import com.jimmy.ssm.po.ItemsQueryVo;
import com.jimmy.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {
	
	@Autowired
	private ItemsMapperCustom itemsMapperCustom;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

}
