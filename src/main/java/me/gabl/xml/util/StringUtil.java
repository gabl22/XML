package me.gabl.xml.util;

import java.util.function.Predicate;

public class StringUtil {

    private StringUtil() {
        throw new UnsupportedOperationException();
    }

    public static boolean startsWith(String base, String prefix, boolean ignoreCase) {
        return StringUtil.startsWith(base, prefix, ignoreCase, 0);
    }

    public static boolean endsWith(String base, String suffix, boolean ignoreCase) {
        return StringUtil.startsWith(base, suffix, ignoreCase, base.length() - suffix.length());
    }

    public static boolean startsWith(String base, String prefix, boolean ignoreCase, int toffset) {
        if (toffset < 0 || toffset > base.length() - prefix.length())
            return false;

        for (int i = 0; i < prefix.length(); i++)
            if (!StringUtil.equal(base.charAt(toffset + i), prefix.charAt(i), ignoreCase))
                return false;
        return true;
    }

    public static boolean equal(char a, char b, boolean ignoreCase) {
        if (ignoreCase)
            return String.valueOf(a).equalsIgnoreCase(String.valueOf(b));
        return a == b;
    }

    public static int next(String base, int toffset, Predicate<Character> condition) {
        for (int i = toffset; i < base.length(); i++)
            if (condition.test(base.charAt(i)))
                return i;
        return -1;
    }

    public static int next(String base, int toffset, Predicate<Character> condition, Predicate<Character> stopCondition) {
        for (int i = toffset; i < base.length(); i++) {
            if (stopCondition.test(base.charAt(i)))
                return -2;
            if (condition.test(base.charAt(i)))
                return i;
        }
        return -1;
    }

    public static String removeLineBreaks(String base) {
        StringBuilder valueBuilder = new StringBuilder();
        for (char c : base.toCharArray())
            if (String.valueOf(c).matches("."))
                valueBuilder.append(c);
        return valueBuilder.toString();
    }

    public static String box(String base, String box, boolean reverseBoxEnd) {
        return box + base + ((reverseBoxEnd) ? StringUtil.reverse(box) : box);
    }

    public static String box(String base, String box, String escBox, boolean reverseBoxEnd) {
        return StringUtil.box(base.replace(box, escBox), box, reverseBoxEnd);
    }

    public static String reverse(String base) {
        return new StringBuilder(base).reverse().toString();
    }

    public static String boxBestQuotes(String base, String escQuotes) {
        if (base.contains("\"")) {
            if (base.contains("'"))
                return StringUtil.box(base, "\"", escQuotes, false);
            return StringUtil.box(base, "'", false);
        }
        return StringUtil.box(base, "\"", false);
    }

    public static String boxApos(String base, String escApos) {
        return StringUtil.box(base, "'", escApos, false);
    }

    public static String boxQuotes(String base, String escQuotes) {
        return StringUtil.box(base, "\"", escQuotes, false);
    }
}
