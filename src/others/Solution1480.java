package others;

public class Solution1480 {
    public int[] runningSum(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1] + nums[i];
        }
        return dp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,1,2,10,11};
        nums = new Solution1480().runningSum(nums);
        for (int i : nums) {
            System.out.print(i);
            System.out.print(",");
        }
    }
}
