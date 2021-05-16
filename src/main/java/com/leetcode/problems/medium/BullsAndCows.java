package com.leetcode.problems.medium;

import java.util.HashMap;
import java.util.Map;

/**
 * You are playing the Bulls and Cows game with your friend.
 * <p>
 * You write down a secret number and ask your friend to guess what the number is. When your friend makes a guess, you provide a hint with the following info:
 * <p>
 * The number of "bulls", which are digits in the guess that are in the correct position.
 * The number of "cows", which are digits in the guess that are in your secret number but are located in the wrong position.
 * Specifically, the non-bull digits in the guess that could be rearranged such that they become bulls.
 * Given the secret number secret and your friend's guess guess, return the hint for your friend's guess.
 * <p>
 * The hint should be formatted as "xAyB", where x is the number of bulls and y is the number of cows.
 * Note that both secret and guess may contain duplicate digits.
 * Example
 * <p>
 * Input: secret = "1807", guess = "7810"
 * Output: "1A3B"
 */
public class BullsAndCows {

    public static void main(String[] args) {
        BullsAndCows bc = new BullsAndCows();
        System.out.println(bc.getHint("1807", "7810"));
    }

    public String getHint(String secret, String guess) {
        StringBuilder hint = new StringBuilder();
        int bull = 0, cow = 0;
        Map<Integer, Boolean> visitedMap = new HashMap<>();
        if (secret.length() == guess.length()) {
            populateVisitedMap(visitedMap, secret);
            // count bulls
            for (int i = 0; i < guess.length(); i++) {
                if (guess.charAt(i) == secret.charAt(i) && !visitedMap.get(i)) {
                    bull++;
                    visitedMap.put(i, Boolean.TRUE);
                }
            }
            // count cows
            Map<Character, Integer> secretMap = getSecretMap(visitedMap, secret);
            for (int i = 0; i < guess.length(); i++) {
                int count = secretMap.get(guess.charAt(i)) == null ? 0 : secretMap.get(guess.charAt(i));
                if (count > 0 && !visitedMap.get(i)) {
                    secretMap.put(guess.charAt(i), count - 1);
                    cow++;
                    visitedMap.put(i, Boolean.TRUE);
                }
            }
            hint.append(bull).append("A").append(cow).append("B");
        }
        return hint.toString();
    }

    private void populateVisitedMap(Map<Integer, Boolean> visitedMap, String secret) {
        for (int i = 0; i < secret.length(); i++) {
            visitedMap.put(i, Boolean.FALSE);
        }
    }

    private Map<Character, Integer> getSecretMap(Map<Integer, Boolean> visitedMap, String secret) {
        Map<Character, Integer> secretMap = new HashMap<>();
        for (int i = 0; i < secret.length(); i++) {
            if (!visitedMap.get(i)) {
                secretMap.put(secret.charAt(i), secretMap.getOrDefault(secret.charAt(i),0)+1);
            }
        }
        return secretMap;
    }
}
