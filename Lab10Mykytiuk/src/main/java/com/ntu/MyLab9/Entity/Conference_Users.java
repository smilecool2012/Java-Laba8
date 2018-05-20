package com.ntu.MyLab9.Entity;

public class Conference_Users extends Parent{

    private long id;
    private long userid;
    private long conferenceid;

    public Conference_Users() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getConferenceid() {
        return conferenceid;
    }

    public void setConferenceid(long conferenceid) {
        this.conferenceid = conferenceid;
    }
}
