package com.shotq.imgr.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shotq.imgr.dao.TestDao;

@Service
public class TestService {
	
	@Autowired
	private TestDao testDao;
	
	public List<Map<String,Object>> testJdbc() {
		return testDao.testJdbc();
	}

}
