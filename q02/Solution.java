public class Solution {
    public static boolean isAbbr(String name, String abbr) {
        int namePos = 0;
        int abbrPos = 0;
        while (namePos < name.length() && abbrPos < abbr.length()) {
            if (Character.isDigit(abbr.charAt(abbrPos))) {
                int digitEnd = abbrPos;
                while (Character.isDigit(abbr.charAt(digitEnd))) {
                    digitEnd++;
                }
                int abbrNum = Integer.parseInt(abbr.substring(abbrPos, digitEnd));
                namePos += abbrNum;
                abbrPos = digitEnd;
            } else {
                if (name.charAt(namePos) != abbr.charAt(abbrPos)) {
                    return false;
                }
                namePos++;
                abbrPos++;
            }
        }
        return namePos == name.length() && abbrPos == abbr.length();
    }
    
    public static void main(String[] args) {
        System.out.println(isAbbr("LOCALIZATION", "L10N"));
        System.out.println(isAbbr("LOCALIZATION", "L9N"));
        System.out.println(isAbbr("LOCALIZATION", "L10Q"));
    }
}
