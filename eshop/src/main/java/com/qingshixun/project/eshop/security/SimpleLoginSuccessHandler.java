package com.qingshixun.project.eshop.security;

import com.google.common.collect.Lists;
import com.qingshixun.project.eshop.dto.CartItemDTO;
import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.module.cart.service.CartItemServiceImpl;
import com.qingshixun.project.eshop.module.member.service.MemberServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Component
public class SimpleLoginSuccessHandler implements AuthenticationSuccessHandler {

    // 注册会员service
    @Autowired
    private MemberServiceImpl memberService;

    // 注册购物车service
    @Autowired
    private CartItemServiceImpl cartService;

    // 重定向对象
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /**
     * 重写onAuthenticationSuccess方法当登录成功后调用该方法
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 登录后将购物车的信息绑定到用户上去
        HttpSession session = request.getSession();
        List<CartItemDTO> cartList = (List<CartItemDTO>) session.getAttribute("cart");
        // 非空判断
        if (cartList == null) {
            cartList = Lists.newArrayList();
        }
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 获取登录用户的购物车信息
        MemberDTO member = memberService.getMember(userDetails.getUsername());
        // 遍历购物项列表
        for (CartItemDTO cartItem : cartList) {
            CartItemDTO dbCart = cartService.getCartItem(member.getId(), cartItem.getProduct().getId());
            if (dbCart != null) {
                dbCart.setQuantity(cartItem.getQuantity() + dbCart.getQuantity());
                cartService.saveCartItem(dbCart);
            } else {
                cartItem.setMember(member);
                cartService.saveCartItem(cartItem);
            }
        }
        // 清空购物车
        session.removeAttribute("cart");
        Object savedRequestObject = request.getSession().getAttribute("SPRING_SECURITY_SAVED_REQUEST");
        String redirectUrl = null;
        if (savedRequestObject != null) {
            redirectUrl = ((SavedRequest) savedRequestObject).getRedirectUrl();
            request.getSession().removeAttribute("SPRING_SECURITY_SAVED_REQUEST");
        }
        if (redirectUrl == null || !redirectUrl.contains("/front")) {
            // 登录成功后跳转的url
            this.redirectStrategy.sendRedirect(request, response, "/front/index");
        } else {
            this.redirectStrategy.sendRedirect(request, response, redirectUrl);
        }
    }

}
