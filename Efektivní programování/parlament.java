package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class parlament {

    static int [][] array = new int[1001][1001];
    static int [][] parliament = new int[1001][1001];

    public static void performTask(BufferedReader br) throws IOException {
        String[] sizeParliament = br.readLine().split(" ");
        int rows = Integer.parseInt(sizeParliament[0]);
        int columns = Integer.parseInt(sizeParliament[1]);
        for(int i = 0; i < rows; i++) {
            String[] dataInRow = br.readLine().split(" ");
            for(int j = 0; j < columns; j++) {
                array[i][j] = Integer.parseInt(dataInRow[j]);
                if(i==0) {
                    if(j==0)
                        parliament[i][j] = array[i][j];
                    else
                        parliament[i][j] =  parliament[i][j-1] + array[i][j];
                } else if(j==0) {
                    parliament[i][j] = parliament[i - 1][j] + array[i][j];
                } else
                    parliament[i][j] = parliament[i-1][j] + parliament[i][j-1] + array[i][j] - parliament[i-1][j-1];
            }
        }
        int answers = Integer.parseInt(br.readLine());
        for(int i = 0; i < answers; i++) {
            String[] dataInRow = br.readLine().split(" ");
            int r1 = (Integer.parseInt(dataInRow[0])) - 1;
            int s1 = (Integer.parseInt(dataInRow[1])) - 1;
            int r2 = (Integer.parseInt(dataInRow[2])) - 1;
            int c2 = (Integer.parseInt(dataInRow[3])) - 1;
            int result = parliament[r2][c2];
            if(r1 > 0 && s1 > 0)
                result += parliament[r1 - 1][s1 - 1];
            if(s1 > 0)
                result -= parliament[r2][s1 - 1];
            if(r1 > 0)
                result -= parliament[r1 - 1][c2];
            System.out.println("Absolutni hodnota pohodlnosti je " + result + " bodu.");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(br.readLine());
        for(int i = 0; i < tests; i++)
            performTask(br);
    }
}
