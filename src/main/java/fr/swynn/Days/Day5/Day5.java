package fr.swynn.Days.Day5;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
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

    /**
     * This method is used to get the column count and the row count.
     * 
     * @return {int[]} The first value is the column count, the second value is the row count.
     */
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

    /**
     * This method is used to generate the table from the data.txt file.
     * 
     * @return {void}
     */
    private void generateTable() {
        int valueIndex = 1;
        for (int i = 0; i < this.columonCount; i++) {
            Stack<String> stack = new Stack<String>();
            for (int j = this.rowCount-1; j >= 0; j--) {
                String value = this.inputDatas.get(j).toCharArray()[valueIndex] + "";
                if (value.trim().isEmpty()) continue;
                stack.add(value);
            }
            this.arrayDatas.add(stack);
            valueIndex += 4;
        }
    }

    /**
     * This method is used to get the top item of the table.
     * 
     * @return {String} The top item of the table.
     */
    private String getTopItem() {
        String res = "";
        for (int i = 0; i < this.arrayDatas.size(); i++) {
            Stack<String> stack = this.arrayDatas.get(i);
            res += stack.pop();
        }
        return res;
    }

    /**
     * This method is used to show the stacks (debug).
     * 
     * @return {void}
     */
    private void showstacks() {
        for (int i = 0; i < this.arrayDatas.size(); i++) {
            Stack<String> stack = this.arrayDatas.get(i);
            for (String string : stack) {
                System.out.print(string + " ");
            }
            System.out.println("");
        }
    }

    /**
     * This method is used to run the game.
     * 
     * @param {boolean} keepOrder - If true, the order of the item will be kept.
     * 
     * @return {void}
     */
    private void game(boolean keepOrder) {
        for (int i = this.rowCount+2; i < this.inputDatas.size(); i++) {
            Intsruction instruction = new Intsruction(this.inputDatas.get(i));

            Stack<String> stackFrom = this.arrayDatas.get(instruction.getColumnFrom());
            Stack<String> stackTo = this.arrayDatas.get(instruction.getColumnTo());

            if (!keepOrder) {
                for (int j = 0; j < instruction.getValue(); j++) {
                    String value = stackFrom.pop();
                    if (value == null) continue;
                    stackTo.add(value);
                }
                continue;
            } 

            List<String> list = new ArrayList<String>();

            for (int j = 0; j < instruction.getValue(); j++) {
                String value = stackFrom.pop();
                if (value == null) continue;
                list.add(value);
            }

            for (int j = list.size()-1; j >= 0; j--) {
                stackTo.add(list.get(j));
            }
        }
    }

    @Override
    public void run() {
        game(true);
        System.out.println("Top item: " + getTopItem());
    }
}
