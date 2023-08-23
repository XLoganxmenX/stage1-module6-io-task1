package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String text = "";

        try (FileInputStream fileInputStream = new FileInputStream(file);){
            int ch;
            while ((ch=fileInputStream.read()) != -1) {
               text += (char) ch;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] stringList = text.split("[\\r\\n]+");

        String name = extractValueFromString(stringList, "Name:");
        int age = Integer.parseInt(extractValueFromString(stringList, "Age:"));
        String email = extractValueFromString(stringList, "Email:");
        Long phone = Long.parseLong(extractValueFromString(stringList, "Phone:"));

        return new Profile(name, age, email, phone);
    }

    private String extractValueFromString(String[] stringList, String keyword) {
        for (String s : stringList) {
            if (s.startsWith(keyword)) {
                return s.substring(keyword.length()).trim();
            }
        }
        return "";
    }

}
