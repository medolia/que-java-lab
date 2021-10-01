package com.medolia.lab.util.sql;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lbli
 * @date 2021/9/14
 */
public class UpdateBuilder extends BaseSqlBuilder{
    public UpdateBuilder(String tableName, List<String> cols) {
        super(tableName, cols);
    }

    @Override
    public String generate() {
        return StringUtils.EMPTY;
    }
}
