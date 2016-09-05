package org.adny.work.controller.helper;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adny.work.controller.cart.CartItemAdd;
import org.adny.work.controller.cart.CartItemDisplay;
import org.adny.work.security.UserSession;
import org.andy.work.appserver.model.IAccount;
import org.andy.work.appserver.model.ICartDetail;
import org.andy.work.appserver.model.ICartHead;
import org.andy.work.appserver.model.IProduct;
import org.andy.work.appserver.model.impl.CartDetail;
import org.andy.work.appserver.service.IProductMaintenanceService;
import org.andy.work.appserver.service.IShoppingCartMaintenanceService;
import org.andy.work.constant.WebConstants;
import org.andy.work.utils.MathUtil;
import org.andy.work.utils.StringUtil;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartHelper {
	
	@Resource	
	private UserSessionHelper userSessionHelper;
	@Resource
	private CookieHelper cookieHelper;
	@Resource
	private IShoppingCartMaintenanceService shoppingCartMaintenanceService;
	@Resource
	private IProductMaintenanceService productMaintenanceService;
	
	/**
	 * 将Cookie里的购物车转移到个人账户去
	 * @param request
	 * @param response
	 */
	public void addCookieCartItemsToAccountCartAfterLogin(HttpServletRequest request, HttpServletResponse response) {
		String[] cookieCartItems = this.getCookieCartItems(request);
		if (cookieCartItems != null && cookieCartItems.length > 0) {
			IAccount account = this.userSessionHelper.getUserAccount();
			ICartHead cartHead = this.shoppingCartMaintenanceService.getAccountCartHead(account.getId(), true);
			
			if (cartHead == null) {
				cartHead = this.shoppingCartMaintenanceService.createCartHead();
				cartHead.setAccount(account);
				cartHead.setCreatedDate(new Date());
			}
			
			boolean isNewCart = false;
			Set<ICartDetail> cartDetails = cartHead.getCartDetails();
			if (cartDetails == null) {
				cartDetails = new HashSet<ICartDetail>();
				isNewCart = true;
			}
			
			Set<ICartDetail> newCartDetails = new HashSet<ICartDetail>();
			for (int i = 0; i < cookieCartItems.length; i++) {
				String[] cookieCartItemValues = cookieCartItems[i].split("\\.");
				if (cookieCartItemValues.length >= 2) {
					Integer prodId = new Integer(cookieCartItemValues[0]);
					Double qty = new Double(cookieCartItemValues[1]);
					String selected = "N";
					if (cookieCartItemValues.length == 3) {
						selected = cookieCartItemValues[2];
					}
					
					if (Double.compare(qty, 0D) > 0) {
						IProduct product = this.productMaintenanceService.getProductById(prodId);
						if (product != null) {
							if (isNewCart) {
								ICartDetail cartDetail = new CartDetail();
								cartDetail.setCartHead(cartHead);
								cartDetail.setProduct(product);
								cartDetail.setQuantity(qty);
								cartDetail.setSelected(selected);
								
								cartDetails.add(cartDetail);
							} else {
								boolean isIncrement = false;
								for (ICartDetail cartDetail : cartDetails) {
									if (cartDetail.getProduct().getId().intValue() == prodId.intValue()) {
										qty = qty + cartDetail.getQuantity();
										cartDetail.setQuantity(qty);
										
										isIncrement = true;
										break;
									}
								}
								if (!isIncrement) {
									ICartDetail cartDetail = this.shoppingCartMaintenanceService.createCartDetail();
									cartDetail.setCartHead(cartHead);
									cartDetail.setProduct(product);
									cartDetail.setQuantity(qty);
									cartDetail.setSelected(selected);
									
									newCartDetails.add(cartDetail);
								}
							}
						}
					}
				}
			}
			if (!newCartDetails.isEmpty()) {
				cartDetails.addAll(newCartDetails);
			}
			cartHead.setCartDetails(cartDetails);
			cartHead.setLastUpdatedDate(new Date());
			
			if (cartHead.getId() == null) {
				this.shoppingCartMaintenanceService.persistCartHead(cartHead);
			} else {
				this.shoppingCartMaintenanceService.updateCartHead(cartHead);
			}
			this.putItemsIntoCookieCart(request, response, "");
		}
	}
	
	private String[] getCookieCartItems(HttpServletRequest request) {
		String shoppingCartValues = this.cookieHelper.getCookieValue(request, WebConstants.SHOPPING_CART_COOKIE);
		if (StringUtil.hasValue(shoppingCartValues)) {
			return shoppingCartValues.split("(#){1}");
		}
		
		return null;
	}
	
	private void putItemsIntoCookieCart(HttpServletRequest request, HttpServletResponse response, String cookieValue) {
		Cookie cookie = this.cookieHelper.getCookie(request, WebConstants.SHOPPING_CART_COOKIE);
		if (cookie != null) {
			try  {
				cookie.setValue(URLEncoder.encode(cookieValue, "UTF-8"));
			} catch (java.io.UnsupportedEncodingException e)  {
				cookie.setValue(cookieValue);
			}
			cookie.setPath("/");
			cookie.setMaxAge(30 * 24 * 60 * 60 ); // 30 days
			response.addCookie(cookie);
		} else  {
			Cookie newCookie = new Cookie(WebConstants.SHOPPING_CART_COOKIE, cookieValue);
			newCookie.setPath("/");
			newCookie.setMaxAge(30 * 24 * 60 * 60 ); // 30 days
			response.addCookie(newCookie);	
		}
	}
	
	public List<CartItemDisplay> getCartItemsForReview(HttpServletRequest request, HttpServletResponse response) {
		boolean isUserLogined = this.userSessionHelper.isLogined();
		if (isUserLogined) {
			return this.getCartItemsFromShoppingCart(request, false);
		} else {
			return this.getCartItemsFromCookie(request);
		}
	}
	
	private List<CartItemDisplay> getCartItemsFromCookie(HttpServletRequest request) {
		List<CartItemDisplay> items = new ArrayList<CartItemDisplay>();
		String shoppingCartValue = this.cookieHelper.getCookieValue(request, WebConstants.SHOPPING_CART_COOKIE);
		if (StringUtil.hasValue(shoppingCartValue)) {
			String[] cartItems = shoppingCartValue.split("(#){1}");
			for (int i = 0; i < cartItems.length; i++) {
				String[] cartItemValues = cartItems[i].split("\\.");
				if (cartItemValues.length >= 2) {
					Integer prodId = new Integer(cartItemValues[0]);
					IProduct product = this.productMaintenanceService.getProductById(prodId);
					if (product != null) {
						CartItemDisplay cartItem = new CartItemDisplay();
						try {
							cartItem.setOrderQty(new Integer(cartItemValues[1]));
						} catch (Exception e) {
							cartItem.setOrderQty(1);
						}
						cartItem.setId(product.getId());
						cartItem.setPrice(product.getPrice());
						cartItem.setProductName(product.getName());
						cartItem.setProdId(product.getId());
						if (cartItemValues.length == 3) {
							cartItem.setSelected(cartItemValues[2]);
						}
						items.add(cartItem);
					}
				}
			}
		}
		return items;
	}
	
	private List<CartItemDisplay> getCartItemsFromShoppingCart(HttpServletRequest request, boolean onlySelected) {
		UserSession userSession = this.userSessionHelper.getUserSession(request);
		ICartHead cartHead = this.shoppingCartMaintenanceService.getAccountCartHead(userSession.getAccountId(), true);
		if (cartHead != null && cartHead.getCartDetails() != null & !cartHead.getCartDetails().isEmpty()) {
			List<ICartDetail> cartDetails = this.sortCartItems(cartHead.getCartDetails());
			List<CartItemDisplay> cartItems = new ArrayList<CartItemDisplay>();
			for (ICartDetail cartDetail : cartDetails) {
				if (onlySelected) {
					if ("Y".equals(cartDetail.getSelected())) {
						CartItemDisplay  cartItem = this.toCartItemDisplay(cartDetail, userSession);
						if (cartItem != null) {
							cartItems.add(cartItem);
						}
					}
				} else {
					// cart view
					CartItemDisplay  cartItem = this.toCartItemDisplay(cartDetail, userSession);
					if (cartItem != null) {
						cartItems.add(cartItem);
					}
				}
			}
			return cartItems;
		}
		return null;
	}
	
	private List<ICartDetail> sortCartItems(Set<ICartDetail> cartItems) {
		List<ICartDetail> items = new ArrayList<ICartDetail>(cartItems);
		Collections.sort(items, new Comparator<ICartDetail>() {
			@Override
			public int compare(ICartDetail o1, ICartDetail o2) {
				if (o1.getId().intValue() < o2.getId().intValue()) {
					return 1;
				}
				return -1;
			}
		});
		return items;
	}
	
	private CartItemDisplay toCartItemDisplay(ICartDetail cartDetail, UserSession userSession) {
		IProduct product = cartDetail.getProduct();
		if (product != null) {
			CartItemDisplay cartItem = new CartItemDisplay();
			Double orderQty = cartDetail.getQuantity();
			cartItem.setOrderQty(orderQty.intValue());
			cartItem.setId(product.getId());
			cartItem.setPrice(product.getPrice());
			cartItem.setProductName(product.getName());
			cartItem.setProdId(product.getId());
			cartItem.setSelected(cartDetail.getSelected());	
			return cartItem;
		}
		return null;
	}

	public Double calculateTotalCartItemAmount(List<CartItemDisplay> cartItems) {
		Double totalAmount = 0D;
		if (cartItems != null && !cartItems.isEmpty()) {
			for (CartItemDisplay itm : cartItems) {
				if ("Y".equals(itm.getSelected())) {
					Double subAmount = MathUtil.round(new Double(itm.getPrice() * itm.getOrderQty()), 2).doubleValue(); 
					totalAmount =  MathUtil.round(totalAmount + subAmount, 2).doubleValue();
				}
			}
		}
		return totalAmount;
	}
	
	public void cartAddItems(HttpServletRequest request, HttpServletResponse response, List<CartItemAdd> products) {
		if (products != null && !products.isEmpty()) {	
			for (CartItemAdd itemAdd : products) {
				this.cartAddItem(request, response, itemAdd);
			}
		}
	}
	
	public void cartAddItem(HttpServletRequest request, HttpServletResponse response, CartItemAdd cartAdd) {
		
		boolean isLogined = this.userSessionHelper.isLogined();
		if (isLogined) {
			ICartHead cartHead = this.getOrCreateShoppingCart(request);
			
			Set<ICartDetail> cartDetails = cartHead.getCartDetails();
			if (cartDetails == null) {		
				IProduct product = this.productMaintenanceService.getProductById(cartAdd.getProdId());
				ICartDetail cartDetail = this.shoppingCartMaintenanceService.createCartDetail();
				cartDetail.setCartHead(cartHead);
				cartDetail.setQuantity(cartAdd.getOrderQty());
				cartDetail.setSelected(cartAdd.getSelected());
				cartDetail.setProduct(product);
					
				this.shoppingCartMaintenanceService.persistCartDetail(cartDetail);
			} else {
				boolean isIncrement = false;
				for (ICartDetail cartDetail : cartDetails) {
					if (cartDetail.getProduct().getId().intValue() == cartAdd.getProdId().intValue()) {
						Double newOrderQty = MathUtil.add(
									new BigDecimal(cartAdd.getOrderQty()), new BigDecimal(cartDetail.getQuantity())).doubleValue();
						cartDetail.setQuantity(newOrderQty);
						this.shoppingCartMaintenanceService.updateCartDetail(cartDetail);
						isIncrement = true;
						break;
					}
				}
					
				if (!isIncrement) {
					IProduct product = this.productMaintenanceService.getProductById(cartAdd.getProdId());
					ICartDetail cartDetail = this.shoppingCartMaintenanceService.createCartDetail();
					cartDetail.setCartHead(cartHead);
					cartDetail.setQuantity(cartAdd.getOrderQty());
					cartDetail.setSelected(cartAdd.getSelected());
					cartDetail.setProduct(product);
					this.shoppingCartMaintenanceService.persistCartDetail(cartDetail);
				}
			}
		} else {
			String shoppingCartValue = this.cookieHelper.getCookieValue(request, WebConstants.SHOPPING_CART_COOKIE);
			shoppingCartValue = this.addToCookieCart(shoppingCartValue, cartAdd);
			this.putItemsIntoCookieCart(request, response, shoppingCartValue);
		}
	}
	
	private ICartHead getOrCreateShoppingCart(HttpServletRequest request) {
		UserSession userSession = this.userSessionHelper.getUserSession(request);
		ICartHead cartHead = this.shoppingCartMaintenanceService.getAccountCartHead(userSession.getAccountId(), true);
		if (cartHead == null) {
			cartHead = this.shoppingCartMaintenanceService.createCartHead();
			cartHead.setAccount(this.userSessionHelper.getUserAccount());
			cartHead = this.shoppingCartMaintenanceService.persistCartHead(cartHead);
		}
		return cartHead;
	}
	
	private String addToCookieCart(String shoppingCartValue, CartItemAdd product) {
		StringBuffer newShoppingCartValue = new StringBuffer();
		if (StringUtil.hasValue(shoppingCartValue)) {
			String[] shoppingCartItems = shoppingCartValue.split("(#){1}");
			if (shoppingCartItems != null) {
				boolean isIncrement = false;
				for (int i = 0; i < shoppingCartItems.length; i++) {
					String[] cartItemValues = shoppingCartItems[i].split("\\.");
					if (cartItemValues.length >= 2) {
						String cartItemId = cartItemValues[0];
						String cartItemQty = cartItemValues[1];
						String selected = "N";
						if (cartItemValues.length == 3) {
							selected = cartItemValues[2];
						}
						if (cartItemId.equals(product.getProdId().toString())) {
							//增加数量
							Double orderQty = MathUtil.add(new BigDecimal(cartItemQty), new BigDecimal(product.getOrderQty())).setScale(BigDecimal.ROUND_DOWN).doubleValue();
							newShoppingCartValue.append(cartItemId + "." + orderQty.intValue() + "." + selected);
							isIncrement = true;
						} else {
							newShoppingCartValue.append(cartItemId + "." + cartItemQty + "." + selected);
						}
						newShoppingCartValue.append("#");
					}
				}
				
				if (!isIncrement) {
					//在购物车中添加一个新的购物车
					newShoppingCartValue.append(product.getProdId().toString() + "." 
							+ product.getOrderQty().intValue() + ".Y");
					newShoppingCartValue.append("#");
				}
			}
		} else {
			//在购物车中添加一个新的购物车
			newShoppingCartValue.append(product.getProdId().toString() + "." 
					+ product.getOrderQty().intValue() + ".Y");
			newShoppingCartValue.append("#");
		}
		
		return newShoppingCartValue.toString();
	}
	
	public void deleteCartItems(Integer[] prodIds, HttpServletRequest request, HttpServletResponse response) {
		if (prodIds != null && prodIds.length > 0) {
			boolean isLogined = this.userSessionHelper.isLogined();
			if (isLogined) {
				UserSession userSession = this.userSessionHelper.getUserSession(request);
				this.shoppingCartMaintenanceService.deleteCartItemByProdIds(userSession.getAccountId(), Arrays.asList(prodIds));
			} else {
				this.deleteCartItemsInCookie(prodIds, request, response);
			}
		}
	}
	
	private void deleteCartItemsInCookie(Integer[] prodIds, HttpServletRequest request, HttpServletResponse response) {
		StringBuffer newShoppingCartValue = new StringBuffer();
		String[] cartItems = this.getCookieCartItems(request);
		if (cartItems != null && cartItems.length > 0) {
			for (int i = 0; i < cartItems.length; i++) {
				String[] cartItemValues = cartItems[i].split("\\.");
				if (cartItemValues.length >= 2) {
					String cartItemId = cartItemValues[0];
					String cartQty = cartItemValues[1];
					String selected = "N";
					if (cartItemValues.length == 3) {
						selected = cartItemValues[2];
					}
					boolean removeIt = false;
					for (int j = 0; j < prodIds.length; j++) {
						Integer paramProdId = prodIds[j];
						if (paramProdId != null && cartItemId.equals(paramProdId.toString())) {
							removeIt = true;
							break;
						}
					}
					if (!removeIt) {
						newShoppingCartValue.append(cartItemId + "." + cartQty + "." + selected + "#");
					}
				}
			}
		}
		this.putItemsIntoCookieCart(request, response, newShoppingCartValue.toString());
	}
	
	public void cleanShoppingCart(HttpServletRequest request, HttpServletResponse response) {
		if (this.userSessionHelper.isLogined()) {
			UserSession userSession = this.userSessionHelper.getUserSession(request);
			this.shoppingCartMaintenanceService.cleanShoppingCart(userSession.getAccountId());
		} else {
			this.putItemsIntoCookieCart(request, response, "");
		}
	}
}
