package com.qingshixun.project.eshop.module.cart.service;

import com.google.common.collect.Lists;
import com.qingshixun.project.eshop.dto.CartItemDTO;
import com.qingshixun.project.eshop.dto.MemberDTO;
import com.qingshixun.project.eshop.dto.ProductDTO;
import com.qingshixun.project.eshop.module.cart.dao.CartItemDaoMyBatis;
import com.qingshixun.project.eshop.module.product.service.ProductServiceImpl;
import com.qingshixun.project.eshop.web.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

@Service
public class CartItemServiceImpl extends BaseService {

    @Autowired
    private CartItemDaoMyBatis cartItemDao;

    @Autowired
    private ProductServiceImpl productService;

    /**
     * 获取购物车列表
     */
    public List<CartItemDTO> getCartItems(MemberDTO member, HttpSession session) {
        // 如果登录，显示用户数据库中的购物车数据，否则显示session中的购物车数据
        if (member != null) {
            List<CartItemDTO> cartItems = cartItemDao.getCartItemsByMember(member.getId());

            for (CartItemDTO cartItem : cartItems) {
                ProductDTO product = productService.getProduct(cartItem.getProduct().getId());

                cartItem.setProduct(product);
            }

            return cartItems;
        }

        List<CartItemDTO> carts = (List<CartItemDTO>) session.getAttribute("cart");
        // 非空判断
        if (carts == null) {
            carts = Lists.newArrayList();
        }
        return carts;
    }

    /**
     * 设置购物车中指定的商品的数量
     *
     * @param productId 商品id
     * @param member    会员
     * @param session   session
     * @param quantity  数量
     */
    public void setCareItemCount(Long productId, MemberDTO member, HttpSession session, int quantity) {
        // 如果有用户信息，设置数据库中的购物车商品数量
        if (member != null) {
            setCareItemCountInDB(productId, member.getId(), quantity);
            // 更新 session 中购物车的商品数量
            session.setAttribute("totalCartCount", getTotalCartCount(member.getId()));
            return;
        }

        // 设置 session 中购物车的商品数量
        setCareItemCountInSession(productId, session, quantity);
        // 更新 session 中购物车的商品数量
        session.setAttribute("totalCartCount", getTotalCartCount(session));
    }

    /**
     * 删除购物车中的指定商品
     *
     * @param ids     由逗号分隔的商品 id 字符串
     * @param session 会话
     * @param member  会员
     */
    public void deleteCartItem(String ids, HttpSession session, MemberDTO member) {
        // 分割为数组
        List<String> productIds = Arrays.asList(ids.split(","));

        // 如果有会员信息，删除数据库中购物车的商品信息
        if (member != null) {
            deleteCareItemInDB(productIds, member.getId());
            // 更新 session 中购物车的商品数量
            session.setAttribute("totalCartCount", getTotalCartCount(member.getId()));
            return;
        }

        // 删除 session 中购物车的商品信息
        deleteCareItemInSession(productIds, session);
        // 更新 session 中购物车的商品数量
        session.setAttribute("totalCartCount", getTotalCartCount(session));

    }

    /**
     * 从 session 中的购物车中删除指定商品
     *
     * @param productIds 商品id集合
     * @param session    session
     */
    public void deleteCareItemInSession(List<String> productIds, HttpSession session) {
        // 从 session 中获取购物车集合
        List<CartItemDTO> cartList = (List<CartItemDTO>) session.getAttribute("cart");

        // 如果购物车集合为空，不继续操作
        if (cartList == null) {
            return;
        }

        // 从 session 中删除指定的商品
        for (String productId : productIds) {
            for (CartItemDTO cartItem : cartList) {
                if (String.valueOf(cartItem.getProduct().getId()).equals(productId)) {
                    cartList.remove(cartItem);
                    break;
                }
            }
        }
    }

    /**
     * 从数据库中的购物车中删除指定商品
     *
     * @param productIds 商品id 集合
     * @param memberId   会员id
     */
    public void deleteCareItemInDB(List<String> productIds, Long memberId) {
        // 从数据库中删除会员购物车中的指定商品
        for (String productId : productIds) {
            CartItemDTO dbCart = getCartItem(memberId, Long.valueOf(productId));
            // 非空判断
            if (dbCart != null) {
                cartItemDao.deleteCartItem(dbCart.getId());
            }
        }
    }

    /**
     * 添加商品到购物车
     */
    public void saveCartItem(HttpSession session, Long productId, MemberDTO member) {
        ProductDTO product = productService.getProduct(productId);

        // 如果有会员信息，保存到数据库
        if (member != null) {
            // 保存到数据库
            saveCartItem(product, member);
            // 更新session中购物车的商品数量
            session.setAttribute("totalCartCount", getTotalCartCount(member.getId()));

            return;
        }

        // 保存到session
        saveCareItemInSession(product, session);
        // 更新session中购物车的商品数量
        session.setAttribute("totalCartCount", getTotalCartCount(session));
    }

