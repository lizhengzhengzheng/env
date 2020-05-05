package com.env.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.env.io.entity.YinziInfo;

public interface YinziInfoRepository extends JpaRepository<YinziInfo,Integer>{
	
	YinziInfo findByYinziNumAndStateAndProjectId(String yinziNum, int state, int projectId);
	
}
