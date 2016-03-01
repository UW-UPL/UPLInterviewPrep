public class Solution {
    public static String lookAndSay(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char cur = s.charAt(i);
            int j = i;
            while (j < s.length() && s.charAt(j) == cur) {
                j++;
            }
            sb.append(j - i);
            sb.append(cur);
            i = j;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "1";
        for (int i = 0; i < 10; i++) {
            System.out.println(s);
            s = lookAndSay(s);
        }
    }
}
