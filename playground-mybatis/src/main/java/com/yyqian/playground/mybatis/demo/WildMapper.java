package com.yyqian.playground.mybatis.demo;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017-01-04T11:27:40+08:00.
 *
 * @author Yinyin Qian
 */
public interface WildMapper {
    List<Map<String, Object>> selectAll();
}
