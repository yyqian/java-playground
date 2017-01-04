package com.yyqian.playground.mybatis.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017-01-04T17:53:27+08:00.
 *
 * @author Yinyin Qian
 */
public interface BaseMapper {
    List<Map<String, Object>> selectAll();
}
