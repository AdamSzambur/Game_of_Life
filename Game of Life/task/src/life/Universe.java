package life;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Universe {
    private int seed;
    private int generations;

    private int[][] currentState;
    private int[][] nextState;

    public Universe(int size, int seed, int generations) {
        this.seed = seed;
        this.generations = generations;
        currentState = new int[size][size];
        nextState = new int[size][size];
        initialUniverse();
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    private void initialUniverse() {
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

    public void printUniverse() {
//        for (int[] ints : currentState) {
//            for (int j = 0; j < currentState.length; j++) {
//                if (ints[j] == 1) {
//                    System.out.print("0");
//                } else {
//                    System.out.print(" ");
//                }
//            }
//            System.out.println();
//        }

        System.out.println("\ncurrentState");
        for (int[] tab : currentState) {
            System.out.println(Arrays.toString(tab));
        }
//
//        System.out.println("\nnextState");
//        for (int[] tab : nextState) {
//            System.out.println(Arrays.toString(tab));
//        }
    }

    public void evolveUniverse() {
        for (int i = 0; i < generations; i++) {

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

            for (int p=0; p<currentState.length;p++) {
                currentState[p] = nextState.clone()[p];
            }

        }
    }
}
