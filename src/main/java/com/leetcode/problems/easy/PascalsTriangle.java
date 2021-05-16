package com.leetcode.problems.easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem 118.
 * Given an integer numRows, return the first numRows of Pascal's triangle.
 * In Pascal's triangle, each number is the sum of the two numbers directly above it.
 * Input: numRows = 5
 * Output: [[1],
 *        [1,1],
 *       [1,2,1],
 *      [1,3,3,1],
 *     [1,4,6,4,1]]
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        PascalsTriangle s = new PascalsTriangle();
        System.out.println(s.generate(5));
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = initializeLists(numRows);
        for (int line = 0; line < numRows; line++) {
            for (int i = 0; i <= line; i++) {
                if (i == 0 || line == i) {
                    // Store corner one's;
                    list.get(line).add(i, 1);
                } else {
//                     Store other numbers
                    Integer sum = list.get(line - 1).get(i - 1) + list.get(line - 1).get(i);
                    list.get(line).add(i, sum);
                }
            }
        }
        return list;
    }

    private List<List<Integer>> initializeLists(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        for (int line = 0; line < numRows; line++) {
            List<Integer> tmp = new ArrayList<>();
            list.add(line, tmp);
        }
        return list;
    }
}
