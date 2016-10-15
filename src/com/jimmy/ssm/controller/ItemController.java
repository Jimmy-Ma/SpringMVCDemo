package com.jimmy.ssm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jimmy.ssm.po.ItemsCustom;
import com.jimmy.ssm.po.ItemsQueryVo;
import com.jimmy.ssm.service.ItemsService;

@Controller
//对url进行分类管理，可以在这里定义根路径，最终访问url是根路径+子路径
//比如”查询商品列表 /items/queryItems
@RequestMapping("/items")
public class ItemController {
	
	@Autowired
	private ItemsService itemsService;
	
	// @RequestMapping指定queryItems()方法和url的映射
	// 一般建议url和方法名一致
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(HttpServletRequest request, ItemsQueryVo itemsQueryVo) throws Exception {
		
		//测试forward后request是否可以共享
		System.out.println(request.getParameter("id"));
		
		//调用serivce
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);

		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当于request的setAttribut方法，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsList", itemsList);

		// 指定试图
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}
	
	//批量修改商品页面，将商品信息查询出来，在页面中可以编辑商品信息
	@RequestMapping("/batchEditItems")
	public ModelAndView batchEditItems(ItemsQueryVo itemsQueryVo) throws Exception {
		
		//调用serivce
		List<ItemsCustom> itemsCustomList = itemsService.findItemsList(itemsQueryVo);
		
		// 返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		// 相当于request的setAttribut方法，在jsp页面中通过itemsList取数据
		modelAndView.addObject("itemsCustomList", itemsCustomList);
		
		// 指定试图
		modelAndView.setViewName("items/batchEditItems");
		return modelAndView;
	}
	
	//商品信息修改提交
	@RequestMapping("/batchEditItemsSubmit")
	public String batchEditItemsSubmit(ItemsQueryVo itemsQueryVo) throws Exception{
		
		itemsService.updateItems(itemsQueryVo.getItemsCustomList());
		return "success";
		
	}
	
	//商品信息修改页面显示
	//限制http请求方法，可以post和get
	/*@RequestMapping(value = "/editItems", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView editItems() throws Exception{
		
		//调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(1);
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("itemsCustom", itemsCustom);
		modelAndView.setViewName("items/editItems");
		
		return modelAndView;
		
	}*/
	
	//商品信息修改页面显示
	//限制http请求方法，可以post和get
	@RequestMapping(value = "/editItems", method = {RequestMethod.GET, RequestMethod.POST})
	//通过@RequestParam注解的value属性可将request传入的参数名和形参进行绑定
	//通过@RequestParam注解的required属性指定参数是必须传入的
	public String editItems(Model model, @RequestParam(value="id", required=true) Integer items_id) throws Exception{
		
		//调用service根据商品id查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		
		//通过形参model讲model数据传到页面
		//相当于modelAndView.addObject("itemsCustom", itemsCustom)
		model.addAttribute("itemsCustom", itemsCustom);
		
		return "items/editItems";
	}
	
	//商品信息修改提交
	@RequestMapping("/eidtItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request, Integer id, ItemsCustom itemsCustom) throws Exception{
		
		itemsService.updateItems(id, itemsCustom);
		
		return "success";
		//重定向
//		return "redirect:queryItems.action";
		//页面转发
//		return "forward:queryItems.action";
	}
	
	//批量删除商品
	@RequestMapping("/deleteItems")
	public String deleteItems(Integer[] items_ids)throws Exception{
		
		itemsService.deleteItems(items_ids);
		
		return "success";
	}
	
}
