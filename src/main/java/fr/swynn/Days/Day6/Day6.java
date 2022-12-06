package fr.swynn.Days.Day6;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;

import fr.swynn.Days.IDay;
import fr.swynn.utils.FileHandler;

public class Day6 implements IDay {

    private final ArrayList<String> inputDatas;
        
    public Day6() throws RuntimeException {
        try {
            this.inputDatas = new FileHandler("Days/Day6/data.txt").getFileContent();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException();
        } 
    }

    private boolean hasAllUniqueChars(String word) {
        HashSet<Character> alphaSet = new HashSet<>();

        for (char letter : word.toCharArray()) {
            if (!(alphaSet.add(letter))) {
                return false;
            }
        }

        return true;
    }

    private int getLastMakerIndex(int size) {
        String line = this.inputDatas.get(0);
        for (int i = 0; i < line.length() - (size+1); i++) {
            String marker = line.substring(i, i+size);
            if (hasAllUniqueChars(marker)) {
                return i+size;
            }
        }
        return -1;
    }

    @Override
    public void run() {
        System.out.println("Day 6 - Part one: " + getLastMakerIndex(4) + "");
        System.out.println("Day 6 - Part one: " + getLastMakerIndex(14) + "");
    }    
}
