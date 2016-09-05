package org.andy.work.utils;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.imageio.plugins.jpeg.*;

@SuppressWarnings("restriction")
public class ImagesUtils {
	
	/*
	 * 根据尺寸图片居中裁剪
	 */
	public static void cutCenterImage(String src, String dest, int w, int h) throws IOException {
		String endName = getFormatName(src);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(endName);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, endName, new File(dest));
	}

	/*
	 * 图片裁剪二分之一
	 */
	public static void cutHalfImage(String src, String dest) throws IOException {
		String endName = getFormatName(src);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(endName);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		int width = reader.getWidth(imageIndex) / 2;
		int height = reader.getHeight(imageIndex) / 2;
		Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, endName, new File(dest));
	}
	
	/*
	 * 图片裁剪通用接口
	 */
	public static void cutImage(String src, String dest, int x, int y, int w, int h) throws IOException {
		String endName = getFormatName(src);
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName(endName);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);
		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, endName, new File(dest));

	}

	/*
	 * 图片缩放
	 */
	public static void zoomImage(String src, String dest, int w, int h) throws Exception {
		double wr = 0, hr = 0;
		File srcFile = new File(src);
		File destFile = new File(dest);
		BufferedImage bufImg = ImageIO.read(srcFile);
		Image Itemp = bufImg.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
		wr = w * 1.0 / bufImg.getWidth();
		hr = h * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		Itemp = ato.filter(bufImg, null);
		try {
			ImageIO.write((BufferedImage) Itemp, dest.substring(dest.lastIndexOf(".") + 1), destFile);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * 图片缩放
	 */
	public static void zoomFileImage(String src, String dest, float desLength) throws Exception {
		BufferedImage result = null;
		File srcfile = new File(src);
		BufferedImage im = ImageIO.read(srcfile);
//		原始图像的宽度和高度 
		int width = im.getWidth();
		int height = im.getHeight();
//		 压缩计算
		float resizeTimes = 0f;
		if(width >= height) {
			resizeTimes = desLength / height;
		} else {
			resizeTimes = desLength / width;
		}
		
//		压缩后的宽高
		int toWidth = (int) (width * resizeTimes);
		int toHeight = (int) (height * resizeTimes);
		
		result = new BufferedImage(toWidth, toHeight, BufferedImage.TYPE_INT_RGB);

		result.getGraphics().drawImage(im.getScaledInstance(toWidth, toHeight, java.awt.Image.SCALE_SMOOTH), 0, 0, null);
		
//		输出到文件流 
		FileOutputStream newimage = new FileOutputStream(new File(dest));
		
		JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO.getImageWritersBySuffix("jpg").next();
        ImageOutputStream ios = ImageIO.createImageOutputStream(newimage);
        imageWriter.setOutput(ios);
		
		JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter.getDefaultWriteParam();
        jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
        jpegParams.setCompressionQuality(1f);
		
		ImageIO.write(result, "jpg", newimage);
		
//		近JPEG编码
		newimage.close();
	}
	
	private static String getFormatName(String imageName) {
		int beginIndex = imageName.lastIndexOf(".") + 1;
		String result = imageName.substring(beginIndex, imageName.length());
		return result;
	}
	
	/**   
     * @param args   
     */    
    public static void main(String[] args) {     
    	try {
    		String iconPath = "E:/projcet/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/staticResources/static/images/dayouyunshangLogo.png";
    		String newImageName = "E:/projcet/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/staticResources/upload/img/ueditor/2016/3/30/dad701e3-f1b2-4d30-9af5-e06558a2f26b.png";
    		ImagesUtils.pressImage(iconPath, newImageName, 10 , 10);
		} catch (Exception e) {
			e.printStackTrace();
		}	
    }   
	
	 /**   
     * 给图片添加水印   
     * @param iconPath 水印图片路径   
     * @param srcImgPath 源图片路径   
     * @param targerPath 目标图片路径   
     */    
    public static void markImageByIcon(String iconPath, String srcImgPath,     
            String targerPath) {     
        markImageByIcon(iconPath, srcImgPath, targerPath, null) ;   
    }     
    /**   
     * 给图片添加水印、可设置水印图片旋转角度   
     * @param iconPath 水印图片路径   
     * @param srcImgPath 源图片路径   
     * @param targerPath 目标图片路径   
     * @param degree 水印图片旋转角度 
     */    
    public static void markImageByIcon(String iconPath, String srcImgPath,     
            String targerPath, Integer degree) {     
        OutputStream os = null;     
        try {     
            Image srcImg = ImageIO.read(new File(srcImgPath));   
            BufferedImage buffImg = new BufferedImage(srcImg.getWidth(null),     
                    srcImg.getHeight(null), BufferedImage.TYPE_INT_RGB);   
            // 得到画笔对象     
            // Graphics g= buffImg.getGraphics();     
            Graphics2D g = buffImg.createGraphics();     
    
            // 设置对线段的锯齿状边缘处理     
            g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,     
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);     
    
            g.drawImage(srcImg.getScaledInstance(srcImg.getWidth(null), srcImg     
                    .getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);     
    
            if (null != degree) {     
                // 设置水印旋转     
                g.rotate(Math.toRadians(degree),     
                        (double) buffImg.getWidth() / 2, (double) buffImg     
                                .getHeight() / 2);     
            }     
            // 水印图象的路径 水印一般为gif或者png的，这样可设置透明度    
            ImageIcon imgIcon = new ImageIcon(iconPath);     
            // 得到Image对象。     
            Image img = imgIcon.getImage();     
            float alpha = 1f; // 透明度     
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));     
            //表示水印图片的位置
            g.drawImage(img, srcImg.getWidth(null) - 180, srcImg.getHeight(null) - 100, null);
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));     
            g.dispose();     
            os = new FileOutputStream(targerPath);     
            // 生成图片     
            ImageIO.write(buffImg, "JPG", os);     
        } catch (Exception e) {     
            e.printStackTrace();     
        } finally {     
            try {     
                if (null != os)     
                    os.close();     
            } catch (Exception e) {     
                e.printStackTrace();     
            }     
        }     
    }     
	
    /***
     * @param path 文件目录 
     * @param oldname  原来的文件名 
     * @param newname 新文件名 
     */ 
     public static void copyFile(String file1,String file2)throws IOException{
     	FileInputStream fis=new FileInputStream(file1);
     	FileOutputStream fos=new FileOutputStream(file2);
     	int temp;
     	while((temp=fis.read())!=-1){
     		fos.write(temp);
     	}
 	  	fis.close();
 	  	fos.close();
     }
     
     /**
 	 * 把图片印刷到图片上
 	 * 
 	 * @param pressImg
 	 *            水印文件
 	 * @param targetImg
 	 *            目标文件
 	 * @param x
 	 *            x坐标
 	 * @param y
 	 *            y坐标
 	 */
	public final static void pressImage(String pressImg, String targetImg,
 			int x, int y) {
 		try {
 			// 目标文件
 			File _file = new File(targetImg);
 			Image src = ImageIO.read(_file);
 			int width = src.getWidth(null);
 			int height = src.getHeight(null);
 			BufferedImage image = new BufferedImage(width, height,
 					BufferedImage.TYPE_INT_RGB);
 			Graphics g = image.createGraphics();
 			g.drawImage(src, 0, 0, width, height, null);

 			// 水印文件
 			File _filebiao = new File(pressImg);
 			Image src_biao = ImageIO.read(_filebiao);
 			int width_biao = src_biao.getWidth(null);
 			int height_biao = src_biao.getHeight(null);
 			
 			//g.drawImage(src_biao, (width - width_biao),
 			//		(height - height_biao), width_biao, height_biao, null);
 			g.drawImage(src_biao, (width - width_biao) - x,  (height - height_biao) - y, width_biao, height_biao, null);
 			// 水印文件结束
 			g.dispose();
 			FileOutputStream out = new FileOutputStream(targetImg);
 
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
 			encoder.encode(image);
 			out.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
 	}
	
	public static void pressTextForPic(String pressText, String targetImg,
			String fontName, int fontStyle, Color color, int fontSize, int x, int y) {
		try {
			File _file = new File(targetImg);
			Image src = ImageIO.read(_file);
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			BufferedImage image = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			Graphics g = image.createGraphics();
			g.drawImage(src, 0, 0, width, height, null);

			g.setColor(color);
			g.setFont(new Font(fontName, fontStyle, fontSize));

			g.drawString(pressText, x, y);
			g.dispose();
			FileOutputStream out = new FileOutputStream(targetImg);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(image);
			out.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
