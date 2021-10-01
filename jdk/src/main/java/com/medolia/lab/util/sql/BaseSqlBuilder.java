package com.medolia.lab.util.sql;

import java.util.List;

/**
 * @author lbli
 * @date 2021/9/14
 */
public abstract class BaseSqlBuilder {

    protected String tableName;
    protected List<String> cols;

    public BaseSqlBuilder(String tableName, List<String> cols) {
        this.tableName = tableName;
        this.cols = cols;
    }

    /**
     * 生成 sql 语句
     */
    public abstract String generate();
}
