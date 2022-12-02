package fr.swynn.Days.Day2;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import fr.swynn.Days.IDay;
import fr.swynn.utils.FileHandler;

public class Day2 implements IDay {

    private final int WIN = 6;
    private final int DRAW = 3;
    private final int LOSE = 0;

    private final int ROCK = 1;
    private final int PAPER = 2;
    private final int SCISSORS = 3;

    /**
     * This method is used to get the result of a game.
     * 
     * @param {char} p1 - The first player.
     * @param {char} p2 - The second player.
     * 
     * @returns {int} - The score of the second player.
     */
    private char fight(char p1, char p2) {
        switch (p2) {
            case 'X':
                switch (p1) {
                    case 'A':
                        return DRAW + ROCK;
                    case 'B':
                        return LOSE + ROCK;
                    case 'C':
                        return WIN + ROCK;
                }
            case 'Y':
                switch (p1) {
                    case 'A':
                        return WIN + PAPER;
                    case 'B':
                        return DRAW + PAPER;
                    case 'C':
                        return LOSE + PAPER;
                }
            case 'Z':
                switch (p1) {
                    case 'A':
                        return LOSE + SCISSORS;
                    case 'B':
                        return WIN + SCISSORS;
                    case 'C':
                        return DRAW + SCISSORS;
                }
            default:
                return 0;
        }
    }

    /**
     * This method is used to get the tricked score of a player.
     * X -> LOSE
     * y -> DRAW
     * Z -> WIN
     * 
     * @param {char} p1 - The player play.
     * @param {char} state - The state of the play.
     * 
     * @returns {int} - The score of the player.
     */
    public int trickedFight(char p1, char state) {
        switch (state) {
            case 'X': {
                switch (p1) {
                    case 'A':
                        return LOSE + SCISSORS;
                    case 'B':
                        return LOSE + ROCK;
                    case 'C':
                        return LOSE + PAPER;
                }
            }
            case 'Y': {
                switch (p1) {
                    case 'A':
                        return DRAW + ROCK;
                    case 'B':
                        return DRAW + PAPER;
                    case 'C':
                        return DRAW + SCISSORS;
                }
            }
            case 'Z': {
                switch (p1) {
                    case 'A':
                        return WIN + PAPER;
                    case 'B':
                        return WIN + SCISSORS;
                    case 'C':
                        return WIN + ROCK;
                }
            }
            default:
                return 0;
        }
    }

    private int[] getMatches() {
        try {
            ArrayList<String> fights = new FileHandler("Days/Day2/data.txt").getFileContent();

            int[] scores = new int[2];
            for (String fight : fights) {
                String[] players = fight.split(" ");
                scores[0] += fight(players[0].charAt(0), players[1].charAt(0));
                scores[1] += trickedFight(players[0].charAt(0), players[1].charAt(0));
            }

            return scores;
        } catch (FileNotFoundException e) {
            System.out.println("Impossible de trouver le fichier");
            return new int[2];
        }
    }

    @Override
    public void run() {
        int[] scores = getMatches();
        System.out.println("Day 2 - Part 1: The score is " + scores[0]);
        System.out.println("Day 2 - Part 2: The score is " + scores[1]);
    }

}
