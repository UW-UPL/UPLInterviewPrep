public class Solution {
    public static IntPair PairyTime(int k, int[] A) {
        int[] found = new int[k+1];
        for (int i = 0; i < k+1; i++) {
            found[i] = -1;
        }
        
        for (int i = 0; i < A.length; i++) {
            // the modulus operator computes the remainder, which
            // isn't what we want here (it does the wrong thing with
            // negative numbers). the difference is that we want the
            // division to round _towards zero_, not down.
            int a = Math.floorMod(A[i], k);
            if (found[k-a] != -1) {
                return new IntPair(found[k-a], i);
            }
            found[a] = i;

            // if we find another a that's equal to zero, found[k-a]
            // will not be filled as it should be in unless we also do this
            if (a == 0) {
                found[k] = i;
            }
        }

        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(PairyTime(3, new int[]{-2, 2}));
        System.out.println(PairyTime(8, new int[]{1, -2, -9, 1, 1, 1}));
        System.out.println(PairyTime(7, new int[]{0, 3, 1, 1, 0}));
        System.out.println(PairyTime(4, new int[]{}));
        System.out.println(PairyTime(3, new int[]{1, 1}));
    }

    private static class IntPair {
        public int fst;
        public int snd;
        
        public IntPair(int f, int s) {
            fst = f;
            snd = s;
        }

        public String toString() {
            return "[" + fst + ", " + snd + "]";
        }
    }
}
