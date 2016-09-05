package org.andy.work.utils;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.log4j.Logger;

public class CommonFileUtils {
	
	private static final Logger log = Logger.getLogger(CommonFileUtils.class);
	
	/** alsd
	 * 获取文件保存路径
	 * @param request
	 * @return String
	 */
	public static String getFileSavePath(HttpServletRequest request, String directory) {
		String basePath = request.getSession().getServletContext().getRealPath(directory);
		log.info("basePath===============>"+basePath);
		basePath = basePath.replace("admin", "woo");
		File file = new File(basePath);
		if (!file.exists()) {
			file.mkdirs();
		}
		return basePath;
	}
	
	/** 
	* 创建文件 
	* @param path
	* @throws IOException 
	*/ 
	public static void createFile(String path) { 
		try {
			File filename = new File(path); 
			if (!filename.exists()) { 
				filename.createNewFile(); 
			} 
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	/** 
	* 写文件 
	* @param path 
	* @param text 
	* @throws IOException 
	*/
	public static boolean writeTxtFile(String path, String text) {
		FileWriterWithEncoding fileWriter = null;
		try {
			fileWriter = new FileWriterWithEncoding(path, "utf-8");
			fileWriter.write(text);
			fileWriter.flush();
			fileWriter.close();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		} 
		return true;
	}
	
	/**
	 * 得到项目名称
	 * @param request
	 * @return
	 */
	public static String getProjectName(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	/**
	 * 获得项目路径并替换项目名称
	 * @param request
	 * @param name 追加路径名称
	 * @param projectName 替换项目的名称
	 * @return 项目路径
	 */
	public static String getFilePath(HttpServletRequest request, String name, String projectName) {
		String path = request.getSession().getServletContext().getRealPath(name);
		path = path.replace(Config.get("project.name"), projectName);
		return path;
	}
	
	/**
	 * 获得项目路径
	 * @param request
	 * @param name 追加路径名称
	 * @return 项目路径
	 */
	public static String getFilePath(HttpServletRequest request, String name) {
		String path = request.getSession().getServletContext().getRealPath(name);
		return path;
	}
	
	/** 
	 * 删除单个文件 
	 * @param   sPath    被删除文件的文件名 
	 * @return 单个文件删除成功返回true，否则返回false 
	 */  
	public static void deleteFile(String path) {  
	    File file = new File(path);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	    }   
	}  
	
}
