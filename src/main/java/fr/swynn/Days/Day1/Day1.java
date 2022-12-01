package fr.swynn.Days.Day1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import fr.swynn.Days.IDay;

public class Day1 implements IDay {

    private ArrayList<String> getInputs() {
        ArrayList<String> inputs = new ArrayList<String>();
        Scanner scanner = new Scanner(getClass().getResourceAsStream("./data.txt"));
        while (scanner.hasNextLine()) {
            inputs.add(scanner.nextLine());
        }
        scanner.close();
        return inputs;
    }

    private List<Integer> getSumCalories() throws NumberFormatException {
        List<Integer> calories = new ArrayList<Integer>();
        int sum = 0;
        for (String input : getInputs()) {
            if (input != null && !input.isEmpty()) {
                sum += Integer.parseInt(input);
            } else {
                calories.add(sum);
                sum = 0;
            }
        }

        return calories;
    }
    
    @Override
    public void run() {
        try {
            List<Integer> calories = getSumCalories();
            calories.sort(Comparator.reverseOrder());
            
            System.out.println("Day 1 - Part 1: " + calories.get(0));
            System.out.println("Day 1 - Part 2: " + (calories.get(0) + calories.get(1) + calories.get(2)));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
