package reader;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class ReaderFromFile {
    private String fileName;
    private String[] stringsArray;

    public ReaderFromFile(String fileName) {
        this.fileName = fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;

        this.readArrayFromFile();
    }

    public String getFileName() {
        return fileName;
    }

    public String[] getStringsArray() {
        return stringsArray;
    }

    public String[] readArrayFromFile(){
        stringsArray = new String[2];
        try (BufferedReader reader = Files.newBufferedReader(Path.of(fileName))){
            int i = 0;
            while (reader.ready()){
                if(i < 2) {
                    stringsArray[i] = reader.readLine();
                    i++;
                }
                else {
                    break;
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        return stringsArray;
    }
}