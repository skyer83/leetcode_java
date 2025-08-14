//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 只会存在一个有效答案 
// 
//
// 
//
// 进阶：你可以想出一个时间复杂度小于 O(n²) 的算法吗？ 
//
// Related Topics 数组 哈希表 👍 19994 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
//        return twoSum01(nums, target);
        return twoSum02(nums, target);
    }

    /**
     * 执行耗时:2 ms,击败了99.65% 的Java用户
     * 内存消耗:44.1 MB,击败了63.06% 的Java用户
     * @param nums
     * @param target
     * @return int[]
     * @author shenjh
     * @since 2025/8/14 15:44
     */
    public int[] twoSum02(int[] nums, int target) {
        Map<Integer, Integer> searchMap = new HashMap<>();
        int length = nums.length;
        for (int i = 0; i < length; i++) {
            int num = nums[i];
            Integer searchPosition = searchMap.get(num);
            if (searchPosition != null) {
                return new int[]{searchPosition, i};
            } else {
                // 存储第一个数字的位置
                searchMap.put(target - nums[i], i);
            }
        }
        return null;
    }

    /**
     * 执行耗时:52 ms,击败了20.56% 的Java用户
     * 内存消耗:43.8 MB,击败了97.80% 的Java用户
     * @param nums
     * @param target
     * @return int[]
     * @author shenjh
     * @since 2025/8/14 15:44
     */
    public int[] twoSum01(int[] nums, int target) {
        int[] result = new int[2];
        int jLength = nums.length;
        int iLength = jLength - 1;
        for (int i = 0; i < iLength; i++) {
            for (int j = i + 1; j < jLength; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
