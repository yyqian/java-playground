package com.yyqian.playground.mybatis.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017-01-04T17:53:27+08:00.
 * Only Support Return type: List<Map<String, Object>>
 * @author Yinyin Qian
 */
public interface ViewMapper {
    List<Map<String, Object>> fetch();
}
