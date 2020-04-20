package com.env.web.mapper;

import com.env.web.entity.YinziInfo;
import com.env.web.vo.YinziInfoVo;
import org.springframework.data.repository.query.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ${author}
 * @since 2020-03-30
 */
public interface YinziInfoMapper extends BaseMapper<YinziInfo> {

	IPage<YinziInfoVo> findListPage(Page<YinziInfo> page, @Param("yinzi")YinziInfo yinzi);

}
