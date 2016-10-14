package com.jimmy.ssm.mapper;

import java.util.List;

import com.jimmy.ssm.po.ItemsCustom;
import com.jimmy.ssm.po.ItemsQueryVo;

public interface ItemsMapperCustom {
	
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	
}