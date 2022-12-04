package fr.swynn.Days.Day4;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.swynn.Days.IDay;
import fr.swynn.utils.FileHandler;

public class Day4 implements IDay {

    private final ArrayList<String> inputDatas;

    public Day4() throws RuntimeException {
        try {
            this.inputDatas = new FileHandler("Days/Day4/data.txt").getFileContent();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException();
        }
    }

    /**
     * This method is used to get the pairs of the current line.
     * 
     * @param {String} line - The line to parse
     * 
     * @return {List<String>} - The list of pairs
     */
    private List<String> getPairs(String line) {
        String[] pairs = line.split(",");
        List<String> digits = new ArrayList<String>();
        for (String pair : pairs) {
            String[] pairDigits = pair.split("-");
            digits.addAll(Arrays.asList(pairDigits));
        }

        return digits;
    }

    /**
     * This method is used to convert all the string pairs to int pairs.
     * 
     * @param {String} digits - The list of pairs to convert
     * 
     * @return {List<Integer>} - The list of converted pairs
     */
    private List<Integer> convertToInteger(List<String> digits) {
        List<Integer> integers = new ArrayList<Integer>();
        for (String digit : digits) {
            integers.add(Integer.parseInt(digit));
        }
        return integers;
    }

    /**
     * This method is used to check if one of the pair contains the other.
     * 
     * @param {String} line - The line to check
     * 
     * @return {boolean} - True if one of the pair contains the other 
     */
    private boolean isContainsAllDigits(String line) {
        List<Integer> digits = convertToInteger(getPairs(line));
        if (digits.size() != 4) {
            return false;
        }

        return 
            (digits.get(0) <= digits.get(2) && digits.get(1) >= digits.get(3))
            || (digits.get(1) <= digits.get(3) && digits.get(0) >= digits.get(2));
    }

    /**
     * This method is used to check if the pairs are overlapping.
     * 
     * @param {String} line - The line to check
     * 
     * @return {boolean} - True if the pairs are overlapping
     */
    private boolean isOverlapping(String line) {
        List<Integer> digits = convertToInteger(getPairs(line));
        if (digits.size() != 4) {
            return false;
        }

        for (int i = digits.get(0); i <= digits.get(1); i++) {
            for (int j = digits.get(2); j <= digits.get(3); j++) {
                if (i == j) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * This method is used to calculate.
     * 
     * @return {int[]} - The result
     */
    private int[] getResult() {
        final int[] res = {0, 0};
        for (String line : this.inputDatas) {
            if (isContainsAllDigits(line)) {
                res[0]++;
            }

            if (isOverlapping(line)) {
                res[1]++;
            }
        }
        return res;
    }

    @Override
    public void run() {
        final int[] result = getResult();
        System.out.println("Total contains: " + result[0]);
        System.out.println("Total overlappng: " + result[1]);
    }    
}
