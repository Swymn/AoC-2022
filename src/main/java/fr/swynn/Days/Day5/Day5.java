package fr.swynn.Days.Day5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Stack;

import fr.swynn.Days.IDay;
import fr.swynn.utils.FileHandler;

public class Day5 implements IDay {

    private ArrayList<String> inputDatas;
    private ArrayList<Stack<String>> arrayDatas;

    private final int columonCount;
    private final int rowCount;

    public Day5() throws RuntimeException {
        try {
            this.inputDatas = new FileHandler("Days/Day5/data.txt").getFileContent();

            final int[] xy = this.getColumn();
            if (xy == null) {
                System.out.println("No table found.");
                throw new RuntimeException();
            }

            this.columonCount = xy[0];
            this.rowCount = xy[1];
            this.arrayDatas = new ArrayList<Stack<String>>();

            generateTable();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            throw new RuntimeException();
        }
    }

    private int[] getColumn() {
        final int[] xy = new int[2];
        for (int i = 0; i < this.inputDatas.size(); i++) {
            String line = this.inputDatas.get(i);
            if (line.matches("[0-9 ]+") && (line.trim().length() > 0)) {
                xy[0] = Integer.parseInt(line.charAt(line.length() - 2) + "");
                xy[1] = i;
                return xy;
            }
        }

        return null;
    }

    private void generateTable() {
        int valueIndex = 1;
        for (int i = 0; i < this.columonCount; i++) {
            Stack<String> stack = new Stack<String>();
            for (int j = this.rowCount-1; j >= 0; j--) {
                String item = this.inputDatas.get(j);
                String value = item.toCharArray()[valueIndex] + "";
                if (value.trim().isEmpty()) continue;
                stack.add(value);
            }
            this.arrayDatas.add(stack);
            valueIndex += 4;
        }
    }

    private String getTopItem() {
        String res = "";
        for (int i = 0; i < this.arrayDatas.size(); i++) {
            Stack<String> stack = this.arrayDatas.get(i);
            res += stack.pop();
        }
        return res;
    }

    private void showstacks() {
        for (int i = 0; i < this.arrayDatas.size(); i++) {
            Stack<String> stack = this.arrayDatas.get(i);
            for (String string : stack) {
                System.out.print(string + " ");
            }
            System.out.println("");
        }
    }

    private void partOne() {
        for (int i = this.rowCount+2; i < this.inputDatas.size(); i++) {
            Intsruction instruction = new Intsruction(this.inputDatas.get(i));

            Stack<String> stackFrom = this.arrayDatas.get(instruction.getColumnFrom());
            Stack<String> stackTo = this.arrayDatas.get(instruction.getColumnTo());

            for (int j = 0; j < instruction.getValue(); j++) {
                String value = stackFrom.pop();
                if (value == null) continue;
                stackTo.add(value);
            }

            /* System.out.println("----------- Part " + i + " -----------");
            showstacks();
            System.out.println("----------- End Part " + i + " -----------"); */
        }
    }

    @Override
    public void run() {
        /* System.out.println("----------- Start -----------");
        showstacks();
        System.out.println("----------- Start -----------"); */

        partOne();

        /* System.out.println("----------- End -----------");
        showstacks();
        System.out.println("----------- End -----------"); */

        System.out.println("Top item: " + getTopItem());
    }
}
