package org.adny.work.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.adny.work.annotation.Secure;
import org.adny.work.annotation.Secure.RequestType;
import org.adny.work.controller.helper.AdditionalCodeHelper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/secure/verify_code")
public class VerifyCodeController {
	
	private static final Logger log = Logger.getLogger(VerifyCodeController.class);
	//验证码图片的宽度。
	private static final int imageWidth=70; 
	//验证码图片的高度。
	private static final int imageHeight=23; 
	//验证码字符个数
	private static final int codeCount=4; 

	//字体高度
	private int fontHeight = 18; 
	private int codeY = 17; 

	char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J',
	'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
	'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9' };
	
	
	
	@Resource
	private AdditionalCodeHelper additionalCodeHelper;
	
	
	@RequestMapping
	private void drawCode(HttpServletRequest request, HttpServletResponse response) {
		BufferedImage bufImg = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_BGR);
		Graphics2D graphics = bufImg.createGraphics();
		
		Color bgColor = new Color(255, 255, 255);
		graphics.setColor(bgColor);
		graphics.fillRect(0, 0, imageWidth, imageHeight);
		
		Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
		graphics.setFont(font);
		
		//画边框。
//		graphics.setColor(Color.GRAY); 
//		graphics.drawRect(0, 0, imageWidth - 1, imageHeight - 1);
		
		Random random = new Random();
		
		//随机产生50条干扰线，使图象中的认证码不易被其它程序探测到。
		graphics.setColor(Color.GRAY); 
		for(int i = 0; i < 20; i++) {
			int x = random.nextInt(imageWidth); 
			int y = random.nextInt(imageWidth); 
			int xl = random.nextInt(12); 
			int yl = random.nextInt(12); 
			graphics.drawLine(x, y, x + xl, y + yl); 
		}
		
		//randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode = new StringBuffer(); 
		int red = 0, green = 0, blue = 255; 

		//随机产生codeCount数字的验证码。
		int codeX = 6;
		for (int i = 0; i < codeCount; i++) {
			//得到随机产生的验证码数字。
			String strRand = String.valueOf(codeSequence[random.nextInt(32)]); 
			
			//将验证码绘制到图像中。
			graphics.setColor(new Color(red, green, blue)); 
			graphics.drawString(strRand, codeX, codeY); 

			//将产生的四个随机数组合在一起。
			randomCode.append(strRand);
			
			codeX += 14;
		}
		
		// 将四位数字的验证码保存到Session中。
		this.additionalCodeHelper.saveAdditionalCode(request, randomCode.toString());

		// 禁止图像缓存。
		response.setHeader("Pragma", "no-cache"); 
		response.setHeader("Cache-Control", "no-cache"); 
		response.setDateHeader("Expires", 0); 

		response.setContentType("image/jpeg"); 

		//将图像输出到Servlet输出流中。
		try {
			ServletOutputStream out = response.getOutputStream(); 
			ImageIO.write(bufImg, "jpeg", out); 
			out.flush();
			out.close();
		} catch (Exception e) {
			log.error(e);
		}
	}
	
	@RequestMapping(value="check")
	@ResponseBody
	@Secure(type=RequestType.AJAX)
	private Map<String, Boolean> checkVerifyCode(@RequestParam String code, HttpServletRequest request) {
		Map<String, Boolean> valid = new HashMap<String, Boolean>();
		if (this.additionalCodeHelper.isValid(code, request)) {
			valid.put("valid", true);
		} else {
			valid.put("valid", false);
		}
		return valid;
	}
}
