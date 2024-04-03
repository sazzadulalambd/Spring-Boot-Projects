package com.example.authorization.trySpring;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HomeRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HomeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> findOne(int id) {
        String query = "SELECT id, name, age, department FROM employee WHERE id=?";
        return jdbcTemplate.queryForMap(query, id);
    }


}

//
//public Map<String, Object> findOne(int id) {
//    // SELECT statement
//    String query = "SELECT "
//            + "id,"
//            + "name,"
//            + "age,"   // Added missing comma here
//            + "department "
//            + "FROM employee "
//            + "WHERE id=?";
//
//    // Executing the query and fetching the result
//    Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);
//
//    return employee;
//}