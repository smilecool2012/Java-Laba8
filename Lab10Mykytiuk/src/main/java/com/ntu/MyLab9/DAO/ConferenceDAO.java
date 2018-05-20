package com.ntu.MyLab9.DAO;

import com.ntu.MyLab9.Entity.Conference;

import java.util.List;

public interface ConferenceDAO {

    Conference geConferenceById(long id);
    List<Conference> getAllConferences();

    boolean insertConference(Conference conference);
    boolean updateConference(Conference conference);
    boolean deleteConference(long id);

}
