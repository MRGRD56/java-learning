public class Leetcode48 {
    public static void main(String[] args) {
        new Solution().rotate(new int[][] {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10,11,12},
                {13,14,15,16}
        });
    }

    static class Solution {
        public void rotate(int[][] matrix) {
            final int n = matrix.length;

            int[][] result = new int[n][n];

            for (int y = 0; y < n; y++) {
                for (int x = 0; x < n; x++) {
                    result[x][(n - 1) - y] = matrix[y][x];
                }
            }

            for (int y = 0; y < n; y++) {
                System.arraycopy(result[y], 0, matrix[y], 0, n);
            }
        }
    }
}
