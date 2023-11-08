package dp;

public class Solution741 {
    public static void main(String[] args) {
        int[][] case1 = new int[][]{{0, 1, -1}, {1, 0, -1}, {1, 1, 1}};
        int[][] case2 = new int[][]{{1, 1, -1}, {1, -1, 1}, {-1, 1, 1}};
        int[][] case3 = new int[][]{{1, 1, 1, 1, -1, -1, -1, 1, 0, 0},
                {1, 0, 0, 0, 1, 0, 0, 0, 1, 0}, {0, 0, 1, 1, 1, 1, 0, 1, 1, 1}, {1, 1, 0, 1, 1, 1, 0, -1, 1, 1},
                {0, 0, 0, 0, 1, -1, 0, 0, 1, -1}, {1, 0, 1, 1, 1, 0, 0, -1, 1, 0}, {1, 1, 0, 1, 0, 0, 1, 0, 1, -1},
                {1, -1, 0, 1, 0, 0, 0, 1, -1, 1}, {1, 0, -1, 0, -1, 0, 0, 1, 0, 0}, {0, 0, -1, 0, 1, 0, 1, 0, 0, 1}};

        int[][] case4 = new int[][]{{1, -1, 1, -1, 1, 1, 1, 1, 1, -1}, {-1, 1, 1, -1, -1, 1, 1, 1, 1, 1}, {1, 1, 1, -1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {-1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, {1, -1, 1, 1, 1, 1, -1, 1, 1, 1}, {1, 1, 1, -1, 1, 1, -1, 1, 1, 1},
                {1, -1, 1, -1, -1, 1, 1, 1, 1, 1}, {1, 1, -1, -1, 1, 1, 1, -1, 1, -1}, {1, 1, -1, 1, 1, 1, 1, 1, 1, 1}};
        Solution741 solution = new Solution741();
        long time = System.currentTimeMillis();
//        System.out.println("maxCherry：" + solution.maxCherry(case1)); // 5
        System.out.println("maxCherry1：" + solution.maxCherry1(case1));
        System.out.println("maxCherry2：" + solution.maxCherry2(case1));
//        System.out.println("maxCherry：" + solution.maxCherry(case2)); // 0
        System.out.println("maxCherry1：" + solution.maxCherry1(case2));
        System.out.println("maxCherry2：" + solution.maxCherry2(case2));
//        System.out.println("maxCherry：" + solution.maxCherry(case3)); // 22
        System.out.println("maxCherry1：" + solution.maxCherry1(case3));
        System.out.println("maxCherry2：" + solution.maxCherry2(case3));
//        System.out.println("maxCherry：" + solution.maxCherry(case4)); // 0
        System.out.println("maxCherry1：" + solution.maxCherry1(case4));
        System.out.println("maxCherry2：" + solution.maxCherry2(case4));
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

    // 将回溯法改造为状态转移表法
    // 如果没有荆棘，这种解法是可以的，但是有荆棘的情况下，无法排除掉走不到(n-1,n-1)的路线，如case4
    public int maxCherry1(int[][] cherry) {
        int n = cherry.length;
        // 由于同时走两条路，涉及到4个变量，所以状态空间使用四维数组
        int[][][][] cherryDp = new int[n][n][n][n];
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
        cherryDp[0][0][0][0] = cherry[0][0];

        // 循环操作状态转移方程
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                        // 必须要排除掉初始位置，不然cherryDp[0][0][0][0]会被复制为 Integer.MIN_VALUE + cherry[0][0]
                        // 导致结果恒为负数，最后输出为0
                        if (i == 0 && j == 0 && k == 0 && l == 0) {
                            continue;
                        }

                        // 如果路线A和路线B不同步则跳过，减少循环次数
                        if (i + j != k + l) {
                            continue;
                        }

                        // 如果路线A或B的当前节点是荆棘，就表示这种情况走不通，直接跳过
                        if (cherry[i][j] == -1 || cherry[k][l] == -1) {
                            continue;
                        }

                        // 设置sum1-4分别表示，能到达(i1,j1,i2,j2)的四种方式，选其中值最大的一种
                        // 这里必须设置为Integer.MIN_VALUE，只有这样才能保证路径中包含荆棘时，最后采集到的樱桃为负数
                        int sum1 = Integer.MIN_VALUE;
                        int sum2 = Integer.MIN_VALUE;
                        int sum3 = Integer.MIN_VALUE;
                        int sum4 = Integer.MIN_VALUE;
                        if (i > 0) {
                            if (k > 0) {
                                // 两条路径都走上节点
                                sum1 = cherryDp[i - 1][j][k - 1][l];
                            }
                            if (l > 0) {
                                // 路径A走上节点，路径B走左节点
                                sum2 = cherryDp[i - 1][j][k][l - 1];
                            }
                        }
                        if (j > 0) {
                            if (k > 0) {
                                // 路径A走左节点，路径B走上节点
                                sum3 = cherryDp[i][j - 1][k - 1][l];
                            }
                            if (l > 0) {
                                // 两条路径都走左节点
                                sum4 = cherryDp[i][j - 1][k][l - 1];
                            }
                        }

                        int currentCherryDp = Math.max(Math.max(sum1, sum2), Math.max(sum3, sum4)) + cherry[i][j];
                        // 如果路径A和路径B没走到同样的位置，则需要分别加上当前位置的樱桃数，如果走到了同样的位置，则只加路径A当前位置的樱桃数
                        if (i != k) {
                            currentCherryDp += cherry[k][l];
                        }
                        cherryDp[i][j][k][l] = currentCherryDp;
                    }
                }
            }
        }

