public class Leetcode34 {
    public static void main(String[] args) {
//        int[] result = new Solution().searchRange(new int[] {1,1,5,7,8,8,8,8,8,10,11}, 8);
//        int[] result = new Solution().searchRange(new int[] {1}, 1);
        int[] result = new Solution().searchRange(new int[] {1,3}, 1);
        Object __null = null;
    }

    static
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            if (nums.length == 0) {
                return new int[] {-1, -1};
            }

            if (nums.length == 1) {
                if (nums[0] == target) {
                    return new int[] {0, 0};
                }

                return new int[] {-1, -1};
            }

            int minIndex = 0;
            int maxIndex = nums.length - 1;

            int targetFirstIndex = -1;
            int targetLastIndex = -1;

            do {
                int index = (int) Math.round((minIndex + maxIndex) / 2D);

                int value = nums[index];

                if (value < target) {
                    minIndex = Math.min(index + 1, nums.length - 1);
                    continue;
                }
                if (value > target) {
                    maxIndex = Math.max(index - 1, 0);
                    continue;
                }

                targetFirstIndex = index;
                targetLastIndex = index;
                break;
            } while (minIndex != maxIndex || nums[minIndex] == target);

            if (targetFirstIndex == -1) {
                return new int[] {targetFirstIndex, targetLastIndex};
            }

            do {
                int index = (int) Math.floor((targetFirstIndex + minIndex) / 2D);

                int value = nums[index];

                if (value < target) {
                    minIndex = Math.min(index + 1, nums.length - 1);
                    continue;
                }

                targetFirstIndex = index;
            } while (targetFirstIndex != minIndex);

            do {
                int index = (int) Math.ceil((targetLastIndex + maxIndex) / 2D);

                int value = nums[index];

                if (value > target) {
                    maxIndex = Math.max(index - 1, 0);
                    continue;
                }

                targetLastIndex = index;
            } while (targetLastIndex != maxIndex);

            return new int[] {targetFirstIndex, targetLastIndex};
        }
    }

    static class Solution_bak1 {
        public int[] searchRange(int[] nums, int target) {
            // [1,1,5,7,8,8,8,8,8,10,11]
            //      >     !     <

            if (nums.length == 0) {
                return new int[] {-1, -1};
            }

            int minIndex = 0;
            int maxIndex = nums.length - 1;

            int targetFirstIndex = -1;
            int targetLastIndex = -1;

            do {
                int index = Math.min((int) Math.round((minIndex + maxIndex) / 2D), nums.length - 1);

                int value = nums[index];

                if (value < target) {
                    minIndex = Math.min(index + 1, nums.length - 1);
                    continue;
                }
                if (value > target) {
                    maxIndex = Math.max(index - 1, 0);
                    continue;
                }

                targetFirstIndex = index;
                targetLastIndex = index;
                break;
            } while (minIndex != maxIndex || nums[minIndex] == target);

            if (targetFirstIndex == -1) {
                return new int[] {targetFirstIndex, targetLastIndex};
            }

            do {
                int index = (int) Math.floor((targetFirstIndex + minIndex) / 2D);

                int value = nums[index];

                if (value < target) {
                    minIndex = Math.min(index + 1, nums.length - 1);
                    continue;
                }

                targetFirstIndex = index;
            } while (targetFirstIndex != minIndex);

            do {
                int index = Math.min((int) Math.ceil((targetLastIndex + maxIndex) / 2D), nums.length - 1);

                int value = nums[index];

                if (value > target) {
                    maxIndex = Math.max(index - 1, 0);
                    continue;
                }

                targetLastIndex = index;
            } while (targetLastIndex != maxIndex);

            return new int[] {targetFirstIndex, targetLastIndex};
        }
    }
}
