package com.env.io.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.env.io.entity.MonitoringData;

public interface MonitoringDataRepository extends JpaRepository<MonitoringData,Integer>{

}
