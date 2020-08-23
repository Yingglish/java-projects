package github.yix.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）
 */
public class Subsets {
    List<List<Integer>> res = new ArrayList<>();
    public void backtrace(int[] nums, ArrayList<Integer> list, int begin){
        // 保存结果
        res.add(new ArrayList<>(list));

        for(int i = begin; i < nums.length; i++){
            // 每次遍历数组nums的时候，都加入当前元素
            list.add(nums[i]);
            backtrace(nums, list, i + 1);
            // 在递归回来的时候再回溯，删除刚刚加入的元素
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> subsets_backtrace(int[] nums) {
        if(nums.length == 0){
            return null;
        }
        backtrace(nums, new ArrayList<Integer>(), 0);
        return res;
    }

    public List<List<Integer>> subsets_foreach(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());

        // 每次遍历一个元素，加入已生成的子集中
        for (int num : nums) {
            int all = res.size();
            for (int j = 0; j < all; j++) {
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(num);
                res.add(tmp);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Subsets app = new Subsets();
        int[] num = {1,2,3};
        System.out.println(Arrays.toString(app.subsets_foreach(num).toArray()));
        System.out.println(Arrays.toString(app.subsets_backtrace(num).toArray()));
    }
}
