package infrastructure.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

    public static List<String> parseUrls(String src) {
        Pattern urlPattern = Pattern.compile("(https?://\\S+)[^)\"\\;]");
        Matcher urlMatcher = urlPattern.matcher(src);
        List<String> allUrls = new ArrayList<>();
        while (urlMatcher.find()) {
            allUrls.add(urlMatcher.group());
        }
        return allUrls;
    }

    public static String replaceAllExceptOfLettersAndDigits(String source) {
        return source.replaceAll("[^\\p{L}0-9]","");
    }
}
