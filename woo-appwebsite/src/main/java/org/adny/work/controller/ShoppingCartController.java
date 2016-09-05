package org.adny.work.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adny.work.controller.cart.CartDisplay;
import org.adny.work.controller.cart.CartItemAdd;
import org.adny.work.controller.cart.CartItemDisplay;
import org.adny.work.controller.helper.ShoppingCartHelper;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.constant.WebConstants;
import org.andy.work.utils.AjaxResponse;
import org.andy.work.utils.CommonUtils;
import org.andy.work.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="/cart")
public class ShoppingCartController {
	
	@Resource
	private ShoppingCartHelper shoppingCartHelper;
	@Resource
	private IProductMaintenanceService productMaintenanceService;
	
	/**
	 * 购物车首页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/index", method=RequestMethod.GET)
	private ModelAndView view(HttpServletRequest request, HttpServletResponse response, ModelAndView model) {
		model.addObject(WebConstants.TITLE, "购物车").setViewName("shopping-cart");
		return model;
	}
	
	/**
	 * 购物车列表
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	@ResponseBody
	public CartDisplay listCartItems(ModelAndView model, HttpServletRequest request, HttpServletResponse response) {
		CartDisplay cart = new CartDisplay();
		List<CartItemDisplay> cartItems = this.shoppingCartHelper.getCartItemsForReview(request, response);		
		if (cartItems != null && !cartItems.isEmpty()) {
			Double totalAmount = this.shoppingCartHelper.calculateTotalCartItemAmount(cartItems);
			
			cart.setTotalAmount(CommonUtils.amountFormat(totalAmount));
			cart.setTotalItems(cartItems.size() + "");
			cart.setCartItems(cartItems);
			
			boolean selectedAll = true;
			for (CartItemDisplay item : cartItems) {
				if (!"Y".equals(item.getSelected())) {
					selectedAll = false;
					break;
				}
			}
			if (selectedAll) {
				cart.setSelectedAll("Y");
			}
		}
		return cart;
	}
	
	/**
	 * 加入购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.GET)
	@ResponseBody
	private AjaxResponse addToCarts(HttpServletRequest request, HttpServletResponse response) {
		List<CartItemAdd> products = this.getParamProducts(request);
		if (!products.isEmpty()) {
			this.shoppingCartHelper.cartAddItems(request, response, products);
		}
		return AjaxResponse.success();
	}
	
	private List<CartItemAdd> getParamProducts(HttpServletRequest request) {
		List<CartItemAdd> cartItems = new ArrayList<CartItemAdd>();
		@SuppressWarnings("unchecked")
		Enumeration<String> paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String paramName = paramNames.nextElement();
			if (StringUtil.hasValue(paramName) && paramName.startsWith("_qty")) {
				Integer prodId = new Integer(paramName.substring(4));
				String qtyParam = request.getParameter(paramName);
				if (StringUtil.hasValue(qtyParam)) {
					Double orderQty = new Double(qtyParam);
					if (orderQty.compareTo(0D) > 0) {
						IProduct product = this.productMaintenanceService.getProductById(prodId);
						if (product != null) {
							CartItemAdd cartItem = new CartItemAdd();
							cartItem.setOrderQty(orderQty);
							cartItem.setProdId(product.getId());
							cartItem.setProdNum(product.getId()+"");
							cartItem.setSelected("Y");
							cartItems.add(cartItem);
						}
					}
				}
			}
		}
		return cartItems;
	}
	
	/**
	 * 删除购物车中的商品
	 * @param prodIds
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/del")
	@ResponseBody
	private AjaxResponse deleteCartItem(@RequestParam(value="p", required=true) Integer[] prodIds
			, HttpServletRequest request, HttpServletResponse response) {
		this.shoppingCartHelper.deleteCartItems(prodIds, request, response);
		return AjaxResponse.success();
	}
	
	/**
	 * 清空购物车
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/clear")
	@ResponseBody
	public AjaxResponse clearCart(HttpServletRequest request, HttpServletResponse response)
	{
		this.shoppingCartHelper.cleanShoppingCart(request, response);
		return AjaxResponse.success();
	}
	
}
