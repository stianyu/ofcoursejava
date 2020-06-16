package StringProcess.UsingStack._726_CountOfAtoms;

import java.util.*;

public class Solution {
    public String countOfAtoms(String formula) {
        if(formula == null || formula.length() == 0) {
            return "";
        }

        Map<String, Integer> map = new TreeMap<>();
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = formula.toCharArray();

        for(int i = 0; i < chars.length; i++) {
            if(chars[i] == ')') {
                int time = 0;
                while (i+1 < chars.length && Character.isDigit(chars[i + 1])) {
                    time = 10*time + chars[i + 1] - '0';
                    i++;
                }
                StringBuilder sb = new StringBuilder();
                Character c;
                while((c = stack.pop()) != '(') {
                    sb.append(c);
                }
                if (time == 0) {
                    time = 1;
                }
                sb.reverse();
                for(int j = 0; j < time; j++) {
                    for(Character ch : sb.toString().toCharArray()) {
                        stack.push(ch);
                    }
                }
            } else if(Character.isDigit(chars[i]) && Character.isLowerCase(chars[i - 1])){
                int time = chars[i] - '0';
                while (i+1 < chars.length && Character.isDigit(chars[i + 1])) {
                    time = 10*time + chars[i + 1] - '0';
                    i++;
                }
                char c = stack.pop();
                char c2 = stack.pop();
                for(int j = 0; j < time; j++) {
                    stack.push(c2);
                    stack.push(c);
                }
            } else if(Character.isDigit(chars[i]) && Character.isUpperCase(chars[i - 1])){
                int time = chars[i] - '0';
                while (i+1 < chars.length && Character.isDigit(chars[i + 1])) {
                    time = 10*time + chars[i + 1] - '0';
                    i++;
                }
                char c = stack.pop();
                for(int j = 0; j < time; j++) {
                    stack.push(c);
                }
            } else {
                stack.push(chars[i]);
            }
        }

        while(!stack.isEmpty()) {
            Character ch = stack.pop();
            StringBuilder atom = new StringBuilder();
            if(Character.isLowerCase(ch) && stack.peek() != null && Character.isUpperCase(stack.peek())) {
                atom.append(ch);
                atom.append(stack.pop());
                atom.reverse();
            } else {
                atom.append(ch);
            }
            map.put(atom.toString(), map.getOrDefault(atom.toString(), 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        map.forEach((key, value) -> {
            sb.append(key);
            if(value != 1) {
                sb.append(value);
            }
        });

        return sb.toString();
    }

    public String countOfAtoms2(String formula) {
        if (formula == null || formula.length() == 0) {
            return "";
        }

        Map<String, Integer> map = new TreeMap<>();
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = formula.toCharArray();

        for(int i = 0; i < chars.length; i++) {
            if (chars[i] == ')') {
                // 记录 （）后的倍数，有数字则 time 为后面的所有数字，不为数字则 time = 1
                int time = 0;
                while (i+1 < chars.length && Character.isDigit(chars[i + 1])) {
                    time = 10*time + chars[i + 1] - '0';
                    i++;
                }
                if (time == 0) {
                    time = 1;
                }
                // 解括号，一层括号
                 Map<String, Integer> mapIn = new HashMap<>();
                while(stack.peek() != '(') {
                    int count = 1;
                    StringBuilder numStr = new StringBuilder();
                    while (Character.isDigit(stack.peek())) {
                        numStr.append(stack.pop());
                    }
                    numStr.reverse();
                    if (numStr.length() != 0){
                        count = Integer.parseInt(numStr.toString());
                    }
                    StringBuilder atom = new StringBuilder();
                    while (Character.isLowerCase(stack.peek())) {
                        atom.append(stack.pop());
                    }
                    atom.append(stack.pop());
                    atom.reverse();
                    mapIn.put(atom.toString(), mapIn.getOrDefault(atom.toString(), 0)  + count);
                }
                stack.pop();
                for (String key : mapIn.keySet()) {
                    int value = mapIn.get(key);
                    map.put(key, map.getOrDefault(key, 0) + value * time);
                }
            } else {
                stack.push(chars[i]);
            }
        }

        while(!stack.isEmpty()) {
            int time = 1;
            StringBuilder numStr = new StringBuilder();
            while (Character.isDigit(stack.peek())) {
                numStr.append(stack.pop());
            }
            numStr.reverse();
            if (numStr.length() != 0){
                time = Integer.parseInt(numStr.toString());
            }
            StringBuilder atom = new StringBuilder();
            while (Character.isLowerCase(stack.peek())) {
                atom.append(stack.pop());
            }
            atom.append(stack.pop());
            atom.reverse();
            map.put(atom.toString(), map.getOrDefault(atom.toString(), 0) + time);
        }

        StringBuilder sb = new StringBuilder();
        for (String key : map.keySet()) {
            sb.append(key);
            int value = 0;
            if ((value = map.get(key)) != 1) {
                sb.append(value);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        String formula = "((B40He3)33(B)15)14";
        Solution solution = new Solution();
        System.out.println(solution.countOfAtoms2(formula));
    }
}
