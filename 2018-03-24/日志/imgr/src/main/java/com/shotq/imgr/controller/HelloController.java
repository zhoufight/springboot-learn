package com.shotq.imgr.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("/")
	public String hello() {
		log.info("HelloController.hello");
		return "Hello World!";
	}
}
