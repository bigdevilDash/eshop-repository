package com.qingshixun.project.eshop.module.product.controller;

import com.qingshixun.project.eshop.core.Constants;
import com.qingshixun.project.eshop.dto.CartItemDTO;
import com.qingshixun.project.eshop.dto.EvaluateDTO;
import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.dto.ProductDTO;
import com.qingshixun.project.eshop.module.brand.service.BrandServiceImpl;
import com.qingshixun.project.eshop.module.cart.service.CartItemServiceImpl;
import com.qingshixun.project.eshop.module.evaluate.service.EvaluateServiceImpl;
import com.qingshixun.project.eshop.module.order.service.OrderServiceImpl;
import com.qingshixun.project.eshop.module.product.service.ProductCategoryServiceImpl;
import com.qingshixun.project.eshop.module.product.service.ProductServiceImpl;
import com.qingshixun.project.eshop.module.product.service.ProductTypeAttributeServiceImpl;
import com.qingshixun.project.eshop.web.BaseController;
import com.qingshixun.project.eshop.web.ResponseData;
import com.qingshixun.project.eshop.web.SimpleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/front/product")
public class ProductController extends BaseController {

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    @Autowired
    private ProductTypeAttributeServiceImpl productTypeAttributeService;

    @Autowired
    private ProductServiceImpl productService;

    @Autowired
    private BrandServiceImpl brandService;

    @Autowired
    private EvaluateServiceImpl evaluateService;

    @Autowired
    private CartItemServiceImpl cartItemService;

    @Autowired
    private OrderServiceImpl orderService;

    /**
     * 商品列表页
     */
    @RequestMapping("/list")
    public String list(Model model, @RequestParam Long categoryId) {
        List<ProductDTO> products = productService.getProductsByCategory(categoryId);

        MemberDTO member = getCurrentUser();

        // 非空验证
        if (!products.isEmpty()) {
            // 获取第一个商品的类型id
            Long typeId = products.get(0).getProductType().getId();

            model.addAttribute("brands", brandService.getBrandsByCategory(categoryId));
            model.addAttribute("productTypeAttributes", productTypeAttributeService.getProductTypeAttributesByProductType(typeId));
        }

        model.addAttribute("productCategories", productCategoryService.getProductCategories());
        model.addAttribute("totalCartCount", cartItemService.getTotalCartCount(member, getSession()));
        model.addAttribute("products", products);
        model.addAttribute("categoryId", categoryId);

        return "/product/list";
    }

    @RequestMapping("/main")
    public String main(Model model, @RequestParam Long productId) {
        ProductDTO product = productService.getProduct(productId);

        MemberDTO member = getCurrentUser();

        model.addAttribute("product", product);
        model.addAttribute("cart", new CartItemDTO());
        model.addAttribute("imagePath", Constants.PRODUCT_IMAGE_PATH);
        model.addAttribute("products", productService.getProductsByPrice(product.getPrice()));
        model.addAttribute("score", evaluateService.getAverageEvaluateScoreByProduct(productId));
        model.addAttribute("totalEvaluateCount", evaluateService.getEvaluateCountByProduct(productId));
        model.addAttribute("satisfiedEvaluateCount", evaluateService.getEvaluateCountByStatusAndProduct("满意", product.getId()));
        model.addAttribute("commonlyEvaluateCount", evaluateService.getEvaluateCountByStatusAndProduct("一般", product.getId()));
        model.addAttribute("disSatisfiedEvaluateCount", evaluateService.getEvaluateCountByStatusAndProduct("不满意", product.getId()));
        model.addAttribute("totalCartCount", cartItemService.getTotalCartCount(member, getSession()));

        return "/product/main";
    }

    @RequestMapping("/evaluate/list")
    public String evaluates(Model model, @RequestParam(required = false, defaultValue = "") String status, @RequestParam Long productId) {
        model.addAttribute("evaluates", evaluateService.getEvaluatesByStatusAndProduct(status, productId));

        return "/product/evaluate/list";
    }

    /**
     * 商品评论页面
     *
     * @param productId 商品ID
     *
     * @throws Exception
     */
    @RequestMapping("/evaluate/{productId}/{orderId}")
    public String productEvaluate(Model model, @PathVariable Long productId, @PathVariable Long orderId) throws Exception {
        // 获取当前的登录用户
        MemberDTO dbMember = this.getCurrentUser();
        model.addAttribute("product", productService.getProduct(productId));
        model.addAttribute("productId", productId);
        model.addAttribute("totalCartCount", cartItemService.getTotalCartCount(dbMember, getSession()));
        model.addAttribute("time", orderService.getOrder(orderId).getUpdateTime());
        model.addAttribute("orderId", orderId);
        model.addAttribute("member", dbMember);
        model.addAttribute("evaluate", evaluateService.getEvaluateByMemberAndProductAndOrder(dbMember.getId(), productId, orderId));
        return "/product/evaluate";
    }

    /**
     * 保存商品评论
     *
     * @param model
     * @param evaluate 评论实体
     */
    @RequestMapping(value = "/evaluate/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData receiverSave(Model model, EvaluateDTO evaluate) {
        MemberDTO member = this.getCurrentUser();

        return new SimpleHandler(request) {
            @Override
            protected void doHandle(ResponseData responseData) throws Exception {
                evaluateService.saveOrUpdateEvaluate(evaluate, member);
            }
        }.handle();
    }

}
