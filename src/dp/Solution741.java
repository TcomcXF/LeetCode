package dp;

public class Solution741 {
    public static void main(String[] args) {
        long time = System.currentTimeMillis();
        System.out.println(new Solution741().maxCherry(new int[][]{{0, 1, -1}, {1, 0, -1}, {1, 1, 1}}));
        System.out.println(new Solution741().maxCherry1(new int[][]{{0, 1, -1}, {1, 0, -1}, {1, 1, 1}}));
        // 5
        System.out.println(new Solution741().maxCherry(new int[][]{{1, 1, -1}, {1, -1, 1}, {-1, 1, 1}}));
        System.out.println(new Solution741().maxCherry1(new int[][]{{1, 1, -1}, {1, -1, 1}, {-1, 1, 1}}));
        // 0
        System.out.println(new Solution741().maxCherry(new int[][]{{1, 1, 1, 1, -1, -1, -1, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0}, {0, 0, 1, 1, 1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0, -1, 1, 1},
                {0, 0, 0, 0, 1, -1, 0, 0, 1, -1}, {1, 0, 1, 1, 1, 0, 0, -1, 1, 0}, {1, 1, 0, 1, 0, 0, 1, 0, 1, -1},
                {1, -1, 0, 1, 0, 0, 0, 1, -1, 1}, {1, 0, -1, 0, -1, 0, 0, 1, 0, 0}, {0, 0, -1, 0, 1, 0, 1, 0, 0, 1}}));
        System.out.println(new Solution741().maxCherry1(new int[][]{{1, 1, 1, 1, -1, -1, -1, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0}, {0, 0, 1, 1, 1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0, -1, 1, 1},
                {0, 0, 0, 0, 1, -1, 0, 0, 1, -1}, {1, 0, 1, 1, 1, 0, 0, -1, 1, 0}, {1, 1, 0, 1, 0, 0, 1, 0, 1, -1},
                {1, -1, 0, 1, 0, 0, 0, 1, -1, 1}, {1, 0, -1, 0, -1, 0, 0, 1, 0, 0}, {0, 0, -1, 0, 1, 0, 1, 0, 0, 1}}));
        // 22
        long timeDiff = System.currentTimeMillis() - time;
        System.out.println("耗时=" + timeDiff);
    }

    int[][][][] cherryDp;
    int n = 0;

    // 回溯法
    // 通过递归遍历两条路径的全部走法，由于同时走两条路径，所以状态的变量有四个，路径1的横纵坐标和路径2的横纵坐标
    public int maxCherry(int[][] cherry) {
        n = cherry.length;
        // 由于同时走两条路，涉及到4个变量，所以状态空间使用四维数组
        cherryDp = new int[n][n][n][n];
        // 初始化状态
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        cherryDp[i][j][k][l] = Integer.MIN_VALUE;
                    }
                }
            }
        }
        // 递归入口
        maxCherry(cherry, 0, 0, 0, 0);

        return Math.max(cherryDp[n - 1][n - 1][n - 1][n - 1], 0);
    }

    public void maxCherry(int[][] cherry, int i1, int j1, int i2, int j2) {
        // 递归出口
        if (i1 == n || j1 == n || i2 == n || j2 == n) {
            return;
        }

        // 任何一条路碰到荆棘时就表示这种情况走不通，直接设置为0
        if (cherry[i1][j1] == -1 || cherry[i2][j2] == -1) {
            cherryDp[i1][j1][i2][j2] = 0;
            return;
        }

        // 设置sum1-4分别表示，能到达(i1,j1,i2,j2)的四种方式，选其中值最大的一种
        int sum1 = 0;
        int sum2 = 0;
        int sum3 = 0;
        int sum4 = 0;
        if (i1 > 0) {
            if (i2 > 0) {
                // 两条路径都走上节点
                sum1 = cherryDp[i1 - 1][j1][i2 - 1][j2];
            }
            if (j2 > 0) {
                // 路径1走上节点，路径2走左节点
                sum2 = cherryDp[i1 - 1][j1][i2][j2 - 1];
            }
        }
        if (j1 > 0) {
            if (i2 > 0) {
                // 路径1走左节点，路径2走上节点
                sum3 = cherryDp[i1][j1 - 1][i2 - 1][j2];
            }
            if (j2 > 0) {
                // 两条路径都走左节点
                sum4 = cherryDp[i1][j1 - 1][i2][j2 - 1];
            }
        }

        int currentCherryDp = Math.max(Math.max(sum1, sum2), Math.max(sum3, sum4)) + cherry[i1][j1];
        // 如果路径1和路径2没走到同样的位置，则需要分别加上当前位置的樱桃数，如果走到了同样的位置，则只加路径1当前位置的樱桃数
        if (i1 != i2 || j1 != j2) {
            currentCherryDp += cherry[i2][j2];
        }
        // 多种方式都能走到这个节点，需要选取历史最大值
        cherryDp[i1][j1][i2][j2] = Math.max(cherryDp[i1][j1][i2][j2], currentCherryDp);

        // 当前位置处理完后，需要继续向下递归
        // 路线1向下
        if (i1 + 1 < n) {
            // 路线2向下
            if (i2 + 1 < n) {
                maxCherry(cherry, i1 + 1, j1, i2 + 1, j2);
            }
            // 路线2向右
            if (j2 + 1 < n) {
                maxCherry(cherry, i1 + 1, j1, i2, j2 + 1);
            }
        }
        // 路线1向右
        if (j1 + 1 < n) {
            // 路线2向下
            if (i2 + 1 < n) {
                maxCherry(cherry, i1, j1 + 1, i2 + 1, j2);
            }
            // 路线2向右
            if (j2 + 1 < n) {
                maxCherry(cherry, i1, j1 + 1, i2, j2 + 1);
            }
        }
    }
}
