package org.andy.work.admin.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileUploadServlet  extends HttpServlet {
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
	
			DiskFileItemFactory factory = new DiskFileItemFactory();
			      String path = request.getRealPath("/upload");//得到要保存上传文件的目录的绝对路径。
			System.out.println("path:"+ path);
			factory.setRepository(new File(path));
			factory.setSizeThreshold(1024 * 1024);
			ServletFileUpload upload=new ServletFileUpload(factory);
			try{
			//这个方法是从请求中得到所有的FileItem。
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item : list){
			String name=item.getFieldName();
			if(item.isFormField()){
			String value=item.getString();
			System.out.println("name: "+name+" value: "+value);
			request.setAttribute(name, value);
			}
			else{
			String value=item.getName();
			int start=value.lastIndexOf("\\");
			String fileName=value.substring(start+1);
			System.out.println("name: "+name+" value: "+fileName);
			item.write(new File(path,fileName));
			}
			}
			}
			catch(Exception ex){
			ex.printStackTrace();
			}
			request.getRequestDispatcher("index.jsp").forward(request, response);
			}
}
