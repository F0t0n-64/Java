package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        int[] array = new int[31];
        array[0] = 0;
        array[1] = 1;
        for (int i = 2; i <= 30; i++)
            array[i] = array[i - 1] + array[i - 1] + 1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            int numbersCount = br.read();
            int number, current = 0, result = 0;
            for (int j = 0; j < numbersCount; j++) {
                number = br.read();
                if (i == 1) {
                    current = number;
                } else if (number != current) {
                    result += array[numbersCount - 1] + 1;
                    for (int k = 0; k < 3; k++) {
                        if (number != k && current != j) {
                            current = j;
                            break;
                        }
                    }
                }
            }
            System.out.println("Je treba" + result + "presunu.");
        }
    }
}
