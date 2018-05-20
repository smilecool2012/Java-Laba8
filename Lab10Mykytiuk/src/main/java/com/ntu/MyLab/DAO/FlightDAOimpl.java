package com.ntu.MyLab.DAO;

import com.ntu.MyConnectionFactory;
import com.ntu.MyLab.Entity.Flight;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class FlightDAOimpl implements FlightDAO{
    @Override
    public Flight getFlightById(long id) {

        try (Connection connection = MyConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flight WHERE id=" + id);
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
    public List<Flight> getAllFlight() {
        try (Connection connection = MyConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM flight");
        ) {

            List<Flight> users = new ArrayList<>(); //змінна для формування списку

            while (rs.next()) {
                Flight user = extractUserFromResultSet(rs);
                users.add(user);  //додаємо одну до списку
            }

            return users; //повертаємо список

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertFlight(Flight user) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO flight(flightnumber,otpravlenie,naznachenie,kolichestvomest,kolichestvoprodanuh,stoimost) VALUES (?,?,?,?,?,?)");
        ) {
            ps.setLong(1, user.getFlightnumber());
            ps.setString(2, user.getOtpravlenie());
            ps.setString(3, user.getNaznachenie());
            ps.setLong(4,user.getKolichestvomest());
            ps.setLong(5,user.getKolichestvoprodanuh());
            ps.setLong(6,user.getStoimost());

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
    public boolean updateFlight(Flight user) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("UPDATE users set flightnumber=?,otpravlenie=?,naznachenie=?" +
                     ",kolichestvomest=?,kolichestvoprodanuh=?,stoimost=? WHERE id=?");
        ) {
            ps.setLong(1, user.getFlightnumber());
            ps.setString(2, user.getOtpravlenie());
            ps.setString(3, user.getNaznachenie());
            ps.setLong(4,user.getKolichestvomest());
            ps.setLong(5,user.getKolichestvoprodanuh());
            ps.setLong(6,user.getStoimost());
            ps.setLong(7, user.getId());

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
    public boolean deleteFlight(long id) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM flight WHERE id=?");
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

    private Flight extractUserFromResultSet(ResultSet rs) throws SQLException {

        Flight user = new Flight();

        user.setId(rs.getLong("id"));
        user.setFlightnumber(rs.getLong("flighnumber"));
        user.setOtpravlenie(rs.getString("otpravlenie"));
        user.setNaznachenie(rs.getString("otpravlenie"));
        user.setKolichestvomest(rs.getLong("kolichestvomest"));
        user.setKolichestvoprodanuh(rs.getLong("kolichestvoprodanuh"));
        user.setStoimost(rs.getLong("stoimost"));

        return user;

    }
}
