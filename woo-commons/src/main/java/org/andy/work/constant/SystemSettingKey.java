package org.andy.work.constant;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SystemSettingKey {
	
	// ORDER
	public static final String ORDER_EXPDATE = "order_expdate"; // 订单有效期
	public static final String ORDER_CONFIRM = "order_confirm";// 订单确认收货有效期
	//佣金明细
	public static final String COMMISSION_DAY = "commission_day"; // 领取佣金的时间
	public static final String ONE_LEVEL_DEDUCT = "one_levle_deduct";//一级分销商的佣金提成比例
	public static final String TOW_LEVEL_DEDUCT = "tow_levle_deduct";//二级分销商的佣金提成比例
	public static final String THREE_LEVEL_DEDUCT = "three_levle_deduct";//三级分销商的佣金提成比例
	public static final String BIG_PROPORTION = "big_proportion";//成为大伽分销商的条件
	public static final String BIG_SHOT_EXPDATE = "big_shot_expdate";//大咖过期天数
	public static final String BIG_SHOT_COMM = "big_shot_comm";//大咖每单得到的佣金
	public static final String BIG_SHOT_UPPER_COMM = "big_shot_upper_comm";//大咖的上级每单得到的佣金
	public static final String BIG_SHOT_TEN_COMM = "big_shot_ten_comm";//大咖的上级往后十人每单得到的佣金
	public static final String INVITE_QRCODE = "invite_qrcode";//邀请二维码
	
	public static final String WAREHOUSE_MODEL = "warehouse_model";//仓管人员的手机号码
	public static final String FINANCE_MODEL = "finance_model";//财务人员的手机号码
	public static final String CANVASS_MODEL = "canvass_model";//招商人员的手机号码
	
	public static final String WATERMARK_IMG = "watermark_img";//水印图片
	public static final String ISWATERMARK = "iswatermark";//是否开启水印
		
	// MEMBER
	public static final String POINTS_DEDUCTION = "points_ded"; // 积分抵扣
	public static final String REGISTER_POINTS_AWARD = "reg_points"; // 注册送积分
	
	//Web site
	public static final String WEB_SITE_TITLE = "web_site_title";//网站的标题
	public static final String WEB_SITE_KEYWORD = "web_site_keyword";//关键字
	public static final String WEB_SITE_DESC = "web_site_desc"; //网站描述
	public static final String WEB_SITE_COPYRIGHT = "web_site_copyright";//版权所有者
	public static final String WEB_SITE_AUTHOR = "web_site_author";//开发者
	
	
	@SuppressWarnings("rawtypes")
	public static List<String> getKeys() {
		try {
			List<String> keys = new ArrayList<String>();
			Class clazz = SystemSettingKey.class;
			Field[] fields = clazz.getFields();
			for (Field field : fields) {
				keys.add((String) field.get(clazz));
			}
			return keys;
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
