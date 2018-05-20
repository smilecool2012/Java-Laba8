package com.ntu.MyLab10;

import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UtilsUI {
    //Методы создания елементов интерфейса

    public static CheckBox createCheckBox(String name, double x, double y){
        CheckBox checkBox = new CheckBox(name);
        checkBox.setTranslateX(x);
        checkBox.setTranslateY(y);
        return checkBox;
    }

    public static Label createLabel(AnchorPane root, String text, double x, double y, double width, double height ){
        Label label = new Label();
        label.setPrefSize(width,height);
        AnchorPane.setLeftAnchor(label,x);
        AnchorPane.setTopAnchor(label,y);
        label.setText(text);

        root.getChildren().addAll(label);

        return label;
    }

    public static Button createButton(String name){
        Button button = new Button();
        button.setText(name);
        button.setPrefSize(120,50);

        return button;
    }

    public static Button createButton(String name,double x,double y){
        Button button = new Button();
        button.setText(name);
        button.setPrefSize(120,30);
        button.setTranslateX(x);
        button.setTranslateY(y);

        return button;
    }

    public static TextField createTextField(double x, double y, double width, double height ){
        TextField textField = new TextField();
        textField.setTranslateX(x);
        textField.setTranslateY(y);
        textField.prefHeight(height);
        textField.prefWidth(width);

        return textField;
    }

    public static SplitMenuButton createSplitMenuButton(String[] menuitems, String name, double x, double y){

        MenuItem[] menuItem= new MenuItem[menuitems.length];

        for(int i=0;i<menuitems.length;i++){
            menuItem[i] = new MenuItem(menuitems[i].toString());
        }

        SplitMenuButton splitMenuButton = new SplitMenuButton(menuItem);

        for(int i=0;i<menuitems.length;i++){

            final int j = i;
            menuItem[i].setOnAction(event -> {
                splitMenuButton.setText(menuItem[j].getText());
            });
        }

        splitMenuButton.setText(name);
        splitMenuButton.setTranslateX(x);
        splitMenuButton.setTranslateY(y);
        splitMenuButton.setWrapText(true);

        return splitMenuButton;
    }

    public static DatePicker createDatePicker(double x,double y) {
        DatePicker datePicker = new DatePicker();
        datePicker.setTranslateX(x);
        datePicker.setTranslateY(y);

        StringConverter<LocalDate> converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern("yyyy.MM.dd");

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);
        datePicker.setPromptText("yyyy.mm.dd");

        return datePicker;
    }
}
