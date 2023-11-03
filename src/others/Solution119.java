package others;

import java.util.ArrayList;
import java.util.List;

public class Solution119 {
    public List<Integer> getRow(int rowIndex) {
        rowIndex += 1;
        int[] lastRow = new int[rowIndex];
        int[] curRow = new int[rowIndex];
        int[] tempRow;
        ArrayList<Integer> curRowList = new ArrayList<Integer>();

        lastRow[0] = 1;
        curRow[0] = 1;
        curRowList.add(1);

        for (int i = 1; i < rowIndex; i++) { //i为层数，从0层开始
            curRow[i] = 1;
            for (int j = 1; j < i; j++) {
                curRow[j] = lastRow[j-1] + lastRow[j];
                if (i == rowIndex - 1) {
                    curRowList.add(lastRow[j-1] + lastRow[j]);
                }
            }
            if (i == rowIndex - 1) {
                curRowList.add(1);
            }
            tempRow = lastRow;
            lastRow = curRow;
            curRow = tempRow;
        }
        return curRowList;
    }

    public static void main(String[] args) {
        List<Integer> dp = new Solution119().getRow(3);
        System.out.println(dp.toString());
    }
}
