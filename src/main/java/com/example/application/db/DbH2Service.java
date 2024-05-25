package com.example.application.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DbH2Service {

    private static final Logger logger = LoggerFactory.getLogger(DbH2Service.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String createTable() {
        try {
            jdbcTemplate.execute("create table if not exists people (id int primary key "
                    + "auto_increment, name varchar(30), email varchar(30))");
        } catch (Exception e) {
            return "error message:" + e.getMessage();
        }
        return "table is created";
    }

    public void fetch() {
        try {
            List<String> list = jdbcTemplate.queryForList("select name from people", String.class);
            if (list.isEmpty()) {
                logger.info("fetch: table exists but there is no data");
            } else {
                List<String> plist = new ArrayList<>(list);
                for (String s : plist) {
                    logger.info("fetch: data: {}", s);
                }
            }
        } catch (Exception e) {
            logger.info("fetch: error message:", e.getMessage());
        }

    }

    public String insertData(String name, String email) {
        int intCount = 0;
        try {
            intCount = jdbcTemplate.update("insert into people (name, email) "
                    + " values ('" + name + "', '" + email + "')");
        } catch (Exception e) {
            return "error message:" + e.getMessage();
        }
        return "inserted " + intCount + " records";
    }

    public List<String> fetchListString() {
        List<String> listString = new ArrayList<>();
        try {
            List<String> list = jdbcTemplate.queryForList("select name from people", String.class);
            if (list.isEmpty()) {
                logger.info("fetchList: table exists but there is no data");
            } else {
                for (int r = 0; r < list.size(); r++) {
                    listString.add(list.get(r));
                }
            }
        } catch (Exception e) {
            logger.error("fetchList: error message:{}", e.getMessage());
        }
        return listString;
    }

    public String deleteAllData() {
        try {
            int intCount = jdbcTemplate.update("delete people");
            return "deleted " + intCount + " records";
        } catch (Exception e) {
            return "error message:" + e.getMessage();
        }
    }

}
