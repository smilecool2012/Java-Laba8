package com.company;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

public class TestReadWrite_2 {

    public static void main(String[] args) {

        String pArray[] = {"2017", "Java SE 9"};
        File fbyte = new File("c:\\byte.data");
        File fsymb = new File("c:\\java2.txt");

        FileOutputStream fos = null;
        FileWriter fw = null;

        try {
            fos = new FileOutputStream(fbyte);
            fw = new FileWriter(fsymb);
            for (String a : pArray) {
                fos.write(a.getBytes());
                fw.write(a);
            }
        } catch (IOException e) {
            System.err.println("помилка запису: " + e);
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
                // нижче некоректно!
                // кожному close() потрібен свій try-catch
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.err.println("помилка закриття потоку: " + e);
            }
        }

    }
}
