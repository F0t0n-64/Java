package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class chess {

    public static Map<Character, Integer> columnsCoordinates = new HashMap<>();
    public static Map<Character, Integer> rowsCoordinates = new HashMap<>();

    public static char[][] field = new char[17][33];

    public static void createChessboard() {
        boolean white = true;
        for(int i = 0; i < 16; i++) {
            createCircuit(i++);
            createField(i, white);
            white = ! white;
        }
        createCircuit(16);
    }

    public static void createCircuit(int row) {
        for(int i = 0; i < 33; i++) {
            if(i % 4 == 0)
                field[row][i] = '+';
            else
                field[row][i] = '-';
        }
    }

    public static void createField(int row, boolean white) {
        for(int i = 0; i < 33; i++) {
            if(i % 4 == 0) {
                field[row][i] = '|';
            } else {
                if(white) {
                    field[row][i++] = '.';
                    field[row][i++] = '.';
                    field[row][i] = '.';
                    white = false;
                } else {
                    field[row][i++] = ':';
                    field[row][i++] = ':';
                    field[row][i] = ':';
                    white = true;
                }
            }
        }
    }

    public static void putPiecesOnField(String[] pieces, boolean whitePieces) {
        int i = 0;
        for(; !Character.isLowerCase(pieces[i].charAt(0)); i++) {
            char column = pieces[i].charAt(1);
            char row    = pieces[i].charAt(2);
            field[rowsCoordinates.get(row)][columnsCoordinates.get(column)] =
                    whitePieces ? pieces[i].charAt(0) : Character.toLowerCase(pieces[i].charAt(0));
        }
        for(; i < pieces.length; i++) {
            char column = pieces[i].charAt(0);
            char row    = pieces[i].charAt(1);
            field[rowsCoordinates.get(row)][columnsCoordinates.get(column)] =
                    whitePieces ? 'P' : 'p';
        }
    }

    public static void main(String[] args) {
        columnsCoordinates.put('a', 2);
        columnsCoordinates.put('b', 6);
        columnsCoordinates.put('c', 10);
        columnsCoordinates.put('d', 14);
        columnsCoordinates.put('e', 18);
        columnsCoordinates.put('f', 22);
        columnsCoordinates.put('g', 26);
        columnsCoordinates.put('h', 30);
        rowsCoordinates.put('1', 15);
        rowsCoordinates.put('2', 13);
        rowsCoordinates.put('3', 11);
        rowsCoordinates.put('4', 9);
        rowsCoordinates.put('5', 7);
        rowsCoordinates.put('6', 5);
        rowsCoordinates.put('7', 3);
        rowsCoordinates.put('8', 1);
        createChessboard();
        Scanner scanner = new Scanner(System.in);
        String[] whitePieces = (scanner.nextLine().split(": "))[1].split(",");
        String[] blackPieces = (scanner.nextLine().split(": "))[1].split(",");

        putPiecesOnField(whitePieces,true);
        putPiecesOnField(blackPieces,false);

        for(int i = 0; i < 17; i ++) {
            for(int j = 0; j < 33; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}
