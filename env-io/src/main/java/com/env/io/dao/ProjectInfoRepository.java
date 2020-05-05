package com.env.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.env.io.entity.ProjectInfo;

public interface ProjectInfoRepository extends JpaRepository<ProjectInfo,Integer> {
	
	ProjectInfo findById(int id);
	
}
