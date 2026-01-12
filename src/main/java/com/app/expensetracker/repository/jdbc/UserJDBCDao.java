package com.app.expensetracker.repository.jdbc;

import com.app.expensetracker.dao.User;
import com.app.expensetracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of UserRepository.
 * Only active when 'local' profile is enabled.
 */
@Repository
@Profile("staging")
@RequiredArgsConstructor
public class UserJDBCDao implements UserRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void saveUser(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName());
        jdbcTemplate.update("INSERT INTO EXPT_USER (first_name, last_name) VALUES (:firstName, :lastName)",
                params);
    }

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query("SELECT * FROM EXPT_USER", (rs, rn) -> User.builder()
                .id(rs.getInt("id"))
                .firstName(rs.getString("first_name"))
                .lastName(rs.getString("last_name"))
                .build()
        );
    }

    @Override
    public User findById(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        
        List<User> users = jdbcTemplate.query(
                "SELECT * FROM EXPT_USER WHERE id = :id",
                params,
                (rs, rn) -> User.builder()
                        .id(rs.getInt("id"))
                        .firstName(rs.getString("first_name"))
                        .lastName(rs.getString("last_name"))
                        .build()
        );
        
        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public void deleteById(Integer id) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);
        jdbcTemplate.update("DELETE FROM EXPT_USER WHERE id = :id", params);
    }

    @Override
    public void updateUser(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName());
        jdbcTemplate.update(
                "UPDATE EXPT_USER SET first_name = :firstName, last_name = :lastName WHERE id = :id",
                params
        );
    }
}
