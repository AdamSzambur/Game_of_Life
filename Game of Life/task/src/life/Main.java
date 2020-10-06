package life;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        //Universe universe = new Universe(sc.nextInt(),sc.nextInt(),sc.nextInt());
//        Universe universe = new Universe(sc.nextInt(),2,11);
//        universe.printEvolution(25);

        new GameOfLife();

    }
}
