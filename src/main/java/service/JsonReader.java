package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class JsonReader {
    public static String readJsonFile(String filePath) {
        try (FileReader fileReader = new FileReader(filePath);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append("\n"); // Append newline character
            }

            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
