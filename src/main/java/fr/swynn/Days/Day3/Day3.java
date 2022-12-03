package fr.swynn.Days.Day3;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import fr.swynn.Days.IDay;
import fr.swynn.utils.FileHandler;

public class Day3 implements IDay {

    private final ArrayList<String> inputDatas;

    public Day3() throws RuntimeException {
        try {
            this.inputDatas = new FileHandler("Days/Day3/data.txt").getFileContent();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException();
        }
    }

    /**
     * This method will cut the string in two parts.
     * 
     * @param {String} str - The string to cut
     * 
     * @return {String[]} The two parts of the string
     */
    private String[] cutString(String str) {
        final int MID = str.length() / 2;
        String[] parts = { str.substring(0, MID), str.substring(MID) };
        return parts;
    }

    /**
     * This method will find the letter common to both strings.
     * 
     * @param {String} str - The string to find the common letter
     * 
     * @return {char} - The common char
     */
    private char findLetterInBothString(String str) {
        String[] parts = cutString(str);
        char result = ' ';
        for (int i = 0; i < parts[0].length(); i++) {
            for (int j = 0; j < parts[1].length(); j++) {
                if (parts[0].charAt(i) == parts[1].charAt(j)) {
                    result = parts[0].charAt(i);
                }
            }
        }
        return result == ' ' ? null : result;
    }

    /**
     * This method will find common letter in grouups of string.
     * 
     * @param {String[]} str - The array of string to find the common letter
     * 
     * @return {char} - The common char
     */
    private char findLetterInGroups(String[] str) {

        char result = ' ';

        for (int i = 0; i < str[0].length(); i++) {
            for (int j = 0; j < str[1].length(); j++) {
                if (str[0].charAt(i) == str[1].charAt(j)) {
                    for (int k = 0; k < str[2].length(); k++) {
                        if (str[0].charAt(i) == str[2].charAt(k) && str[1].charAt(j) == str[2].charAt(k)) {
                            result = str[0].charAt(i);
                        }
                    }
                }
            }
        }

        return result;
    } 

    /**
     * This method will return the score of the letter.
     * 
     * @param {char} letter - The letter to find the score
     * 
     * @return {int} - The score of the letter
     */
    private int valueOfLetter(char letter) {
        if (Character.isUpperCase(letter))
            // Because the value of A is 65, and we need to add 26 to get the valid value.
            return letter - 38;
        return letter - 96;
    }

    /**
     * This method will return the score of the string.
     * 
     * @return {int} - The score of the string
     */
    private int sumOfLetters() {
        int sum = 0;
        for (int i = 0; i < inputDatas.size(); i++) {
            sum += valueOfLetter(findLetterInBothString(inputDatas.get(i)));
        }
        return sum;
    }

    /**
     * This method will return the score of the groups of string.
     * 
     * @return {int} - The score of the string
     */
    private int sumOfGroups() {
        int sum = 0;
        for (int i = 0; i < inputDatas.size(); i += 3) {
            String[] str = { inputDatas.get(i), inputDatas.get(i + 1), inputDatas.get(i + 2) };
            sum += valueOfLetter(findLetterInGroups(str));
        }
        return sum;
    }

    @Override
    public void run() {
        System.out.println("Day 3 - Part 1 : " + sumOfLetters()); 
        System.out.println("Day 3 - Part 2 : " + sumOfGroups());       
    }
}
