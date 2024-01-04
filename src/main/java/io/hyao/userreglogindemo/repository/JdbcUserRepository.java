package io.hyao.userreglogindemo.repository;

import io.hyao.userreglogindemo.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public class JdbcUserRepository implements UserRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public String registerNewUser(User user) {
        String sql = String.format("SELECT * FROM insert_user_func ('%s','%s')", user.getName(), user.getPassword());
        System.out.println(sql);
        List<Map<String, Object>> dataResult = jdbcTemplate.queryForList(sql);
        if (!dataResult.isEmpty()) {
            return (String) dataResult.get(0).get("new_id");
        }
        return null;
    }

    @Override
    public User getUserInfoByUserName(String userName) {
        String sql = String.format("SELECT * FROM query_user_func('%s')", userName);
        List<Map<String, Object>> dataResult = jdbcTemplate.queryForList(sql);
        if (!dataResult.isEmpty()) {
            String userId = (String) dataResult.get(0).get("user_id");
            String password = (String) dataResult.get(0).get("user_pwd");
            return new User(userId, userName, password, Set.of(""));
        }
        return null;
    }
}
