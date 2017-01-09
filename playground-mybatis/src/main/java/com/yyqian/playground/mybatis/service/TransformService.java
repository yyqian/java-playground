package com.yyqian.playground.mybatis.service;

import com.yyqian.playground.mybatis.domain.KeyKeyMap;
import com.yyqian.playground.mybatis.domain.Transformer;
import com.yyqian.playground.mybatis.util.DataSourceUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created on 2017-01-06T15:36:47+08:00.
 *
 * @author Yinyin Qian
 */
@Service
public class TransformService {

    private final FilterService filterService;

    @Autowired
    public TransformService(FilterService filterService) {
        this.filterService = filterService;
    }

    public void transform(List<Map<String, Object>> view, List<Transformer> transformers) {
        if (view == null) {
            return;
        }
        view.forEach(row -> transform(row, transformers));
    }

    public void transform(Map<String, Object> row, List<Transformer> transformers) {
        if (transformers == null || row == null) {
            return;
        }
        transformers.forEach(transformer -> {
            KeyKeyMap keyKeyMap = DataSourceUtil.parseKeyKeyMap(transformer.getKeyMapper());
            if (row.containsKey(keyKeyMap.getFrom())) {
                String fromKey = keyKeyMap.getFrom();
                Object targetValue = process(row.get(fromKey), transformer.getValueFilters());
                row.remove(fromKey);
                if (targetValue != null) {
                    row.put(keyKeyMap.getTo(), targetValue);
                }
            }
        });
    }

    private Object process(Object rawValue, List<String> filters) {
        Object targetValue = rawValue;
        for (String filter : filters) {
            targetValue = filterService.process(targetValue, filter);
        }
        return targetValue;
    }
}
