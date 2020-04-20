package com.env.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.env.io.entity.DictionaryType;

public interface DictionaryTypeRepository extends JpaRepository<DictionaryType,Integer> {
	
	DictionaryType findByTypeAndAlarmNumAndProjectIdAndState(int type, String alarmNum, int projectId, int state);
	
}
