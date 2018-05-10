package com.company;

import java.lang.String;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;

public class Main  {

    public static void main(String[] args) throws ParseException, IOException {
        Car car;
        ArrayList<Machine> machines = new ArrayList<>();
        machines.add(new Machine("Shkoda", "Miki", "Gavela", "AI3333", 613, true ));
        machines.add(new Machine("Audi", "Zago", "Shepeleva", "AB0000", 300, false));
        car = new Car(machines);
        String fileName = "c:\\java2.txt";
        car.saveToFile(fileName);
        ArrayList<Machine> carsFromFile = new ArrayList<>();
        Car myCarsFromFile = new Car(carsFromFile);
        myCarsFromFile.loadFromFile(fileName);
        System.out.println("Список машин (з файлу):");
        myCarsFromFile.printList();

    }
}


