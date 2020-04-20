package com.env.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.env.io.entity.AlarmInfo;

public interface AlarmInfoRepository extends JpaRepository<AlarmInfo,Integer> {

}
