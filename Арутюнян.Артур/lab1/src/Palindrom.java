public class Palindrom {
   public static Boolean checkPalindrom(int x) {
        String str = Integer.toString(x);
        var len = str.length();
        for (int i = 0; i < len / 2; i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
} 