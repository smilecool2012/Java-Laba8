package com.company;

import java.io.*;
import java.util.ArrayList;

public class Car {
    private ArrayList<Machine> machines;

    public Car(ArrayList<Machine> list) { machines = list;
    }
    public void printList(){
        for(Machine car : machines){
            System.out.println(car);
        }
    }

    public void printList(boolean isAvailable) {
        for (Machine car : machines) {
            if (car.isOnParking() == isAvailable)
                System.out.println(car);
        }
    }

    public String findCarBysurname(String surname) {
        String result = "";
        for (Machine car : machines){
            if (car.getSurname().contains(surname))
                result += car.toString();
        }           return result.isEmpty()?"нічого не знайдено":result;
    }
    public void saveToFile(String fileName){
        File file = new File(fileName);
        try {
            FileOutputStream fileOut =  new FileOutputStream(file);
            ObjectOutputStream outStream = new ObjectOutputStream(fileOut);
            for(Machine car: machines){
                outStream.writeObject(car);
            }
            outStream.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Файл для запису даних не створено - " +   e.getMessage() );
        }
        catch (IOException e) {
            System.out.println("Помилка запису даних - " + e.getMessage() );
        }
    }

    public void loadFromFile(String fileName){
        File file = new File(fileName);
        int i = 0;
        Machine car=null;
        ObjectInputStream inStream = null;
        try {
            FileInputStream fileIn =  new FileInputStream(file);
            inStream = new ObjectInputStream(fileIn);
            if(inStream != null){
                while(true){
                    car = (Machine) inStream.readObject();
                    if(car==null)break;
                    machines.add(car);
                }
                inStream.close();
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Не знайдено файл з даними - " + e.getMessage() );
        }
        catch (ClassNotFoundException e) {
            System.out.println("Помилка в структурі даних - " + e.getMessage() );
        }
        catch (IOException e) {
            System.out.println("Помилка читання даних - " + e.getMessage() );
        }
    }
}
