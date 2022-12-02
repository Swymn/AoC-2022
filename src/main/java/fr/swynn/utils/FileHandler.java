package fr.swynn.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    
    private final String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFilePath() {
        return getClass().getResource(fileName).getPath();
    }

    public ArrayList<String> getFileContent() throws FileNotFoundException {
        ArrayList<String> inputs = new ArrayList<String>();
        File file = new File("src/main/java/fr/swynn/" + fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            inputs.add(scanner.nextLine());
        }
        scanner.close();
        return inputs;
    }
}
