package dp;

public class Solution42 {
    public static void main(String[] args) {
        Solution42 solution42 = new Solution42();
        int[] care1 = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] care2 = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(solution42.trap2(care1));
        System.out.println(solution42.trap2(care2));

    }

    // 按柱子高度(水位线)划分为多层级，第i层的接水量和第i-1层、第i+1层都无关，考虑分治算法
    // 可以分别求出每一层的接水量，然后求和即可
    // 遍历找出最高的柱子，设置为阶段总数
    public int trap1(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int maxHeight = 0;
        // 找出最高的柱子
        for (int i = 0; i < n; i++) {
            if (maxHeight < height[i]) {
                maxHeight = height[i];
            }
        }

        // 设置状态空间
        int water = 0;

        for (int i = 1; i < maxHeight + 1; i++) {
            water += trapOfCertainStagePro(height, i);
        }

        return water;
    }

    // 参数k为水位线(阶段)，求第k层能接多少水
    public int trapOfCertainStage(int[] height, int k) {
        int n = height.length;
        // 找出接水的起点
        int start;
        for (start = 0; start < n; start++) {
            if (height[start] - k >= 0) {
                break;
            }
        }
        // 找出接水的终点
        int end;
        for (end = n - 1; end > 0; end--) {
            if (height[end] - k >= 0) {
                break;
            }
        }
        if (start == n || start == end) {
            return 0;
        }
        int water = 0;
        for (int i = start; i <= end; i++) {
            if (height[i] - k < 0) {
                water++;
            }
        }
        return water;
    }

    // 优化求某一层接水量的方法
    public int trapOfCertainStagePro(int[] height, int k) {
        int n = height.length;
        int water = 0;
        int tempWater = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (height[i] - k < 0) {
                tempWater++;
            } else {
                water += Math.max(tempWater, 0);
                tempWater = 0;
            }
        }
        return water;
    }

    // 由求某一层的接水量的思路扩展，想想怎么求某一列的接水量
    // 设某一列柱子高度为0，当左右存在高度为1的柱子时，接水量为1
    // 推广可得：柱子高度为i，left_max为它左边最高的柱子，right_max为它右边最高的柱子，那么他的接水量就是max(min(left_max, right_max) - i, 0)
    public int trap2(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }
        int water = 0;
        // 首位两列可以忽略
        for (int i = 1; i < n - 1; i++) {
            int leftMax = getMaxHeight(height, 0, i - 1);
            int rightMax = getMaxHeight(height, i + 1, n - 1);
            water += Math.max(0, Math.min(leftMax, rightMax) - height[i]);
        }
        return water;
    }

    public int getMaxHeight(int[] height, int start, int end) {
        int max = 0;
        for (int i = start; i <= end; i++) {
            if (max < height[i]) {
                max = height[i];
            }
        }
        return max;
    }

    // trap2中每列都需要求一次左侧最高列和右侧最高列，浪费了不少时间，可以继续优化
    // 设置一个leftMax[],leftMax[i]表示第i列左侧最高的柱子
    // 设置一个rightMax[],rightMax[i]表示第i列右侧最高的柱子
    public int trap3(int[] height) {
        int n = height.length;
        if (n < 3) {
            return 0;
        }

        // 设置左侧最高列数组
        int[] leftMax = new int[n];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i - 1]);
        }

        // 设置右侧最高列数组
        int[] rightMax = new int[n];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i + 1]);
        }

        int water = 0;
        // 首位两列可以忽略
        for (int i = 1; i < n - 1; i++) {
            water += Math.max(0, Math.min(leftMax[i], rightMax[i]) - height[i]);
        }
        return water;
    }

}
