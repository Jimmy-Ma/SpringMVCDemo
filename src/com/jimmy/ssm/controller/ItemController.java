package com.jimmy.ssm.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jimmy.ssm.controller.validation.ValidationGroup1;
import com.jimmy.ssm.po.ItemsCustom;
import com.jimmy.ssm.po.ItemsQueryVo;
import com.jimmy.ssm.service.ItemsService;

@Controller
//对url进行分类管理，可以在这里定义根路径，最终访问url是根路径+子路径
//比如”查询商品列表 /items/queryItems
@RequestMapping(value="/items")
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
		
		//与业务有关的异常放到service中进行处理
//		if (itemsCustom == null) {
//			throw new CustomException("修改的商品信息不存在！");
//		}
		
		//通过形参model讲model数据传到页面
		//相当于modelAndView.addObject("itemsCustom", itemsCustom)
		model.addAttribute("itemsCustom", itemsCustom);
		
		return "items/editItems";
	}
	
	//商品信息修改提交
	//在需要校验的pojo之前添加@Validated标签，在需需要校验的pojo之后添加BindingResult bindingResult参数接受校验出错信息
	//注意：@Validated和BindingResult bindingResult必须配对出现，并且顺序是固定的。
	//value={ValidationGroup1.class})指定使用ValidationGroup1分组校验
	//@ModelAttribute("itemsCustom")指定pojo回显到页面在request中key
	@RequestMapping("/eidtItemsSubmit")
	public String editItemsSubmit(
			Model model, 
			HttpServletRequest request, 
			Integer id, 
			@ModelAttribute("itemsCustom") @Validated(value={ValidationGroup1.class}) ItemsCustom itemsCustom, 
			BindingResult bindingResult,
			MultipartFile items_pic//接受商品图片
			) throws Exception{
		
		if(bindingResult.hasErrors()){
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for (ObjectError objectError : allErrors) {
				System.out.println(objectError.getDefaultMessage());
			}
			//将错误信息传到页面
			model.addAttribute("allErrors", allErrors);
			//出错返回到商品修改页面
			return "items/editItems";
		}
				
		//上传图片
		if (items_pic != null) {
			//原始图片名称
			String originalFilename = items_pic.getOriginalFilename();
			if (originalFilename != null && originalFilename != "") {

				//存储图片的屋里路径
				String pic_path = "D:\\PROGRAMMING\\temp\\";
				
				//新的图片名称
				String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				//新图片文件
				File newFile = new File(pic_path + newFileName);
				
				//将内存中的数据写入磁盘
				items_pic.transferTo(newFile);
				
				//将新图片名称存入itemsCustom中
				itemsCustom.setPic(newFileName);
			}			
		}
		
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
	
	//商品分类
	//@ModelAttribute("itemTypes")指定方法返回值在request中的key
	@ModelAttribute("itemTypes")
	public Map<String, String> getItemType(){
		HashMap<String, String> itemTypes = new HashMap<String, String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		return itemTypes;
	}
	
	//查询商品信息，输出json
	///itemsView/{id}：其中的{id}表示将这个位置的参数传到@PathVariable("id")标识的参数中
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception{
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		return itemsCustom;
	}
	
	
}
