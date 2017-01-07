package com.yyqian.playground.mybatis.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2017-01-06T16:54:57+08:00.
 *
 * @author Yinyin Qian
 */
public class Transformer {
    private String keyMapper;
    private List<String> valueFilters = new ArrayList<>();

    public Transformer() {
    }

    public String getKeyMapper() {
        return keyMapper;
    }

    public void setKeyMapper(String keyMapper) {
        this.keyMapper = keyMapper;
    }

    public List<String> getValueFilters() {
        return valueFilters;
    }

    public void setValueFilters(List<String> valueFilters) {
        this.valueFilters = valueFilters;
    }
}
