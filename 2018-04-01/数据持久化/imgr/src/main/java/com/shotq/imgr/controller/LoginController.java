package com.shotq.imgr.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shotq.imgr.constants.Constants;

@Controller
public class LoginController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/login")
	public String login() {

		return "/pages/login";
	}
	
	@RequestMapping("/validate")
	public String validate(@RequestParam Map<String,String> params,HttpServletRequest request,HttpServletResponse response) {
		String text = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String verifyCode = params.get("verifyCode");
		if(text.equals(verifyCode)) {
			log.info("验证成功");
		}else {
			log.info("验证失败");
		}
		return "";
	}

}
