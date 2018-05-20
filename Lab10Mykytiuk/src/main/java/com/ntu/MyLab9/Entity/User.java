package com.ntu.MyLab9.Entity;

public class User extends Parent{

    private long id;
    private String username;
    private String surname;
    private String nickname;
    private boolean inConference;

    public User() {
    }

    public User(long id, String username, String surname, String nickname, boolean inConference) {
        this.id = id;
        this.username = username;
        this.surname = surname;
        this.nickname = nickname;
        this.inConference = inConference;
    }

    public User(String username, String surname, String nickname) {
        this.username = username;
        this.surname = surname;
        this.nickname = nickname;
        this.inConference = false;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isInConference() {
        return inConference;
    }

    public void setInConference(boolean inConference) {
        this.inConference = inConference;
    }

    @Override
    public String toString() {

        System.out.println("User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", inConference=" + inConference +
                '}');

        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", surname='" + surname + '\'' +
                ", nickname='" + nickname + '\'' +
                ", inConference=" + inConference +
                '}';
    }
}
