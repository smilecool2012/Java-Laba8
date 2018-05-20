package com.ntu.MyLab9.DAO;

import com.ntu.MyLab9.Entity.Conference;
import com.ntu.MyLab9.Entity.Conference_Users;
import com.ntu.MyLab9.Entity.User;
import com.ntu.MyConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Conference_UsersDAOimpl implements Conference_UsersDAO {

    private UserDAO userDAO = new UserDAOimpl();
    private ConferenceDAO conferenceDAO = new ConferenceDAOimpl();

    @Override
    public boolean addUserToConference(long conferenceid, long userid) {

        Conference conference = conferenceDAO.geConferenceById(conferenceid);
        User user = userDAO.getUserById(userid);

        if (conference.isIsavailable() & !user.isInConference()) {

            try (Connection connection = MyConnectionFactory.getConnection();
                 PreparedStatement ps = connection.prepareStatement(
                         "INSERT INTO conference_users(userid, conferenceid) VALUES (?,?)");
            ) {
                ps.setLong(1, userid);
                ps.setLong(2, conferenceid);

                int i = ps.executeUpdate(); // for INSERT, UPDATE & DELETE

                if (i == 1) {

                    user.setInConference(true);
                    userDAO.updateUser(user);


                    Statement stmt = connection.createStatement();
                    ResultSet rs = stmt.executeQuery(
                            "SELECT COUNT(*) FROM conference_users WHERE conferenceid = "+'"'+conference.getId()+'"');

                    if (rs.next()) {
                        if(rs.getLong("COUNT(*)")>=4){
                            conference.setIsavailable(false);

                            conferenceDAO.updateConference(conference);
                        }
                    }
                    return true;
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean deleteUserFromConference(long id) {

        try (Connection connection = MyConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM conference_users WHERE id=" + id);
        ) {
            if (rs.next()) {
                long userid = rs.getLong("userid");
                long conferenceid = rs.getLong("conferenceid");

                User user = userDAO.getUserById(userid);
                user.setInConference(false);
                userDAO.updateUser(user);

                Conference conference = conferenceDAO.geConferenceById(conferenceid);
                conference.setIsavailable(true);
                conferenceDAO.updateConference(conference);

            }
        } catch (SQLException ex) {
            ex.printStackTrace();

        }



        try (Connection connection = MyConnectionFactory.getConnection();
             PreparedStatement ps = connection.prepareStatement("DELETE FROM conference_users WHERE id=?");
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

    @Override
    public List<Conference_Users> getAll() {
        try (Connection connection = MyConnectionFactory.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Conference_USERS");
        ) {

            List<Conference_Users> conferences = new ArrayList<>(); //змінна для формування списку

            while (rs.next()) {
                Conference_Users conference = extractConferenceFromResultSet(rs);
                conferences.add(conference);  //додаємо одну до списку
            }

            return conferences; //повертаємо список

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return null;
    }

    private Conference_Users extractConferenceFromResultSet(ResultSet rs) throws SQLException {

        Conference_Users conference_users = new Conference_Users();

        conference_users.setId(rs.getLong("id"));
        conference_users.setUserid(rs.getLong("userid"));
        conference_users.setConferenceid(rs.getLong("conferenceid"));

        return conference_users;

    }
}
