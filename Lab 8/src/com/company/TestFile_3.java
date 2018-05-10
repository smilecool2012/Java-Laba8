package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestFile_3 {
    public static void main(String[] args) {
        // c об'єктом типу File асоціюється файл на диску FileTest2.java
        File fp = new File("FileTest2.java");
        if(fp.exists()) {
            System.out.println(fp.getName() + " існує");
            if(fp.isFile()) { // якщо об'єкт - дисковий файл
                System.out.println("Шлях до файлу:\t" + fp.getPath());
                System.out.println("Абсолютний шлях:\t" + fp.getAbsolutePath());
                System.out.println("Розмір файлу:\t" + fp.length());
                System.out.println("Остання модифікація :\t"+ new Date(fp.lastModified()));
                System.out.println("Файл доступний для читання:\t" + fp.canRead());
                System.out.println("Файл доступний для запису:\t" + fp.canWrite());
                System.out.println("Файл видалений:\t" + fp.delete());
            }
        }
        else
            System.out.println("файл " + fp.getName() + " не існує");
        try {
            if(fp.createNewFile()) {
                System.out.println("Файл " + fp.getName() + " створено");
            }
        }
        catch(IOException e) {
            System.err.println(e);
        }


        // в об'єкт типу File поміщається каталог\директорія
        // повинен бути створений каталог com.ntu з декількома файлами
        File dir = new File("С:"+ File.separator + "Lab 8" + File.separator + "com" + File.separator + "ntu");

        if (dir.exists() && dir.isDirectory()) { /* якщо об'єкт є каталогом і якщо цей каталог існує */
            System.out.println("каталог " + dir.getName() + " існує");
        }
        File[] files = dir.listFiles();
        for(int i = 0; i < files.length; i++) {
            Date date = new Date(files[i].lastModified());
            System.out.print("\n"+files[i].getPath()+" \t| "+files[i].length()+"\t|"+date);
        }
        // метод listRoots() повертає доступні кореневі каталоги
        File root = File.listRoots()[1];
        System.out.printf("\n%s %,d из %,d вільно.", root.getPath(), root.getUsableSpace(),
                root.getTotalSpace());
    }

}
