package fr.swynn.Days.Day1;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import fr.swynn.Days.IDay;
import fr.swynn.utils.FileHandler;

public class Day1 implements IDay {

    private List<Integer> getSumCalories() throws NumberFormatException {
        try {
            ArrayList<String> inputs = new FileHandler("Days/Day1/data.txt").getFileContent();
            List<Integer> calories = new ArrayList<Integer>();
            int sum = 0;
            for (String input : inputs) {
                if (input != null && !input.isEmpty()) {
                    sum += Integer.parseInt(input);
                } else {
                    calories.add(sum);
                    sum = 0;
                }
            }

            return calories;
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    
    @Override
    public void run() {
        try {
            List<Integer> calories = getSumCalories();
            if (calories != null) {
                calories.sort(Comparator.reverseOrder());
            
                System.out.println("Day 1 - Part 1: " + calories.get(0));
                System.out.println("Day 1 - Part 2: " + (calories.get(0) + calories.get(1) + calories.get(2)));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
