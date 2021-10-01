package com.medolia.lab.util.sql;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lbli
 * @date 2021/9/14
 */
public class SelectBuilder extends BaseSqlBuilder {
    public SelectBuilder(String tableName, List<String> cols) {
        super(tableName, cols);
    }

    @Override
    public String generate() {
        StringBuilder result = new StringBuilder();
        String init = String.format("SELECT * FROM %s WHERE ", tableName);
        result.append(init);
        List<String> argList = cols.stream().map(e -> String.format("%s = ?", e)).collect(Collectors.toList());
        String argSeg = String.join(" AND ", argList);
        result.append(argSeg);
        return result.toString();
    }

    public String generateWithLimit(int limit) {
        return String.format("%s LIMIT %s", generate(), limit);
    }
}