        return Math.max(cherryDp[n - 1][n - 1][n - 1][n - 1], 0);
    }

    // 状态转移表法1改造为状态转移表法2
    // 由于路线A和路线B是同时进行，所以四个变量并不是完全独立的，i+j == k+l必定成立
    // 所以可以设置k=i+j, 0<=k<=2n-2, 然后将四维数组降低到三维
    public int maxCherry2(int[][] cherry) {
        int n = cherry.length;
        // 状态空间使用三维数组
        int[][][] cherryDp = new int[2 * n - 1][n][n];
        // 初始化状态
        for (int i = 0; i < 2 * n - 1; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(cherryDp[i][j], Integer.MIN_VALUE);
            }
        }
        cherryDp[0][0][0] = cherry[0][0];

        // 循环操作状态转移方程
        for (int k = 1; k < 2 * n - 1; k++) {
            for (int i1 = 0; i1 < Math.min(n, k + 1); i1++) {
                for (int i2 = 0; i2 < Math.min(n, k + 1); i2++) {
                    int j1 = k - i1;
                    int j2 = k - i2;

                    // 当k<n时，由于k可能小于i1/i2，导致j1/j2可能为复数
                    // 当k>=n时，j1/j2可能大于或等于n导致越界
                    if (j1 < 0 || j1 >= n || j2 < 0 || j2 >= n) {
                        continue;
                    }
                    // 如果路线A或路线B的当前节点是荆棘，就表示这种情况走不通
                    if (cherry[i1][j1] == -1 || cherry[i2][j2] == -1) {
                        continue;
                    }

                    // 设置sum1-4分别表示，能到达(i1,j1,i2,j2)的四种方式，选其中值最大的一种
                    // 这里必须用Integer.MIN_VALUE，只有这样才能保证路径中包含荆棘时，最后采集到的樱桃为负数
                    // maxCherry1()解法不能这么设置，因为其四层for循环中有很多冗余的步骤，并不能完全模拟两条路径的同步走法
                    int sum1 = Integer.MIN_VALUE;
                    int sum2 = Integer.MIN_VALUE;
                    int sum3 = Integer.MIN_VALUE;
                    int sum4 = Integer.MIN_VALUE;
                    if (i1 > 0) {
                        if (i2 > 0) {
                            // 两条路径都走上节点
                            sum1 = cherryDp[k - 1][i1 - 1][i2 - 1];
                        }
                        if (j2 > 0) {
                            // 路径A走上节点，路径B走左节点
                            sum2 = cherryDp[k - 1][i1 - 1][i2];
                        }
                    }
                    if (j1 > 0) {
                        if (i2 > 0) {
                            // 路径A走左节点，路径B走上节点
                            sum3 = cherryDp[k - 1][i1][i2 - 1];
                        }
                        if (j2 > 0) {
                            // 两条路径都走左节点
                            sum4 = cherryDp[k - 1][i1][i2];
                        }
                    }
                    int currentCherryDp = Math.max(Math.max(sum1, sum2), Math.max(sum3, sum4)) + cherry[i1][j1];
                    // 如果路径A和路径B没走到同样的位置，则需要分别加上当前位置的樱桃数，如果走到了同样的位置，则只加路径A当前位置的樱桃数
                    if (i1 != i2) {
                        currentCherryDp += cherry[i2][j2];
                    }
                    cherryDp[k][i1][i2] = currentCherryDp;
                }
            }
        }

        return Math.max(cherryDp[2 * n - 2][n - 1][n - 1], 0);
    }
}
