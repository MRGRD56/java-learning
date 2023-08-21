public class Leetcode50 {
    class Solution {
        public double myPow(double x, int n) {
            if (x == 1 || n == 0) {
                return 1;
            }

            double result = 1;

            if (n > 0) {
                for (int i = 0; i < n; i++) {
                    result *= x;
                }
            } else {
                for (int i = 0; i < -n; i++) {
                    result /= x;
                }
            }

            return result;
        }
    }
}
