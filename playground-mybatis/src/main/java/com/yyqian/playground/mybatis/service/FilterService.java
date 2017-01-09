package com.yyqian.playground.mybatis.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

/**
 * Created on 2017-01-09T11:51:07+08:00.
 *
 * @author Yinyin Qian
 */
@Service
public class FilterService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilterService.class);

    private Map<String, BiFunction<Object, Object, Object>> filterRegister;

    {
        filterRegister = new HashMap<>();
        filterRegister.put("lowercase", (o, x) -> ((String)o).toLowerCase());
        filterRegister.put("uppercase", (o, x) -> ((String)o).toUpperCase());
        filterRegister.put("trim", (o, x) -> ((String)o).trim());
        filterRegister.put("date", (o, x) -> ((java.sql.Timestamp)o).toLocalDateTime().toLocalDate().toString());
        filterRegister.put("time", (o, x) -> ((java.sql.Timestamp)o).toLocalDateTime().toLocalTime().toString());
        filterRegister.put("drop", (o, x) -> null);
        // substring;0;5
        filterRegister.put("substring", (o, parameter) -> {
            String[] parts = ((String)parameter).split(";");
            return ((String)o).substring(Integer.valueOf(parts[0]), Integer.valueOf(parts[1]));
        });
        // map;0:1,2,3;1:4,5,6;2:7
        filterRegister.put("map", (o, parameter) -> {
            int from = (int)o;
            int to = from;
            String[] maps = ((String)parameter).split(";");
            for (String map : maps) {
                String[] parts = map.split(":");
                for (String opt : parts[1].split(",")) {
                    if (Integer.parseInt(opt) == from) {
                        to = Integer.parseInt(parts[0]);
                    }
                }
            }
            return to;
        });
    }

    public Object process(Object rawValue, String filter) {
        LOGGER.info("rawValue:{}, filter:{}", rawValue, filter);
        Object target = rawValue;
        if (filter.contains(";")) {
            int idx = filter.indexOf(";");
            String filterName = filter.substring(0, idx);
            String parameters = filter.substring(idx + 1);
            BiFunction<Object, Object, Object>  func = filterRegister.get(filterName);
            if (func != null) {
                target = func.apply(target, parameters);
            } else {
                LOGGER.warn("filter {} does not exist.", filterName);
            }
        } else {
            BiFunction<Object, Object, Object>  func = filterRegister.get(filter);
            if (func != null) {
                target = func.apply(target, null);
            } else {
                LOGGER.warn("filter {} does not exist.", filter);
            }
        }
        return target;
    }


}
