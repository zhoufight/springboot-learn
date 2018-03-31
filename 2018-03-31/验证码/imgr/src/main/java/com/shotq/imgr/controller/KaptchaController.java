package com.shotq.imgr.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.code.kaptcha.Producer;
import com.shotq.imgr.constants.Constants;

@Controller
public class KaptchaController {
	
	@Autowired
	Producer producer;

	@RequestMapping("/kaptcha")
	public void kaptcha(HttpServletRequest request,HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		//图片
		response.setContentType("image/jpeg");
		//过期时间
		response.setDateHeader("Expires", 0);
		//设置没有缓存
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		
		String text = producer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
		
		BufferedImage bi = producer.createImage(text);
		
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {  
            out.flush();  
        } finally {  
            out.close();  
        }  
	}
}
