package com.ntu.MyLab10;

import com.ntu.MyLab9.DAO.*;
import com.ntu.MyLab9.Entity.Conference;
import com.ntu.MyLab9.Entity.Conference_Users;
import com.ntu.MyLab9.Entity.Parent;
import com.ntu.MyLab9.Entity.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.List;


public class Main extends Application {


    //private static TextField textFieldUserId;
    private static TextField textFieldUserName;
    private static TextField textFieldUserSurname;
    private static TextField textFieldUserNickname;
    private static TextField textFieldUserInConference;

    //private static TextField textFieldConferenceId;
    private static TextField textFieldConferenceName;
    private static TextField textFieldConferenceIsAvailable;

    //private static TextField textFieldConfrrenceUserId;
    private static TextField textFieldConfrrenceUserUserId;
    private static TextField textFieldConfrrenceUserConferenceId;

    private static boolean isItAUserTable;
    private static boolean isItAConferenceTable;
    private static boolean isItAConferenceUserTable;

    private static long userIdToUpdate;
    private static long conferenceIdToUpdate;

    private static UserDAO userDAO = new UserDAOimpl();
    private static ConferenceDAO conferenceDAO = new ConferenceDAOimpl();
    private static Conference_UsersDAO conferenceUsersDAO = new Conference_UsersDAOimpl();

