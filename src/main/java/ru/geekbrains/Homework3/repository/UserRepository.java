package ru.geekbrains.Homework3.repository;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.geekbrains.Homework3.model.User;


import java.util.List;

@Repository
public class UserRepository {
    private JdbcTemplate jdbcTemplate;

    //Вариант через jdbcTemplate
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //Запрос всех пользователей из бд
    public List<User> getUsers() {
        String sql = "SELECT * FROM Users;";
        RowMapper<User> userRowMapper = (rs, rowNum) -> {
            User user = new User();
            user.setName(rs.getString("name"));
            user.setAge(rs.getInt("age"));
            user.setEmail(rs.getString("email"));
            return user;
        };
        return jdbcTemplate.query(sql, userRowMapper);
    }

    //Добавление пользователя в бд
    public void save(User user) {
        String sql = "INSERT INTO Users (name,age,email) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, user.getName(), user.getAge(), user.getEmail());
    }


    //Базовый вариант через List

//    private List<User> users = new ArrayList<>();
//    public List<User> getUsers() {
//        return users;

//    }

//    public void setUsers1(List<User> users) {
//        this.users = users;
//    }
}
