package others;

import java.util.ArrayList;
import java.util.List;

public class Solution118 {
    public List<List<Integer>> generate(int numRows) {
        int[] lastRow = new int[numRows];
        int[] curRow = new int[numRows];
        int[] tempRow;
        List<List<Integer>> dp = new ArrayList<>();
        List<Integer> curRowList = new ArrayList<>();
        lastRow[0] = 1;
        curRow[0] = 1;
        curRowList.add(1);
        dp.add(curRowList);

        for (int i = 1; i < numRows; i++) { //i为层数，从0层开始
            curRowList = new ArrayList<>();
            curRowList.add(1);
            curRow[i] = 1;
            for (int j = 1; j < i; j++) {
                curRow[j] = lastRow[j-1] + lastRow[j];
                curRowList.add(lastRow[j-1] + lastRow[j]);
            }
            curRowList.add(1);
            dp.add(curRowList);

            tempRow = lastRow;
            lastRow = curRow;
            curRow = tempRow;
        }
        return dp;
    }

    public static void main(String[] args) {
        List<List<Integer>> dp = new Solution118().generate(5);
        for (List<Integer> l: dp){
            System.out.println(l.toString());
        }
    }
}
