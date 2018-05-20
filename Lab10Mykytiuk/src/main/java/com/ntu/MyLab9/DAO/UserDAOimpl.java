package com.ntu.MyLab9.DAO;

import com.ntu.MyLab9.Entity.User;
import com.ntu.MyConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOimpl implements UserDAO {
    @Override
    public User getUserById(long id) {

        try (Connection connection = MyConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id=" + id);
        ) {
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public List<User> getAllUsers() {
        try (Connection connection = MyConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users");
        ) {

            List<User> users = new ArrayList<>(); //змінна для формування списку

            while (rs.next()) {
                User user = extractUserFromResultSet(rs);
                users.add(user);  //додаємо одну до списку
            }

            return users; //повертаємо список

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertUser(User user) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO users(username, surname, nickname) VALUES (?,?,?)");
        ) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getNickname());

            int i = ps.executeUpdate(); // for INSERT, UPDATE & DELETE

            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE users set username = ?, surname = ?, nickname = ?, inconference = ? WHERE id=?");
        ) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getSurname());
            ps.setString(3, user.getNickname());
            ps.setBoolean(4, user.isInConference());
            ps.setLong(5, user.getId());

            int i = ps.executeUpdate(); // for INSERT, UPDATE & DELETE

            if (i == 1) {
                return true;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean deleteUser(long id) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM users WHERE id=?");
        ) {
            ps.setLong(1, id);
            int i = ps.executeUpdate(); // for INSERT, UPDATE & DELETE
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private User extractUserFromResultSet(ResultSet rs) throws SQLException {

        User user = new User();

        user.setId(rs.getLong("id"));
        user.setUsername(rs.getString("username"));
        user.setSurname(rs.getString("surname"));
        user.setNickname(rs.getString("nickname"));
        user.setInConference(rs.getBoolean("inconference"));

        return user;

    }
}
