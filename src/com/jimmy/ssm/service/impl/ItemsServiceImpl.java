package com.jimmy.ssm.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jimmy.ssm.mapper.ItemsMapper;
import com.jimmy.ssm.mapper.ItemsMapperCustom;
import com.jimmy.ssm.po.Items;
import com.jimmy.ssm.po.ItemsCustom;
import com.jimmy.ssm.po.ItemsExample;
import com.jimmy.ssm.po.ItemsQueryVo;
import com.jimmy.ssm.service.ItemsService;

public class ItemsServiceImpl implements ItemsService {
	
	@Autowired
	private ItemsMapper itemsMapper;
	@Autowired
	private ItemsMapperCustom itemsMapperCustom;

	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}

	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		//对商品信息进行业务处理
		ItemsCustom itemsCustom = new ItemsCustom();
		//items的内容拷贝到itemsCustom
		BeanUtils.copyProperties(items, itemsCustom);
		return itemsCustom;
	}

	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
		//校验id
		
		//更新商品信息 使用updateByPrimaryKeyWithBLOBs根据id更新items表中所有字段，包括大文本类型字段
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);		
	}

	@Override
	public void deleteItems(Integer[] items_ids) {
		ItemsExample example = new ItemsExample();
		//通过criteria构造查询条件
		ItemsExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(Arrays.asList(items_ids));
		
		itemsMapper.deleteByExample(example);
	}

	@Override
	public void updateItems(List<ItemsCustom> itemsCustomList) throws Exception {
		for (ItemsCustom itemsCustom : itemsCustomList) {
			itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
		}
	}

}
