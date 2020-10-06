package life;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Universe {
    private int generations;

    private int[][] currentState;
    private int[][] nextState;

    public Universe(int size, int seed, int generations) {
        this.generations = generations;
        currentState = new int[size][size];
        nextState = new int[size][size];
        initialUniverse(seed);
    }

    private void initialUniverse(int seed) {
        Random rnd = new Random(seed);
        for (int i = 0; i < currentState.length; i++) {
            for (int j = 0; j < currentState.length; j++) {
                if (rnd.nextBoolean()) {
                    currentState[i][j] = 1;
                } else {
                    currentState[i][j] = 0;
                }
            }
        }
    }

    private void printUniverse() {
        for (int[] inits : currentState) {
            for (int j = 0; j < currentState.length; j++) {
                if (inits[j] == 1) {
                    System.out.print("o");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public void printEvolution(int delay) {
        for (int i = 0; i<generations; i++){


            try {
                if (System.getProperty("os.name").contains("Windows"))
                    new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
                else
                    Runtime.getRuntime().exec("clear");
            }
            catch (IOException | InterruptedException e) {}


            System.out.println("Generation #"+(i+1));
            System.out.println("Alive: "+sumAlive(currentState));
//            System.out.println();
            printUniverse();
            evolveUniverse();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private int sumAlive(int[][] universe) {
        int result =0;
        for (int[] row : universe) {
            for (int col : row) {
                result += col;
            }
        }
        return result;
    }

    private void evolveUniverse() {
        for (int j = 0; j < currentState.length; j++) {
            for (int k = 0; k < currentState.length; k++) {

                int temp = currentState.length;
                int n = (j-1+temp)%temp;
                int s = (j+1+temp)%temp;
                int w = (k-1+temp)%temp;
                int e = (k+1+temp)%temp;

                int sum = currentState[n][w]
                        + currentState[n][k]
                        + currentState[n][e]
                        + currentState[j][w]
                        + currentState[j][e]
                        + currentState[s][w]
                        + currentState[s][k]
                        + currentState[s][e];

                if (currentState[j][k] == 0) {
                    if (sum == 3) {
                        nextState[j][k] = 1;
                    } else {
                        nextState[j][k] = 0;
                    }
                } else {
                    if (sum == 2 || sum ==3) {
                        nextState[j][k] = 1;
                    } else {
                        nextState[j][k] = 0;
                    }
                }
            }
        }
        for (int p=0; p< currentState.length;p++) {
            System.arraycopy(nextState[p], 0, currentState[p], 0, currentState.length);
        }
    }
}

