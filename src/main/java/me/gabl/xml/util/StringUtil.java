package me.gabl.xml.util;

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
}
