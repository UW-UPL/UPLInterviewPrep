public class Solution {
    public static int firstOccurrence(int[] nums, int k) {
        int first = 0;
        int last = nums.length - 1;
        while (first < last) {
            int med = first + (last - first) / 2;
            if (nums[med] < k) {
                first = med + 1;
            } else if (nums[med] > k){
                last = med - 1;
            } else {
                last = med;
            }
        }
        if (nums[first] == k) return first;
        else return -1;
    }

    public static void main(String[] args) {
        int[] nums = { 1, 1, 1, 2, 2, 2, 2, 3, 4, 5, 6, 7, 8, 8, 10, 10, 11, 12, 16 };
        System.out.print("Array: [");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            if (i != nums.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        for (int k = 0; k < 20; k++) {
            int first = firstOccurrence(nums, k);
            System.out.println(k + " first found at index " + first);
        }
    }
}
