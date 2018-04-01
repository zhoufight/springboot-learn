package com.shotq.imgr.dao;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> testJdbc() {
		List<Map<String,Object>> list = null;
		try {
			String querySql = "select * from imgr_log_info";
			list = jdbcTemplate.queryForList(querySql);
			log.info(list.toString());
		}catch(Exception e) {
			
		}
		return list;
	}

}
