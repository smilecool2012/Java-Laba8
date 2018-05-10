package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class TestFRead_1 {

    public static void main(String[] args) {
        String fileName = "c:\\byte.data";
        File f = new File(fileName);
        int b, count = 0;
        FileReader is = null;
        try{
            is = new FileReader(f);
            while((b = is.read())!= -1){
                System.out.println((byte) b);
                count++;
            }
            System.out.println("Bytes: " + count);
        }
        catch(IOException e){
            System.err.println("Found: " + e);
        }
        finally {
            try {
                if(is != null)
                    is.close();
            }
            catch(IOException e){
                System.out.println("Eror 2: " + e);
            }
        }
    }
}