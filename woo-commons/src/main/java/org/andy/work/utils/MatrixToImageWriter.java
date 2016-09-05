package org.andy.work.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public final class MatrixToImageWriter {

	private static final int BLACK = 0xFF000000;
	private static final int WHITE = 0xFFFFFFFF;

	private MatrixToImageWriter() { }

	private static BufferedImage toBufferedImage(BitMatrix matrix) {
		int width = matrix.getWidth();
		int height = matrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x ++) {
			for (int y = 0; y < height; y ++) {
				image.setRGB(x, y, matrix.get(x, y) ? BLACK : WHITE);
			}
		}
		return image;
	}

	private static void writeToFile(BitMatrix matrix, String format, File file) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, file)) {
			throw new IOException("Could not write an image of format " + format + " to " + file);
		}
	}

	/*private static void writeToStream(BitMatrix matrix, String format, OutputStream stream) throws IOException {
		BufferedImage image = toBufferedImage(matrix);
		if (!ImageIO.write(image, format, stream)) {
			throw new IOException("Could not write an image of format " + format);
		}
	}*/
	
	public static void saveQrCode(String url, String filePath, String fileName, int width, int height) {
		try {
			 File diskPath = new File(filePath);
			 if (!diskPath.exists()) { diskPath.mkdirs(); }
		     MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
		     Map<EncodeHintType, String> hints = new HashMap<EncodeHintType, String>();
		     hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		     BitMatrix bitMatrix = multiFormatWriter.encode(url, BarcodeFormat.QR_CODE, width, height, hints);
		     File file = new File(filePath, fileName);
		     String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		     MatrixToImageWriter.writeToFile(bitMatrix, suffix, file);
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	}
	
	public static void main(String[] args) {
		saveQrCode("weixin://wxpay/bizpayurl?pr=bDcdlWe", "D:/", "123.jpg", 400, 400);
	}

}