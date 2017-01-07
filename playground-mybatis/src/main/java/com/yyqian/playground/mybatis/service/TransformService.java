package com.yyqian.playground.mybatis.service;

import com.yyqian.playground.mybatis.domain.KeyKeyMap;

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
    public void modifyKey(Map<String, Object> row, List<KeyKeyMap> keyKeyMaps) {
        if (keyKeyMaps == null || row == null) {
            return;
        }
        keyKeyMaps.forEach(keyKeyMap -> {
            if (row.containsKey(keyKeyMap.getFrom())) {
                row.put(keyKeyMap.getTo(), row.remove(keyKeyMap.getFrom()));
            }
        });
    }

    public void modifyKey(List<Map<String, Object>> view, List<KeyKeyMap> keyKeyMaps) {
        if (view == null) {
            return;
        }
        view.forEach(row -> modifyKey(row, keyKeyMaps));
    }
}
