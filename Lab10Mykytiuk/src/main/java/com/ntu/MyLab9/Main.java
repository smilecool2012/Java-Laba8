package com.ntu.MyLab9;


import com.ntu.MyLab9.DAO.*;
import com.ntu.MyLab9.Entity.Conference;
import com.ntu.MyLab9.Entity.User;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        UserDAO userDAO = new UserDAOimpl();
        ConferenceDAO conferenceDAO = new ConferenceDAOimpl();
        Conference_UsersDAO conference_usersDAO = new Conference_UsersDAOimpl();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("1: create new user      6:  create new conference" );
            System.out.println("2: get user by id       7:  get conference by id" );
            System.out.println("3: get all users        8:  get all conferences" );
            System.out.println("4: update user by id    9:  update conference by id" );
            System.out.println("5: delete user by id    10: delete conference by id " );
            System.out.println("11:add User To Conference   12: delete user from conference " );

            System.out.print("-> ");

            String s = sc.nextLine();
            switch (s) {
                case "1":

                    System.out.print("Внесіть username: ");
                    String username = sc.nextLine();
                    System.out.print("Внесіть surname: ");
                    String surname = sc.nextLine();
                    System.out.print("Внесіть nickName: ");
                    String nickName = sc.nextLine();
                    User userToCreate = new User(username, surname, nickName);
                    userDAO.insertUser(userToCreate);

                    break;
                case "2":
                    System.out.print("Внесіть ID user: ");
                    long userIdToGetByID = sc.nextLong();
                    User userToGetByID = userDAO.getUserById(userIdToGetByID);
                    userToGetByID.toString();

                    break;
                case "3":

                    List<User> userList = userDAO.getAllUsers();
                    userList.forEach(User::toString);

                    break;
                case "4":

                    System.out.print("Внесіть ID user: ");
                    long userIdToUpdate = sc.nextLong();
                    User userToUpdate = userDAO.getUserById(userIdToUpdate);

                    System.out.print("Внесіть new username: ");
                    userToUpdate.setUsername(sc.nextLine());
                    System.out.print("Внесіть surname: ");
                    userToUpdate.setSurname(sc.nextLine());
                    System.out.print("Внесіть nickName: ");
                    userToUpdate.setNickname(sc.nextLine());
                    userDAO.updateUser(userToUpdate);

                    break;
                case "5":

                    System.out.print("Внесіть ID user: ");
                    long userIdToDelete = sc.nextLong();
                    userDAO.deleteUser(userIdToDelete);

                    break;
                case "6":

                    System.out.print("Внесіть conferencename: ");
                    String conferencename = sc.nextLine();
                    Conference conference = new Conference(conferencename);
                    conferenceDAO.insertConference(conference);

                    break;
                case "7":

                    System.out.print("Внесіть ID conference: ");
                    long conferenceIdToGetByID = sc.nextLong();
                    Conference conferenceToGetByID = conferenceDAO.geConferenceById(conferenceIdToGetByID);
                    conferenceToGetByID.toString();

                    break;
                case "8":

                    List<Conference> conferenceList = conferenceDAO.getAllConferences();
                    conferenceList.forEach(Conference::toString);

                    break;
                case "9":

                    System.out.print("Внесіть ID conference: ");
                    long conferenceIdToUpdate = sc.nextLong();
                    Conference conferenceToUpdate = conferenceDAO.geConferenceById(conferenceIdToUpdate);
                    System.out.print("Внесіть new conferencename: ");
                    conferenceToUpdate.setConferencename(sc.nextLine());
                    conferenceDAO.updateConference(conferenceToUpdate);

                    break;
                case "10":

                    System.out.print("Внесіть ID conference: ");
                    long conferenceIdToDelete = sc.nextLong();
                    conferenceDAO.deleteConference(conferenceIdToDelete);

                    break;
                case "11":
                    System.out.print("Внесіть ID user: ");
                    long userIdToAdd = sc.nextLong();
                    System.out.print("Внесіть ID conference: ");
                    long conferenseIdToAdd = sc.nextLong();

                    conference_usersDAO.addUserToConference(conferenseIdToAdd,userIdToAdd);

                    break;
                case "12":
                    System.out.print("Внесіть ID: ");
                    long conferense_userid = sc.nextLong();

                    conference_usersDAO.deleteUserFromConference(conferense_userid);
                    break;
                case "13":
                    break;
                default:
                    return;
            }

        }
    }

}
