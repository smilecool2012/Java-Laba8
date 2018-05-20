package com.ntu.MyLab;

import com.ntu.MyLab.DAO.FlightDAO;
import com.ntu.MyLab.DAO.FlightDAOimpl;
import com.ntu.MyLab.Entity.AllEntitysParent;
import com.ntu.MyLab.Entity.Flight;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class main extends Application{
    private static Flight flightToUpdate;
    private TextField textFieldFlightFlightnumber;
    private TextField textFieldFlightOtpravlenie;
    private TextField textFieldFlightNaznachenie;
    private TextField textFieldFlightKolichestvoMest;
    private TextField textFieldFlightKolichestvoProdanuh;
    private TextField textFieldFlightStoimost;
    //Сама таблица
    private static TableView<AllEntitysParent> table;
    //Лист с данными из бд для таблицы
    private static ObservableList<AllEntitysParent> list = FXCollections.observableArrayList();
    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage) throws Exception {
        createStartMenu(stage);
    }

    private void createStartMenu(Stage stage) {

        //////сцена//////
        AnchorPane root = new AnchorPane();
        stage.setTitle("Lab 10");
        Scene scene = new Scene(root, 500, 250);
        stage.setScene(scene);
        stage.show();

        Label labelHello = UtilsUI.createLabel(root,"WELCOME!",30,30,550,100);
        labelHello.setFont(new Font("Arial", 70));

        //////кнопка 1//////
        Button btnSubscribersTable = UtilsUI.createButton("Subscribers Table", 30, 200);
        btnSubscribersTable.setOnAction(event -> {
            createDialogMenu(stage);
        });

        //////кнопка 2//////
        Button btnConversationTable = UtilsUI.createButton("Conversation Table", 330, 200);
        btnConversationTable.setOnAction(event -> {
            createDialogMenu(stage);
        });

        //////добавление в root//////
        root.getChildren().addAll(btnSubscribersTable, btnConversationTable);
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

        FlightDAO flightDAO = new FlightDAOimpl();
        String regex = "[0-9]+";

        btnUpdate.setOnAction(event -> {

            try {
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {
                    //if (isItASubscriberTable) {
                    flightToUpdate = (Flight) list.get(selectedIndex);
                    textFieldFlightFlightnumber.setText(String.valueOf(flightToUpdate.getFlightnumber()));
                    textFieldFlightOtpravlenie.setText(String.valueOf(flightToUpdate.getOtpravlenie()));
                    textFieldFlightNaznachenie.setText(String.valueOf(flightToUpdate.getNaznachenie()));
                    textFieldFlightKolichestvoMest.setText(String.valueOf(flightToUpdate.getKolichestvomest()));
                    textFieldFlightKolichestvoProdanuh.setText(String.valueOf(flightToUpdate.getKolichestvoprodanuh()));
                    textFieldFlightStoimost.setText(String.valueOf(flightToUpdate.getStoimost()));
                        }

                    //} else {

//                        conversationToUpdate = (Conversation) list.get(selectedIndex);
//
//                        textFieldConversationSubWhoCall.setText(conversationToUpdate.getSubWhoCallId());
//                        textFielConversatinCalledSub.setText(conversationToUpdate.getCalledSubId());

                    //}

            } catch (ArrayIndexOutOfBoundsException e) {
            }


        });

        btnCreate.setOnAction(event -> {
           // if (isItASubscriberTable) {

            Flight flight = new
                    Flight();flight.setFlightnumber(Long.valueOf(textFieldFlightFlightnumber.getText()));
            flight.setOtpravlenie((textFieldFlightOtpravlenie.getText()));
            flight.setNaznachenie((textFieldFlightNaznachenie.getText()));
            flight.setKolichestvomest(Long.valueOf(textFieldFlightKolichestvoMest.getText()));
            flight.setKolichestvoprodanuh(Long.valueOf(textFieldFlightKolichestvoProdanuh.getText()));
            flight.setStoimost(Long.valueOf(textFieldFlightStoimost.getText()));

            flightDAO.insertFlight(flight);
            list.add(flight);
            /*} else {

                String numberSubWhoCall = textFieldConversationSubWhoCall.getText();
                String numberCalledSub = textFielConversatinCalledSub.getText();

                //validation and add
                if (numberSubWhoCall.length() == 7 & numberCalledSub.length() == 7) {
                    if (numberSubWhoCall.matches(regex) & numberCalledSub.matches(regex)) {

                        Conversation conversation = new Conversation();

                        conversation.setSubWhoCallId(numberSubWhoCall);
                        conversation.setCalledSubId(numberCalledSub);

                        //Проверяем существуют ли такие пользователи
                        List<Subscriber> listWirhAllSubscribers = subscriberDAO.getAllSubscriber();
                        List<String> numbers = new ArrayList<😠);
                        for (Subscriber s:listWirhAllSubscribers) {
                            numbers.add(s.getNumber());
                        }

                        if(numbers.contains(numberSubWhoCall)&numbers.contains(numberCalledSub)) {

                            conversationDAO.insertConversation(conversation);
                            list.add(conversation);

                        }else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Ahtung");
                            alert.setHeaderText(null);
                            alert.setContentText("Some testfields are not correct" +
                                    "\nTheu are real important");

                            alert.showAndWait();
                        }

                    } else {
                        System.out.println("в номере должны быть только цыфры");
                    }
                } else {
                    System.out.println("неверная длина номера!");
                }

            }
            */
        });

        btnDelete.setOnAction(event -> {
            try {
                int selectedIndex = table.getSelectionModel().getSelectedIndex();
                if (selectedIndex != -1) {
                    //if (isItASubscriberTable) {
                        Flight subscriber = (Flight) list.get(selectedIndex);
                        flightDAO.deleteFlight(subscriber.getId());

                        list.remove(selectedIndex);
                    //} else {
                        //Conversation conversation = (Conversation) list.get(selectedIndex);
                        //conversationDAO.deleteConversation(conversation.getID());

                        //list.remove(selectedIndex);
                    //}
                }
            } catch (ArrayIndexOutOfBoundsException e) {
            }
        });

        btnSave.setOnAction(event -> {

            //if (isItASubscriberTable) {

                //DODELAT
//                        //String oldSubNumber = subscriberToUpdate.getNumber();
//                        list.removeAll(subscriberToUpdate);
//
//                        subscriberToUpdate.setNumber(number);
//                        if (textFieldSubAvailable.getText().equals("0")) {
//                            subscriberToUpdate.setAvailable(false);
//                        } else {
//                            subscriberToUpdate.setAvailable(true);
//                        }
//
//                        System.out.println(subscriberToUpdate.getNumber());
//                        subscriberDAO.updateSubscriber(subscriberToUpdate, oldSubNumber);
//                        list.addAll(subscriberToUpdate);


            //} else {

//                String numberSubWhoCall = textFieldConversationSubWhoCall.getText();
//                String numberCalledSub = textFielConversatinCalledSub.getText();
//
//                //validation and add
//                if (numberSubWhoCall.length() == 7 & numberCalledSub.length() == 7) {
//                    if (numberSubWhoCall.matches(regex) & numberCalledSub.matches(regex)) {
//
//                        list.remove(conversationToUpdate);
//
//                        String id1 = conversationToUpdate.getSubWhoCallId();
//                        String id2 = conversationToUpdate.getCalledSubId();
//
//                        conversationToUpdate.setSubWhoCallId(numberSubWhoCall);
//                        conversationToUpdate.setCalledSubId(numberCalledSub);
//
//                        conversationDAO.updateConversation(conversationToUpdate,id1,id2);
//                        list.add(conversationToUpdate);
//
//                    } else {
//                        System.out.println("в номере должны быть только цыфры");
//                    }
//                } else {
//                    System.out.println("неверная длина номера!");
//                }

            //}
        });
    }

    //////Штуки для диалогового окна с таблицей//////
    private void menuWithTableAddInfoPickers(AnchorPane root) {
        //if (isItASubscriberTable) {
            Label labelSubNumber = UtilsUI.createLabel(root, "Subscriber number", 50, 10, 150, 30);
            Label labelSubAvailable = UtilsUI.createLabel(root, "Subscriber Available(0 or 1)", 50, 90, 150, 30);

            textFieldSubName = UtilsUI.createTextField(50, 50, 150, 30);
            textFieldSubAvailable = UtilsUI.createTextField(50, 130, 150, 30);

            root.getChildren().addAll(textFieldSubName, textFieldSubAvailable);

//        } else {
//            Label conversationSubWhoCall = UtilsUI.createLabel(root, "Sub who call", 50, 10, 150, 30);
//            Label conversationCalledSub = UtilsUI.createLabel(root, "Called Sub", 50, 90, 150, 30);
//
//            textFieldConversationSubWhoCall = UtilsUI.createTextField(50, 50, 150, 30);
//            textFielConversatinCalledSub = UtilsUI.createTextField(50, 130, 150, 30);
//
//            root.getChildren().addAll(textFieldConversationSubWhoCall, textFielConversatinCalledSub);
//        }
    }

    //////Штуки для диалогового окна с таблицей//////
    private void menuWithTableAddTables(AnchorPane root) {
        table = new TableView<😠);
        table.setTranslateX(10);
        table.setTranslateY(250);
        list.clear();
        table.setItems(readAllForDB());

        if (isItASubscriberTable) {
            createTableColumn(table, "Number");
            createTableColumn(table, "Available");
        } else {
            createTableColumn(table, "ID");
            createTableColumn(table, "SubWhoCallId");
            createTableColumn(table, "CalledSubId");
        }

        root.getChildren().addAll(table);
    }

    //////Вспомогательный для menuWithTableAddTables()//////
    private TableColumn<AllEntitysParent, String> createTableColumn(
            TableView<AllEntitysParent> table, String name) {

        TableColumn<AllEntitysParent, String> tmp
                = new TableColumn<😠name);

        tmp.setCellValueFactory(new PropertyValueFactory<😠name));
        tmp.setPrefWidth(190);

        tmp.setStyle("-fx-alignment: CENTER;"
        );

        table.getColumns().addAll(tmp);

        return tmp;
    }

    //////Вспомогательный для menuWithTableAddTables()//////
    private static ObservableList<AllEntitysParent> readAllForDB() {

        if (isItASubscriberTable) {
            SubscriberDAO subscriberDAO = new SubscriberDAOImpl();
            List<Subscriber> subs = subscriberDAO.getAllSubscriber();
            list.addAll(subs);
        } else {
            ConversationDAO conversationDAO = new ConversationDAOImpl();

            List<Conversation> conversations = conversationDAO.getAllConversation();
            list.addAll(conversations);
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
