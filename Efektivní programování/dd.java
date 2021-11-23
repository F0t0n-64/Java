package com.company;

import java.util.*;

public class dd {

    static class Time {

        public final static List<char[][]> numbers = new ArrayList<>();

        public static void printTimeByNumber(int number, int i) {
            for (int k = 0; k < 5; k++)
                System.out.print(numbers.get(number)[i][k]);
        }

        public static void printColon(int i) {
            System.out.print(numbers.get(10)[i][0]);
        }

    }

    public final static char[][] zero = {
            {'+', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '|'},
            {'+', ' ', ' ', ' ', '+'},
            {'|', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'}
    };

    public final static char[][] one = {
            {' ', ' ', ' ', ' ', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+'},
    };

    public final static char[][] two = {
            {'+', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', ' '},
            {'|', ' ', ' ', ' ', ' '},
            {'+', '-', '-', '-', '+'},
    };

    public final static char[][] three = {
            {'+', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
    };

    public final static char[][] four = {
            {'+', ' ', ' ', ' ', '+'},
            {'|', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+'},
    };

    public final static char[][] five = {
            {'+', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', ' '},
            {'|', ' ', ' ', ' ', ' '},
            {'+', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
    };

    public final static char[][] six = {
            {'+', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', ' '},
            {'|', ' ', ' ', ' ', ' '},
            {'+', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
    };

    public final static char[][] seven = {
            {'+', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '+'},
    };

    public final static char[][] eight = {
            {'+', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
    };

    public final static char[][] nine = {
            {'+', '-', '-', '-', '+'},
            {'|', ' ', ' ', ' ', '|'},
            {'|', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
            {' ', ' ', ' ', ' ', '|'},
            {' ', ' ', ' ', ' ', '|'},
            {'+', '-', '-', '-', '+'},
    };


    final static char[][] colon = {
            {' '},
            {' '},
            {'o'},
            {' '},
            {'o'},
            {' '},
            {' '}
    };

    public static void printTime(String time) {
        char[] timesSymbols = time.toCharArray();
        for(int i = 0; i < 7; i++) {
            Time.printTimeByNumber(Integer.parseInt(String.valueOf(timesSymbols[0])), i);
            System.out.print("  ");
            Time.printTimeByNumber(Integer.parseInt(String.valueOf(timesSymbols[1])), i);
            System.out.print("  ");
            Time.printColon(i);
            System.out.print("  ");
            Time.printTimeByNumber(Integer.parseInt(String.valueOf(timesSymbols[3])), i);
            System.out.print("  ");
            Time.printTimeByNumber(Integer.parseInt(String.valueOf(timesSymbols[4])), i);
            System.out.println();
        }
        System.out.print("\n\n");
    }


    public static void main(String[] args) {
        Time.numbers.add(zero);
        Time.numbers.add(one);
        Time.numbers.add(two);
        Time.numbers.add(three);
        Time.numbers.add(four);
        Time.numbers.add(five);
        Time.numbers.add(six);
        Time.numbers.add(seven);
        Time.numbers.add(eight);
        Time.numbers.add(nine);
        Time.numbers.add(colon);
        Scanner scanner = new Scanner(System.in);
        String time = scanner.nextLine();
        List<String> times = new ArrayList<>();
        while (! time.equals("end")) {
            times.add(time);
            time = scanner.nextLine();
        }
        for(var i : times)
            printTime(i);
        System.out.println("end");
    }
}
