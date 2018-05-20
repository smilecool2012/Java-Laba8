package com.ntu.MyLab9.DAO;

import com.ntu.MyLab9.Entity.Conference;
import com.ntu.MyConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConferenceDAOimpl implements ConferenceDAO {

    @Override
    public Conference geConferenceById(long id) {
        try (Connection connection = MyConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM conference WHERE id=" + id);
        ) {
            if (rs.next()) {
                return extractConferenceFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }

        return null;
    }

    @Override
    public List<Conference> getAllConferences() {

        try (Connection connection = MyConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM conference");
        ) {

            List<Conference> conferences = new ArrayList<>(); //змінна для формування списку

            while (rs.next()) {
                Conference conference = extractConferenceFromResultSet(rs);
                conferences.add(conference);  //додаємо одну до списку
            }

            return conferences; //повертаємо список

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;

    }

    @Override
    public boolean insertConference(Conference conference) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO conference(conferencename, isavailable) VALUES (?,?)");
        ) {
            ps.setString(1, conference.getConferencename());
            ps.setBoolean(2, conference.isIsavailable());

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
    public boolean updateConference(Conference conference) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "UPDATE conference set conferencename = ?, isavailable = ? WHERE id=?");
        ) {
            ps.setString(1, conference.getConferencename());
            ps.setBoolean(2, conference.isIsavailable());
            ps.setLong(3, conference.getId());

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
    public boolean deleteConference(long id) {
        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM conference WHERE id=?");
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

    private Conference extractConferenceFromResultSet(ResultSet rs) throws SQLException {

        Conference conference = new Conference();

        conference.setId(rs.getLong("id"));
        conference.setConferencename(rs.getString("conferencename"));
        conference.setIsavailable(rs.getBoolean("isavailable"));

        return conference;

    }

}
