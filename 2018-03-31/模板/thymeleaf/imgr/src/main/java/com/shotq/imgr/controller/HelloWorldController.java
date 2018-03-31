package com.shotq.imgr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloWorldController {
	
	@RequestMapping("/helloworld")
	public String helloworld() {
		return "/pages/helloworld";
	}

}
