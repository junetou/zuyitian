package org.andy.work.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CompoundImage {
	public static void main(String[] args) {
		try {
			File bgFile = new File("d:/conpou-template.jpg");
			File innerFile = new File("d:/qrcode.jpg");
			String destination = "d:/newImage.jpg";
			BufferedImage newImage = drawImageToImage(bgFile, innerFile, 100, 70);
			newImage = drawCharactersToImage(newImage, "400", 120, 645);
			ImageIO.write(newImage, getFileSuffix(destination), new File(destination));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param bgFile	背景图
	 * @param innerFile	要嵌入的图
	 * @param destination	文件保存的路径(包括文件名)例如：D:/images/image.jpg
	 * @param x	开始位置为背景图的x
	 * @param y	开始位置为背景图的y
	 * @return 返回一个图片缓冲区对象
	 * @throws IOException
	 */
	public static BufferedImage drawImageToImage(File bgFile, File innerFile, int x, int y) throws IOException {
//		加载背景图
		BufferedImage bgImage = ImageIO.read(bgFile);
//		加载要嵌入的图
		BufferedImage innerImage = ImageIO.read(innerFile);
		Graphics2D graphic = bgImage.createGraphics();
		graphic.drawImage(innerImage, x, y, null);
		graphic.dispose();
		return bgImage;
	}
	
	/**
	 * 
	 * @param bgImage	  背景图
	 * @param characters 要嵌入的文字
	 * @param x	开始位置为背景图的x
	 * @param y 开始位置为背景图的y
	 * @return 	返回一个图片缓冲区对象
	 */
	public static BufferedImage drawCharactersToImage(BufferedImage bgImage, String characters, int x, int y) {
		Graphics2D graphic = bgImage.createGraphics();
		graphic.setColor(Color.white);
		Font font = new Font("Helvetica Neue", Font.BOLD, 112);
		graphic.setFont(font);
		graphic.drawString(characters, x, y);
		graphic.dispose();
		return bgImage;
	}
	
	/**
	 * 获取文件的后缀名
	 * @param fileName 文件名
	 * @return
	 */
	public static String getFileSuffix(String fileName) {
		int beginIndex = fileName.lastIndexOf(".") + 1;
		int endIndex = fileName.length();
		String suffix = fileName.substring(beginIndex, endIndex);
		return suffix;
	}
}
