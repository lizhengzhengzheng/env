package com.env.io.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.env.io.entity.SiteYinzi;

public interface SiteYinziRepository extends JpaRepository<SiteYinzi,Integer>{

	List<SiteYinzi> findBySiteIdAndState(int siteId, int state);

}
