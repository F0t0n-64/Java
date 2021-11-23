package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class polish {

    public static List<Character> array = new ArrayList<>();

    public static int translateData(int index) {
        if(index >= array.size())
            return 9999999;
        if(array.get(index) - '0' >= 0 && array.get(index) - '0' <= 9) {
            System.out.print(array.get(index) + " ");
            return index + 1;
        }
        int firstValue = translateData(index + 1);
        int secondValue = translateData(firstValue);
        System.out.print(array.get(index) + " ");
        return secondValue;
    }

    public static void main(String[] args) throws IOException {
	    String data;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            array.clear();
            data = br.readLine();
            if(data.equals("0"))
                return;
            for(int i = 0; i < data.length(); i++)
                if(i % 2 == 0)
                    array.add(data.charAt(i));
            translateData(0);
        }
    }
}
