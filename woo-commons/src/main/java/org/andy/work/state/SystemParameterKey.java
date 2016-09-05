package org.andy.work.state;

/**
 * 文件保存路劲
 * @author Administrator
 *
 */
public class SystemParameterKey {

	//directory
	public static final String BRAND_IMG_DIR = "hb.dir.brand"; // 品牌文件的保存路劲  insert into woo_sys_param(s_key, val) values('hb.dir.brand', '/upload/brand')
	public static final String COMMODITY_IMG_DIR = "hb.dir.commodity";// 商品图片的保存路劲 insert into woo_sys_param(s_key, val) values('hb.dir.commodity', '/upload/commodity/list')

	//Excel 导入存放路劲
	public static final String IMPORT_STOCK_DIR = "hb.dir.import.stock";// 导入商品库存文件的保存路劲 insert into woo_sys_param(s_key, val) values('hb.dir.import.stock', '/upload/filestock/excel')
}
