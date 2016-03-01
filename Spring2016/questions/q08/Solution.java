public class Solution {
    // subarraySum fills out a 2-dimensional array, where
    // the value at index [i][j] is the sum of the elements
    // in nums from indices i to j (inclusive)
    public static int[][] subarraySum(int[] nums) {
        int[][] sums = new int[nums.length][nums.length];

        // base case: i == j => nums[i]
        for (int i = 0; i < nums.length; i++) {
            sums[i][i] = nums[i];
        }

        // general case: i < j
        // we fill out the array in order of ascending
        // difference j - i (set by the variable diff).
        for (int diff = 1; diff < nums.length; diff++) {
            for (int i = 0; i < nums.length - diff; i++) {
                int j = i + diff;
                // to sum elements i through j, look up the sum
                // for elements i through j-1 and add element j
                int sum = sums[i][j-1] + sums[j][j];
                // if you know that i will always be less than j,
                // you only have to fill out one diagonal half of
                // the table
                sums[i][j] = sum;
            }
        }
        return sums;
    }

    // return the score of the _first_ player when both are playing
    // optimally. the second player's score can be found by subtracting
    // this from the total sum of elements
    public static int twoEnds(int[] nums) {
        int[][] sums = subarraySum(nums);
        // fill out a 2-dimensional array called opts (OPTimal valueS),
        // where opts[i][j] is the score of the first player if both
        // play optimally with the cards found between indices i and j
        int[][] opts = new int[nums.length][nums.length];

        // base case: i == j => nums[i]
        for (int i = 0; i < nums.length; i++) {
            opts[i][i] = nums[i];
        }

        // general case: i < j, filled out in order of ascending difference
        
        // the best that the first player can do is found by minimizing
        // the score of the second player. the second player will either
        // be playing with the cards from i+1 to j or from i to j-1.
        // to obtain the first player's score from this, subtract from
        // the sum of all the cards from i to j.

        // another way of looking at this: to find the first player's
        // score, take all the cards, remove all the cards that the
        // second player would take if they were playing optimally, and
        // sum the cards you have left
        for (int diff = 1; diff < nums.length; diff++) {
            for (int i = 0; i < nums.length - diff; i++) {
                int j = i + diff;
                opts[i][j] = sums[i][j] - Math.min(opts[i+1][j], opts[i][j-1]);
            }
        }
        // the answer is the value stored in opt[0][nums.length - 1]
        // because this contains every card
        return opts[0][nums.length - 1];
    }

    public static void main(String[] args) {
        int[] nums = { 1, 3, 100, 2 };
        int sum = 0;
        System.out.print("Nums: [");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i]);
            sum += nums[i];
            if (i < nums.length - 1) System.out.print(", ");
        }
        System.out.println("]");
        int firstPlayerScore = twoEnds(nums);
        System.out.println("First player score: " + firstPlayerScore);
        System.out.println("Second player score: " + (sum - firstPlayerScore));
    }
}
