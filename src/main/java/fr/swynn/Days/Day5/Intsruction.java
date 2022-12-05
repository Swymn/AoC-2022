package fr.swynn.Days.Day5;

public class Intsruction {

    private final int value;
    private final int columnFrom;
    private final int columnTo;

    public Intsruction(String instruction) {
        String[] values = instruction.split(" ");

        if (values.length != 6)
            throw new IllegalArgumentException("Instruction must have 6 values.");

        this.value = Integer.parseInt(values[1]);
        this.columnFrom = Integer.parseInt(values[3]) - 1;
        this.columnTo = Integer.parseInt(values[5]) - 1;
    }

    public int getValue() {
        return value;
    }

    public int getColumnFrom() {
        return columnFrom;
    }

    public int getColumnTo() {
        return columnTo;
    }
}
