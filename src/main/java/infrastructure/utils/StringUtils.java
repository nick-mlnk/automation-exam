package infrastructure.utils;

public class StringUtils {

    public static boolean isUpperCase(String src) {
        for (char c : src.toCharArray()) {
            if (!Character.isUpperCase(c) && !Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}
