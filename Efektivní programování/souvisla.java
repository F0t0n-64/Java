package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class souvisla {

    public static List<Integer> maxLength = new ArrayList<>();

    public static void readSequenceAndProcess(Scanner scanner) {
        List<Integer> subsequence = new ArrayList<>();
        int numberOfSubsequence = scanner.nextInt();
        while (numberOfSubsequence != 0) {
            subsequence.add(numberOfSubsequence);
            numberOfSubsequence = scanner.nextInt();
        }
        findLargestSubsequence(subsequence);
    }

    public static void findLargestSubsequence(List<Integer> list) {
        int maxSubsequence = 0;
        int current = 1;
        for (int i = 0; i < list.size() - 1; i++) {
            if ((list.get(i) < list.get(i + 1)) ||  (list.get(i).equals(list.get(i + 1)))) {
                current++;
            } else {
                if (maxSubsequence < current)
                    maxSubsequence = current;
                current = 1;
            }
        }
        maxLength.add(Math.max(maxSubsequence, current));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberTasks = scanner.nextInt();
        for(int i = 0; i < numberTasks; i++)
            readSequenceAndProcess(scanner);
        maxLength.forEach(System.out::println);
    }
}