    /**
     * 在 session 中操作添加商品到购物车
     *
     * @param product 商品
     * @param session session
     */
    public void saveCareItemInSession(ProductDTO product, HttpSession session) {

        // 获取session中的购物项
        List<CartItemDTO> carts = (List<CartItemDTO>) session.getAttribute("cart");
        // 如果cartList为空则新建 list 对象并放到 session 中
        if (carts == null) {
            carts = Lists.newArrayList();
            session.setAttribute("cart", carts);
        }

        // 是否为新添商品
        boolean isNewProduct = true;
        // 如果购物车中包含添加的商品则不是新商品，直接将购物车中该商品的数量加1
        for (CartItemDTO cartItem : carts) {
            if (cartItem.getProduct().getId().longValue() == product.getId().longValue()) {
                cartItem.setQuantity();
                isNewProduct = false;
                break;
            }
        }

        // 如果为新添加的商品，则添加到购物车中去
        if (isNewProduct) {
            CartItemDTO cart = new CartItemDTO();
            cart.setProduct(product);
            carts.add(cart);
        }
    }

    /**
     * 通过会员Id商品id获取购物项数据
     */
    public CartItemDTO getCartItem(Long memberId, Long productId) {
        return cartItemDao.getCartItem(memberId, productId);
    }

    /**
     * 获取当前购物车的商品数量
     */
    public int getTotalCartCount(MemberDTO member, HttpSession session) {
        if (member != null) {
            return getTotalCartCount(member.getId());
        }

        return getTotalCartCount(session);
    }

    /**
     * 获取 CartItemDTO 集合中商品总数量
     */
    public int getTotalCartCount(List<CartItemDTO> cartItems) {
        if (cartItems == null) {
            return 0;
        }

        int count = 0;
        for (CartItemDTO cartItem : cartItems) {
            count += cartItem.getQuantity();
        }
        return count;
    }

    /**
     * 保存购物车
     */
    public void saveCartItem(CartItemDTO cartItem) {
        cartItemDao.saveCartItem(cartItem);
    }

    /**
     * 设置数据库中购物车的指定商品数量
     *
     * @param productId 商品id
     * @param memberId  会员id
     * @param quantity  数量
     */
    private void setCareItemCountInDB(Long productId, Long memberId, int quantity) {
        CartItemDTO cartItem = getCartItem(memberId, productId);
        cartItem.setQuantity(quantity);

        cartItemDao.updateCartItemQuantity(cartItem);
    }

    /**
     * 设置 session 中购物车的指定商品数量
     *
     * @param productId 商品id
     * @param session   session
     * @param quantity  数量
     */
    private void setCareItemCountInSession(Long productId, HttpSession session, int quantity) {
        // 从 session 中获取购物车集合
        List<CartItemDTO> cartList = (List<CartItemDTO>) session.getAttribute("cart");

        // 如果购物车集合为空，不继续操作
        if (cartList == null) {
            return;
        }

        // 遍历购物车
        for (CartItemDTO cartItem : cartList) {

            // 找到指定的商品，设置数量
            if (productId.longValue() == cartItem.getProduct().getId().longValue()) {
                cartItem.setQuantity(quantity);
            }
        }
    }

    /**
     * 保存商品到购物车
     */
    private void saveCartItem(ProductDTO product, MemberDTO member) {
        // 查询该会员的购物车中是否存在该商品
        CartItemDTO cartItem = getCartItem(member.getId(), product.getId());

        // 如果数据库已经存在购物项，该数量添加1.
        if (cartItem != null) {
            cartItem.setQuantity();
        } else {
            // 否则创建新的购物项
            cartItem = new CartItemDTO();
            cartItem.setProduct(product);
            cartItem.setMember(member);
        }

        cartItemDao.saveCartItem(cartItem);
    }

    private int getTotalCartCount(Long memberId) {
        int totalCount = 0;
        if (memberId == null) {
            return totalCount;
        }

        List<CartItemDTO> cartItems = cartItemDao.getCartItemsByMember(memberId);
        return getTotalCartCount(cartItems);
    }

    /**
     * 获取 session 中购物车集合中商品总数量
     *
     * @return
     */
    private int getTotalCartCount(HttpSession session) {
        List<CartItemDTO> cartItems = (List<CartItemDTO>) session.getAttribute("cart");
        return getTotalCartCount(cartItems);
    }


}
