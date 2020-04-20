package com.env.io.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.env.io.entity.SiteInfo;

public interface SiteInfoRepository extends JpaRepository<SiteInfo,Integer> {
	
	List<SiteInfo> findByNetIpAndState(String netIp, int state);
	
	SiteInfo findByAccessCodeAndState(String accessCode, int state);
}