    //Сама таблица
    private static TableView<Parent> table;
    //Лист с данными из бд для таблицы
    private static ObservableList<Parent> list = FXCollections.observableArrayList();


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        createStartMenu(primaryStage);
    }

    private void createStartMenu(Stage stage) {

        //////сцена//////
        AnchorPane root = new AnchorPane();
        stage.setTitle("Lab 10");
        Scene scene = new Scene(root, 500, 260);
        stage.setScene(scene);
        stage.show();

        Label label = new Label("WELCOME!");
        label.setFont(new Font("Arial", 70));
        label.setTranslateX(50);
        label.setTranslateY(50);
        Label label1 = new Label("By Drobotovich");
        label1.setFont(new Font("Arial", 20));
        label1.setTranslateX(170);
        label1.setTranslateY(160);
        root.getChildren().addAll(label,label1);

        //////кнопка 1//////
        Button btnUserTable = UtilsUI.createButton("User Table", 30, 200);
        btnUserTable.setOnAction(event -> {

            isItAUserTable = true;
            isItAConferenceTable = false;
            isItAConferenceUserTable = false;

            createDialogMenu(stage);
        });

        //////кнопка 2//////
        Button btnConferenceTable = UtilsUI.createButton("Conference Table", 185, 200);
        btnConferenceTable.setOnAction(event -> {
            isItAUserTable = false;
            isItAConferenceTable = true;
            isItAConferenceUserTable = false;

            createDialogMenu(stage);
        });

        //////кнопка 3//////
        Button btnConferenceUserTable = UtilsUI.createButton("Conference_User Table", 350, 200);
        btnConferenceUserTable.setOnAction(event -> {
            isItAUserTable = false;
            isItAConferenceTable = false;
            isItAConferenceUserTable = true;

            createDialogMenu(stage);
        });

        //////добавление в root//////
        root.getChildren().addAll(btnUserTable, btnConferenceTable, btnConferenceUserTable);
    }

    private void createDialogMenu(Stage generalStage) {
        //Создаем маленькое окошко
        AnchorPane root = createSmallStage(generalStage, "TableMenu");

        //Добаляем кнопки
        menuWithTableAddButtons(root);

        //Добавляем поля для ввода информации
        menuWithTableAddInfoPickers(root);

        //Добаляем саму таблицу со значениями
        menuWithTableAddTables(root);
    }

    //////Штуки для диалогового окна с таблицей//////
    private void menuWithTableAddButtons(AnchorPane root) {

        Button btnUpdate = UtilsUI.createButton("Update", 240, 10);

        Button btnCreate = UtilsUI.createButton("Create", 240, 160);

        Button btnDelete = UtilsUI.createButton("Delete", 240, 110);

        Button btnSave = UtilsUI.createButton("Save Update", 240, 60);

        root.getChildren().addAll(btnCreate, btnUpdate, btnDelete, btnSave);

        //////setOnAction//////

        btnUpdate.setOnAction(event -> {
            try {
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {

                    if (isItAUserTable) {
                        User user = (User) list.get(selectedIndex);

                        userIdToUpdate = user.getId();

                        textFieldUserName.setText(user.getUsername());
                        textFieldUserSurname.setText(user.getSurname());
                        textFieldUserNickname.setText(user.getNickname());
                        if (user.isInConference()) {
                            textFieldUserInConference.setText("1");
                        } else {
                            textFieldUserInConference.setText("0");
                        }

                        list.remove(selectedIndex);
                    }

                    if (isItAConferenceTable) {
                        Conference conference = (Conference) list.get(selectedIndex);

                        conferenceIdToUpdate = conference.getId();

                        textFieldConferenceName.setText(conference.getConferencename());

                        if (conference.isIsavailable()) {
                            textFieldConferenceIsAvailable.setText("1");
                        } else {
                            textFieldConferenceIsAvailable.setText("0");
                        }

                        list.remove(selectedIndex);
                    }

                    if (isItAConferenceUserTable) {
                        System.out.println("Add or delete only!!!");
                    }

                }

            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        });

        btnCreate.setOnAction(event -> {
            if (isItAUserTable) {
                User user = new User();
                user.setUsername(textFieldUserName.getText());
                user.setSurname(textFieldUserSurname.getText());
                user.setNickname(textFieldUserNickname.getText());
                if (textFieldUserInConference.getText() == "0") {
                    user.setInConference(false);
                } else {
                    user.setInConference(true);
                }

                userDAO.insertUser(user);
                list.add(user);
            }

            if (isItAConferenceTable) {
                Conference conference = new Conference();
                conference.setConferencename(textFieldConferenceName.getText());
                if (textFieldConferenceIsAvailable.getText() == "0") {
                    conference.setIsavailable(false);
                } else {
                    conference.setIsavailable(true);
                }


                conferenceDAO.insertConference(conference);
                list.add(conference);
            }

            if (isItAConferenceUserTable) {
                Conference_Users conference_users = new Conference_Users();
                conference_users.setUserid(Long.valueOf(textFieldConfrrenceUserUserId.getText()));
                conference_users.setConferenceid(Long.valueOf(textFieldConfrrenceUserConferenceId.getText()));


                conferenceUsersDAO.addUserToConference(
                        Long.valueOf(textFieldConfrrenceUserUserId.getText()),
                        Long.valueOf(textFieldConfrrenceUserConferenceId.getText()));
                list.add(conference_users);
            }
        });

        btnDelete.setOnAction(event -> {
            try {
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {
                    {

                        if (isItAUserTable) {
                            User user = (User) list.get(selectedIndex);
                            userDAO.deleteUser(user.getId());
                            list.remove(selectedIndex);
                        }

                        if (isItAConferenceTable) {
                            Conference conference = (Conference) list.get(selectedIndex);
                            conferenceDAO.deleteConference(conference.getId());
                            list.remove(selectedIndex);
                        }

                        if (isItAConferenceUserTable) {
                            Conference_Users conference_users = (Conference_Users) list.get(selectedIndex);
                            conferenceUsersDAO.deleteUserFromConference(conference_users.getId());
                            list.remove(selectedIndex);
                        }
                    }
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        });

        btnSave.setOnAction(event -> {
            if (isItAUserTable) {

                if (userIdToUpdate != -1) {
                    User user = userDAO.getUserById(userIdToUpdate);

                    user.setUsername(textFieldUserName.getText());
                    user.setSurname(textFieldUserSurname.getText());
                    user.setNickname(textFieldUserNickname.getText());
                    if (textFieldUserInConference.getText() == "0") {
                        user.setInConference(false);
                    } else {
                        user.setInConference(true);
                    }

                    userDAO.updateUser(user);
                    list.add(user);

                    userIdToUpdate = -1;
                }
            }

            if (isItAConferenceTable) {

                if (conferenceIdToUpdate != -1) {
                    Conference conference = conferenceDAO.geConferenceById(conferenceIdToUpdate);
                    conference.setConferencename(textFieldConferenceName.getText());
                    if(textFieldConferenceIsAvailable.getText()=="1") {
                        conference.setIsavailable(true);
                    }else {
                        conference.setIsavailable(false);
                    }

                    conferenceDAO.updateConference(conference);
                    list.add(conference);

                    conferenceIdToUpdate = -1;
                }

            }

            if (isItAConferenceUserTable) {
                System.out.println("Add or delete only!!!");
            }
        });

    }

    //////Штуки для диалогового окна с таблицей//////
    private void menuWithTableAddInfoPickers(AnchorPane root) {
        if (isItAUserTable) {

            // textFieldUserId = UtilsUI.createTextField(50, 10, 150, 30);
            textFieldUserName = UtilsUI.createTextField(50, 50, 150, 30);
            textFieldUserSurname = UtilsUI.createTextField(50, 90, 150, 30);
            textFieldUserNickname = UtilsUI.createTextField(50, 130, 150, 30);
            textFieldUserInConference = UtilsUI.createTextField(50, 170, 150, 30);

            //textFieldUserId.setPromptText("UserId");
            textFieldUserName.setPromptText("UserName");
            textFieldUserSurname.setPromptText("UserSurname");
            textFieldUserNickname.setPromptText("UserNickname");
            textFieldUserInConference.setPromptText("UserInConference(0 or 1)");

            // textFieldConferenceId = null;
            textFieldConferenceName = null;
            textFieldConferenceIsAvailable = null;

            //textFieldConfrrenceUserId = null;
            textFieldConfrrenceUserUserId = null;
            textFieldConfrrenceUserConferenceId = null;
        }

        if (isItAConferenceTable) {
            // textFieldUserId = null;
            textFieldUserName = null;
            textFieldUserSurname = null;
            textFieldUserNickname = null;
            textFieldUserInConference = null;

            // textFieldConferenceId = UtilsUI.createTextField(50, 10, 150, 30);
            textFieldConferenceName = UtilsUI.createTextField(50, 50, 150, 30);
            textFieldConferenceIsAvailable = UtilsUI.createTextField(50, 90, 150, 30);

            //textFieldConferenceId.setPromptText("ConferenceId");
            textFieldConferenceName.setPromptText("ConferenceName");
            textFieldConferenceIsAvailable.setPromptText("ConferenceIsAvailable(0 or 1)");

            //textFieldConfrrenceUserId = null;
            textFieldConfrrenceUserUserId = null;
            textFieldConfrrenceUserConferenceId = null;
        }

        if (isItAConferenceUserTable) {
            //textFieldUserId = null;
            textFieldUserName = null;
            textFieldUserSurname = null;
            textFieldUserNickname = null;
            textFieldUserInConference = null;

            // textFieldConferenceId = null;
            textFieldConferenceName = null;
            textFieldConferenceIsAvailable = null;

            // textFieldConfrrenceUserId = UtilsUI.createTextField(50, 10, 150, 30);
            textFieldConfrrenceUserUserId = UtilsUI.createTextField(50, 50, 150, 30);
            textFieldConfrrenceUserConferenceId = UtilsUI.createTextField(50, 90, 150, 30);

            //textFieldConfrrenceUserId.setPromptText("Id");
            textFieldConfrrenceUserUserId.setPromptText("ConferenceId");
            textFieldConfrrenceUserConferenceId.setPromptText("UserId");
        }

        if (textFieldUserName != null) {
            root.getChildren().addAll(
                    textFieldUserName,
                    textFieldUserSurname,
                    textFieldUserNickname,
                    textFieldUserInConference);
        }

        if (textFieldConferenceName != null) {
            root.getChildren().addAll(
                    textFieldConferenceName,
                    textFieldConferenceIsAvailable);
        }

        if (textFieldConfrrenceUserUserId != null) {
            root.getChildren().addAll(
                    textFieldConfrrenceUserUserId,
                    textFieldConfrrenceUserConferenceId
            );
        }
    }

    //////Штуки для диалогового окна с таблицей//////
    private void menuWithTableAddTables(AnchorPane root) {
        table = new TableView<>();
        table.setTranslateX(10);
        table.setPrefWidth(390);
        table.setTranslateY(250);
        list.clear();
        table.setItems(readAllForDB());

        if (isItAUserTable) {
            createTableColumn(table, "id");
            createTableColumn(table, "username");
            createTableColumn(table, "surname");
            createTableColumn(table, "nickname");
            createTableColumn(table, "inConference");
        }

        if (isItAConferenceTable) {
            createTableColumn(table, "id");
            createTableColumn(table, "conferencename");
            createTableColumn(table, "isavailable");
        }

        if (isItAConferenceUserTable) {
            createTableColumn(table, "id");
            createTableColumn(table, "userid");
            createTableColumn(table, "conferenceid");
        }


        root.getChildren().addAll(table);
    }

    //////Вспомогательный для menuWithTableAddTables()//////
    private TableColumn<Parent, String> createTableColumn(
            TableView<Parent> table, String name) {

        TableColumn<Parent, String> tmp
                = new TableColumn<>(name);

        tmp.setCellValueFactory(new PropertyValueFactory<>(name));
        tmp.setPrefWidth(80);

        tmp.setStyle("-fx-alignment: CENTER;"
        );

        table.getColumns().addAll(tmp);

        return tmp;
    }

    //////Вспомогательный для menuWithTableAddTables()//////
    private static ObservableList<Parent> readAllForDB() {

        if (isItAUserTable) {
            List<User> users = userDAO.getAllUsers();
            list.addAll(users);
        }

        if (isItAConferenceTable) {
            List<Conference> conferences = conferenceDAO.getAllConferences();
            list.addAll(conferences);
        }

        if (isItAConferenceUserTable) {
            List<Conference_Users> conferenceUsers = conferenceUsersDAO.getAll();
            list.addAll(conferenceUsers);
        }

        return list;
    }

    //////Диалоговое окно//////
    private static AnchorPane createSmallStage(Stage owner, String stageName) {
        Stage createStage = new Stage();
        createStage.setTitle(stageName);
        createStage.initModality(Modality.WINDOW_MODAL);
        createStage.initOwner(owner);

        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 410, 550);
        createStage.setScene(scene);
        createStage.show();

        return root;
    }


}
