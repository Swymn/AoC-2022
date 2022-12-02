package fr.swynn.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileHandler {
    
    private final File file;

    public FileHandler(String fileName) {
        this.file = new File("src/main/java/fr/swynn/" + fileName);
    }

    /**
     * This method is used to get the file.
     * 
     * @return {File} - The file.
     */
    public File getFileName() {
        return file;
    }

    /**
     * This method is used to get the path of the file.
     * 
     * @return {String} - The path of the file.
     */
    public String getFilePath() {
        return file.toPath().toString();
    }

    /**
     * This method is used to get the content of a file.
     * 
     * @returns {ArrayList<String>} - The content of the file.
     * 
     * @throws FileNotFoundException - If the file is not found.
     */
    public ArrayList<String> getFileContent() throws FileNotFoundException {
        ArrayList<String> inputs = new ArrayList<String>();
        Scanner scanner = new Scanner(this.file);
        while (scanner.hasNextLine()) {
            inputs.add(scanner.nextLine());
        }
        scanner.close();
        return inputs;
    }
}
