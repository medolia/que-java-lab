package com.medolia.lab.util.sql;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author lbli
 * @date 2021/9/14
 */
class SelectBuilderTest {
    @Test
    void testGenerateSelect() {
        String tableName = "query_detail";
        List<String> cols = Lists.newArrayList("query_date", "query_name", "host_id");
        SelectBuilder builder = new SelectBuilder(tableName, cols);
        assertEquals(builder.generate(), "SELECT * FROM query_detail WHERE query_date = ? AND query_name = ? AND host_id = ?");
    }

    @Test
    void testGenerateWithLimit() {
        String tableName = "query_detail";
        List<String> cols = Lists.newArrayList("query_date", "query_name", "host_id");
        int limit = 2;
        SelectBuilder builder = new SelectBuilder(tableName, cols);
        assertEquals(builder.generateWithLimit(limit),
                "SELECT * FROM query_detail WHERE query_date = ? AND query_name = ? AND host_id = ? LIMIT 2");
    }
}
