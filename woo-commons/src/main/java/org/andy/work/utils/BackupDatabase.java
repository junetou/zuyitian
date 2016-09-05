package org.andy.work.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupDatabase {
	
	/**
	 * 备份数据库MYSQL(需要配置环境变量)
	 * @param host	IP地址	例如: 192.168.1.1
	 * @param userName 	数据库登录名
	 * @param password	数据库登录密码
	 * @param database	要备份的数据库
	 * @param savePath	数据库保存的路径	例如: D:/SQL
	 * @param fileName	数据库备份的名称	例如: 20160128.sql
	 * @return boolean true: 备份成功, false: 备份失败
	 */
	public static boolean backup(String host, String userName, String password, String database, String savePath, String fileName) {
		File saveFile = new File(savePath);
//		如果不存在这个路径，则创建这个路径
		if (!saveFile.exists()) saveFile.mkdirs();
//		如果保存路径不是以"/"或者"\\"结尾，则自动加上去
		if (!savePath.endsWith(File.separator)) savePath = savePath + File.separator;
		
		PrintWriter printWriter = null;
		BufferedReader bufferedReader = null;
		try {
			OutputStream out = new FileOutputStream(savePath + fileName);
			OutputStreamWriter outWriter = new OutputStreamWriter(out, "utf8");
			printWriter = new PrintWriter(outWriter);
			
			StringBuffer buff = new StringBuffer(" mysqldump -h");
			buff.append(host).append(" -u").append(userName).append(" -p");
			buff.append(password).append(" --set-charset=utf8 ").append(database);
			Process process = Runtime.getRuntime().exec(buff.toString());
			InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
			bufferedReader = new BufferedReader(inputStreamReader);
			
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				printWriter.println(line);
			}
			printWriter.flush();
			if (process.waitFor() == 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) bufferedReader.close();
				if (printWriter != null) printWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		String host = "192.168.1.168";
		String userName = "root";
		String password = "dykj321_r";
		String database = "db_dykj";
		String savePath = "D:/mysqlbackup";
		String fileName = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + ".sql";
		if(backup(host, userName, password, database, savePath, fileName)) {
			System.out.println("备份成功, " + savePath + "/" + fileName);
		} else {
			System.out.println("备份失败");
		}
	}
}
