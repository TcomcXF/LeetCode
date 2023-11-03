package others;

public class Solution123 {
    public int maxProfit(int[] prices) {
        int maxProfit1;
        int maxProfit2;
        int maxProfit = 0;
        if (prices.length < 2) {
            return 0;
        }
        for (int i = -1; i < prices.length; i++) {
            maxProfit1 = getMaxProfit(prices, 0, i);
            maxProfit2 = getMaxProfit(prices, i+1, prices.length - 1);
            maxProfit = Math.max(maxProfit, maxProfit1 + maxProfit2);
        }
        return maxProfit;
    }

    public int getMaxProfit(int[] prices, int left, int right) {
        if (right < 0 || left > prices.length - 1) {
            return 0;
        }
        int minPrices = prices[left];
        int maxProfit = 0;

        for (int i = left; i <= right; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrices);
            minPrices = Math.min(minPrices, prices[i]);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = new int[]{1,2,4,2,5,7,2,4,9,0};
        System.out.println(new Solution123().maxProfit(prices));
    }
}
