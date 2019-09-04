package com.qingshixun.project.eshop.module.search.controller;

import java.util.List;

import org.apache.solr.common.SolrDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.module.cart.service.CartItemServiceImpl;
import com.qingshixun.project.eshop.module.product.service.ProductCategoryServiceImpl;
import com.qingshixun.project.eshop.module.search.service.SearchService;
import com.qingshixun.project.eshop.web.BaseController;

@Controller

public class SearchController extends BaseController{
	private static final Logger logger = LoggerFactory.getLogger(SearchController.class);
	 @Autowired
	    private CartItemServiceImpl cartItemService;
	 @Autowired
	    private ProductCategoryServiceImpl productCategoryService;

	
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="/front/search")
	public String Search(Model model,@RequestParam("searchtext")String keyword) {
		 MemberDTO member = getCurrentUser();
		 List<SolrDocument> products=null;
		try {
			 products=searchService.getSearch(keyword);
			
		}catch(Exception e){
			logger.error("查询失败"+e.getMessage());
		}
		 model.addAttribute("totalCartCount", cartItemService.getTotalCartCount(member, getSession()));
		 model.addAttribute("productCategories", productCategoryService.getProductCategories());
		 model.addAttribute("products", products);
		return "/result";
	}
	
	
}
