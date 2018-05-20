package com.ntu.MyLab9.DAO;

import com.ntu.MyLab9.Entity.Conference_Users;

import java.util.List;

public interface Conference_UsersDAO {
    boolean addUserToConference(long conferenceid,long userid);
    boolean deleteUserFromConference(long id);

    List<Conference_Users> getAll();
}
