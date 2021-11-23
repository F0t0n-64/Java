package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class dresy {

    static boolean result = true;

    public static void checkPlan(BufferedReader br) throws IOException {
        result = true;
        char[] plan = br.readLine().toCharArray();
        List<Integer> clothes = new ArrayList<>();
        for(int i = 0 ; i < plan.length; i++) {
            if (i + 1 < plan.length
                    && ((plan[i] == '(' && plan[i + 1] == '*')
                    || ( plan[i] == '*' && plan[i + 1] == ')'))) {
                if (plan[i] == '(' && plan[i + 1] == '*')
                    clothes.add(0);
                else
                    if(controlCharacter(clothes, 0))
                        break;
                i++;
            } else if (checkForMatchOnCharacter(plan[i],'(',')','[',']','{','}','<','>')) {
                if (plan[i] == '(')      clothes.add(1);
                else if (plan[i] == '[') clothes.add(2);
                else if (plan[i] == '{') clothes.add(3);
                else if (plan[i] == '<') clothes.add(4);
                else if (plan[i] == ')') {
                    if (controlCharacter(clothes, 1))
                        break;
                } else if (plan[i] == ']') {
                    if (controlCharacter(clothes, 2))
                        break;
                } else if (plan[i] == '}') {
                    if (controlCharacter(clothes, 3))
                        break;
                } else if (plan[i] == '>') {
                    if (controlCharacter(clothes, 4))
                        break;
                }
            }
        }
        if(clothes.isEmpty() && result)
            System.out.println("Plan je v poradku.");
        else
            System.out.println("V planu je chyba.");
    }

    public static boolean checkForMatchOnCharacter(char plan, char... character) {
        for(char i : character) {
            if (i == plan) {
                return true;
            }
        }
        return false;
    }

    public static boolean controlCharacter(List<Integer> clothes, int number) {
        if (clothes.isEmpty() || clothes.get(clothes.size() - 1) != number) {
            result = false;
            return true;
        } else {
             clothes.remove(clothes.size() - 1);
             return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tests = Integer.parseInt(br.readLine());
        for(int i = 0; i < tests; i++)
            checkPlan(br);
    }
}
