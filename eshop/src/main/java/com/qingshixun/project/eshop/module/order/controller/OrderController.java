package com.qingshixun.project.eshop.module.order.controller;

import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.dto.OrderDTO;
import com.qingshixun.project.eshop.dto.OrderItemDTO;
import com.qingshixun.project.eshop.dto.ReceiverDTO;
import com.qingshixun.project.eshop.module.cart.service.CartItemServiceImpl;
import com.qingshixun.project.eshop.module.member.service.MemberServiceImpl;
import com.qingshixun.project.eshop.module.order.service.OrderItemServiceImpl;
import com.qingshixun.project.eshop.module.order.service.OrderServiceImpl;
import com.qingshixun.project.eshop.module.product.service.ProductCategoryServiceImpl;
import com.qingshixun.project.eshop.module.receiver.service.ReceiverServiceImpl;
import com.qingshixun.project.eshop.web.BaseController;
import com.qingshixun.project.eshop.web.ResponseData;
import com.qingshixun.project.eshop.web.SimpleHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/front/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderItemServiceImpl orderItemService;

    @Autowired
    private ReceiverServiceImpl receiverService;

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private CartItemServiceImpl cartItemService;

    @Autowired
    private ProductCategoryServiceImpl productCategoryService;

    /**
     * 进入我的订单页面
     *
     * @param model
     *
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String orderList(Model model) {
        MemberDTO member = this.getCurrentUser();
        // 传递商品分类数据
        model.addAttribute("productCategories", productCategoryService.getProductCategories());
        // 传递我的订单数据
        model.addAttribute("orders", orderService.getOrdersByMember(member.getId()));
        // 传递登录会员数据
        model.addAttribute("member", member);
        return "/order/list";
    }

    /**
     * 进入订单详情页面
     *
     * @param model
     * @param orderId
     *            订单ID
     * @return
     */
    @RequestMapping(value = "/detail/{orderId}", method = RequestMethod.GET)
    public String orderDetailt(Model model, @PathVariable Long orderId) {
        MemberDTO member = this.getCurrentUser();

        model.addAttribute("productCategories", productCategoryService.getProductCategories());
        model.addAttribute("orderItems", orderItemService.getOrderItemsByOrder(orderId));
        model.addAttribute("orderId", orderId);
        // 传递登录会员数据
        model.addAttribute("member", member);
        return "/order/detail";
    }

    @RequestMapping("/settlement")
    public String save(Model model, @RequestParam(required = false, defaultValue = "") String params) {
        MemberDTO member = this.getCurrentUser();
        if (member == null) {
            return "redirect:/front/index";
        }

        List<OrderItemDTO> orderItems = orderItemService.getSelectCart(params, getSession());
        Double productTotalPrice = 0.0;
        for (OrderItemDTO orderItem : orderItems) {
            productTotalPrice += orderItem.getTotalPrice();
        }
        Double totalAmount = productTotalPrice;
        if (member.getMemberLevel() != null) {
            totalAmount = productTotalPrice * member.getMemberLevel().getDiscount();
        }
        BigDecimal bg = new BigDecimal(totalAmount);
        totalAmount = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        model.addAttribute("receivers", receiverService.getReceiversByMember(member.getId()));
        model.addAttribute("orderItems", orderItems);
        model.addAttribute("productTotalPrice", productTotalPrice);
        model.addAttribute("totalAmount", totalAmount);
        model.addAttribute("params", params);
        model.addAttribute("member", member);

        return "/order/main";
    }

    /**
     * 进入订单页面
     *
     * @param model
     * @param orderId 试题ID
     *
     * @return
     */
    @RequestMapping(value = "/main/{orderId}", method = RequestMethod.GET)
    public String orderForm(Model model, @PathVariable Long orderId) {
        // 获取当前的登录用户
        MemberDTO member = this.getCurrentUser();
        OrderDTO order = orderService.saveOrder(orderId, member);
        model.addAttribute("receivers", receiverService.getReceiversByMember(member.getId()));
        model.addAttribute("products", orderItemService.getOrderItemsByOrder(orderId));
        model.addAttribute("productTotalPrice", order.getProductTotalPrice());
        model.addAttribute("totalAmount", order.getTotalAmount());
        model.addAttribute("orderId", orderId);
        model.addAttribute("totalCartCount", cartItemService.getTotalCartCount(member, getSession()));
        model.addAttribute("member", member);
        return "/front/order/main";
    }

    @RequestMapping("/receiver/form/{receiverId}")
    public String receiver(Model model, @PathVariable Long receiverId) throws Exception {
        ReceiverDTO receiver = new ReceiverDTO();

        // 编辑
        if (receiverId != 0) {
            receiver = receiverService.getReceiver(receiverId);
        }
        model.addAttribute("receiver", receiver);
        return "/order/receiver/form";
    }

    /**
     * 提交订单
     *
     * @param params jsp页面拼接的参数
     *
     * @return
     */
    @RequestMapping(value = "/commit/{params}/{receiverId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData orderSave(@PathVariable String params, @PathVariable Long receiverId) {
        MemberDTO member = this.getCurrentUser();

        return new SimpleHandler(request) {
            @Override
            protected void doHandle(ResponseData responseData) throws Exception {
                responseData.setData(orderService.commitOrder(params, member, receiverId, getSession()));
            }
        }.handle();
    }

    /**
     * 模拟支付订单
     *
     * @param orderId 订单id
     *
     * @return
     */
    @RequestMapping(value = "/pay/{orderId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData orderPay(final @PathVariable Long orderId) {
        return new SimpleHandler(request) {
            @Override
            protected void doHandle(ResponseData responseData) throws Exception {
                orderService.updateOrderStatus(orderId, "ORDS_Pay");
            }
        }.handle();
    }

    /**
     * 保存收货人
     *
     * @param receiver 收货人实体
     *
     * @return
     */
    @RequestMapping(value = "/receiver/save", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData receiverSave(final @ModelAttribute ReceiverDTO receiver) {
        final MemberDTO member = this.getCurrentUser();
        SimpleHandler handler = new SimpleHandler(request) {

            @Override
            protected void doHandle(ResponseData responseData) throws Exception {
                receiverService.saveOrUpdateReceiver(receiver, member);
            }
        };
        return handler.handle();
    }

    /**
     * 删除联系人
     *
     * @param receiverId 联系人id
     *
     * @return
     */
    @RequestMapping(value = "/receiver/delete/{receiverId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseData cartsDelete(final @PathVariable Long receiverId) {
        final MemberDTO member = this.getCurrentUser();

        return new SimpleHandler(request) {
            @Override
            protected void doHandle(ResponseData responseData) throws Exception {
                receiverService.deleteReceiver(member, receiverId);
            }
        }.handle();
    }

    /**
     * 设置默认收货人
     *
     * @param model
     * @param receiverId 收货人Id
     *
     * @return
     */
    @RequestMapping(value = "/set/default/receiver/{receiverId}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseData setDefaultReceiverSave(Model model, final @PathVariable Long receiverId) {
        final MemberDTO member = this.getCurrentUser();
        return new SimpleHandler(request) {

            @Override
            protected void doHandle(ResponseData responseData) throws Exception {
                memberService.updateMemberDefaultReceiverId(member.getId(), receiverId);
            }
        }.handle();
    }

}
